package com.leo.rabbitmq.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.leo.rabbitmq.common.RbConfigConstant;
import com.leo.rabbitmq.config.RabbitConfirmCallback;
import com.leo.rabbitmq.service.RabbitMQService;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;

@Component("rabbitMQService")
public class RabbitMQServiceImpl implements RabbitMQService {
  Logger logger = LoggerFactory.getLogger(RabbitMQServiceImpl.class);

  private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  @Resource
  private RabbitTemplate rabbitTemplate;

  @Resource
  private RabbitConfirmCallback rabbitConfirmCallback;

  @Resource
  private SimpleRabbitListenerContainerFactory factory;

  @PostConstruct
  public void init() {
    logger.info("rabbitMQService init");
    rabbitTemplate.setConfirmCallback(rabbitConfirmCallback);
    rabbitTemplate.setReturnsCallback(rabbitConfirmCallback);
  }

  @Override
  public void send(String msg) {
    String msgId = UUID.randomUUID().toString().replace("-", "").substring(0, 32);
    CorrelationData correlationData = new CorrelationData(msgId);
    try {
      rabbitTemplate.convertAndSend(RbConfigConstant.EXCHANGE_NAME, RbConfigConstant.ROUTING_KEY, msg, correlationData);
      logger.info("rabbitTemplate send success msgId:{}", msgId);

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }

  @Override
  public void sendTopic(String msg, String routingKey) {
    Map<String, Object> message = getMessage(msg);
    CorrelationData correlationData =(CorrelationData) message.remove("correlationData");
    try {
      rabbitTemplate.convertAndSend(RbConfigConstant.TOPIC_EXCHANGE_NAME, routingKey, message, correlationData);
      logger.info("rabbitTemplate send success msgId:{}", correlationData.getId());

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }

  @Override
  public void sendFanout(String msg) {
    Map<String, Object> message = getMessage(msg);
    CorrelationData correlationData =(CorrelationData) message.remove("correlationData");
    try {
      rabbitTemplate.convertAndSend(RbConfigConstant.FANOUT_EXCHANGE_NAME, "", message, correlationData);
      logger.info("rabbitTemplate send success msgId:{}", correlationData.getId());

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }

  private Map<String, Object> getMessage(String msg) {
    String msgId = UUID.randomUUID().toString().replace("-", "").substring(0, 32);
    CorrelationData correlationData = new CorrelationData(msgId);
    String sendTime = sdf.format(new Date());
    Map<String, Object> map = new HashMap<>();
    map.put("msgId", msgId);
    map.put("sendTime", sendTime);
    map.put("msg", msg);
    map.put("correlationData", correlationData);
    return map;
  }

}
