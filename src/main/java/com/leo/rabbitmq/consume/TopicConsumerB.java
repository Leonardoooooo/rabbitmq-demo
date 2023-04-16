package com.leo.rabbitmq.consume;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.leo.rabbitmq.common.RbConfigConstant;
import com.rabbitmq.client.Channel;

@Component
 public class TopicConsumerB{
    Logger logger = org.slf4j.LoggerFactory.getLogger(TopicConsumerB.class);

    private ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<String, Integer>();

    enum Action {
        SUCCESS, RETRY, REJECT
    }

    @RabbitHandler
    @RabbitListener(queuesToDeclare = @Queue(RbConfigConstant.TOPIC_QUEUE_B))
    public void receive(Map<String, Object> msgMap, Message message,Channel channel) {
        long tag = message.getMessageProperties().getDeliveryTag();
        Action action = Action.SUCCESS;
        try {
            String msg =msgMap.get("msg").toString(); 
            String msgId = msgMap.get("msgId").toString();
            logger.info("receive msg:{},tag:{},msgId:{}", msg,tag,msgId);
            if ("bad".equals(msg) && map.get(msgId) == null) {
                map.put(msgId, 1);
                throw new IllegalArgumentException("bad msg,抛出可重回队列的异常");
            }
            if(map.get(msgId) != null){
                logger.info("重试次数:{}",map.get(msgId));
            }
            if ("error".equals(msg)) {
                throw new RuntimeException("error msg,抛出不可重回队列的异常");
            }
        } catch (IllegalArgumentException e) {
            logger.error("IllegalArgumentException:{}", e.getMessage());
            action = Action.RETRY;
        } catch (Exception e) {
            logger.error("Exception:{}", e.getMessage());
            action = Action.REJECT;
        } finally {
            try {
                switch (action) {
                    case SUCCESS:
                        channel.basicAck(tag, false);
                        break;
                    case RETRY:
                        channel.basicNack(tag, false, true);
                        break;
                    case REJECT:
                        channel.basicNack(tag, false, false);
                        break;
                    default:
                        break;
                }
                channel.close();
            } catch (Exception e2) {
                logger.error("finally Exception:{}", e2.getMessage());
            }
        }

    }
    
 }