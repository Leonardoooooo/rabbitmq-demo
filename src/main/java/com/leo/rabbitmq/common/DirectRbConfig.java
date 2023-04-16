package com.leo.rabbitmq.common;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;



import jakarta.annotation.Resource;

@Component
@Configuration
public class DirectRbConfig implements BeanPostProcessor{

    @Resource
    private RabbitAdmin rabbitAdmin;

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;

    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setConcurrentConsumers(2);
        factory.setMaxConcurrentConsumers(5);
        return factory;
    }

    @Bean
    public Queue rabbitmqDemoDirectQueue() {
        Queue queue = new Queue(RbConfigConstant.QUEUE_NAME, true, false, false);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Exchange rabbitmqDemoDirectExchange() {
        Exchange exchange = new DirectExchange(RbConfigConstant.EXCHANGE_NAME, true, false);
        //set vhost to /demo
        rabbitAdmin.declareExchange(exchange);
        return exchange;
    }

    @Bean
    public Binding rabbitmqDemoDirectBinding() {
        return BindingBuilder
                .bind(rabbitmqDemoDirectQueue())
                .to(rabbitmqDemoDirectExchange())
                .with(RbConfigConstant.ROUTING_KEY).noargs();
    }

    


}
