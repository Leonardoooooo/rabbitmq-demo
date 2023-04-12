package com.leo.rabbitmq.config;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnsCallback;
import org.springframework.stereotype.Component;

@Component
public class RabbitConfirmCallback implements ConfirmCallback,ReturnsCallback{
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            System.out.println("消息发送成功"+correlationData.getId());
        } else {
            System.out.println("消息发送失败"+correlationData.getId()+cause);
        }
    }

    @Override
    public void returnedMessage(ReturnedMessage returned) {
        System.out.println("消息主体："+returned.getMessage());
        System.out.println("消息主体："+returned.getReplyCode());
        System.out.println("描述："+returned.getReplyText());
        System.out.println("消息使用的交换器exchange："+returned.getExchange());
        System.out.println("消息使用的路由键routing："+returned.getRoutingKey());
    }
     
}