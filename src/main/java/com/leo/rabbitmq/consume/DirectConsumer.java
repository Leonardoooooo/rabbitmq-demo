package com.leo.rabbitmq.consume;

import org.slf4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.leo.rabbitmq.common.RbConfigConstant;
import com.rabbitmq.client.Channel;

@Component
public class DirectConsumer {
    Logger logger = org.slf4j.LoggerFactory.getLogger(DirectConsumer.class);

    enum Action {
        SUCCESS, RETRY, REJECT
    }

    @RabbitHandler
    @RabbitListener(queuesToDeclare = @Queue(RbConfigConstant.QUEUE_NAME))
    public void receive(String msg, Message message, Channel channel) {
        long tag = message.getMessageProperties().getDeliveryTag();
        Action action = Action.SUCCESS;
        try {
            logger.info("receive msg:{}", msg);
            if ("bad".equals(msg)) {
                throw new IllegalArgumentException("bad msg,抛出可重回队列的异常");
            }
            if ("error".equals(msg)) {
                throw new RuntimeException("error msg,抛出不可重回队列的异常");
            }
        } catch (IllegalArgumentException e) {
            logger.error("IllegalArgumentException:{}", e.getMessage());
            action = Action.REJECT;
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


    @RabbitHandler
    @RabbitListener(queuesToDeclare = @Queue(RbConfigConstant.QUEUE_NAME2))
    public void process(String msg, Message message, Channel channel) {
        long tag = message.getMessageProperties().getDeliveryTag();
        Action action = Action.SUCCESS;
        try {
            logger.info("receive msg:{}", msg);
            if ("bad".equals(msg)) {
                throw new IllegalArgumentException("bad msg,抛出可重回队列的异常");
            }
            if ("error".equals(msg)) {
                throw new RuntimeException("error msg,抛出不可重回队列的异常");
            }
        } catch (IllegalArgumentException e) {
            logger.error("IllegalArgumentException:{}", e.getMessage());
            action = Action.REJECT;
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
