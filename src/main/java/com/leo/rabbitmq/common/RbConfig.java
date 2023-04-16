package com.leo.rabbitmq.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
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
public class RbConfig implements BeanPostProcessor {

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

    // direct
    @Bean
    public Queue rabbitmqDemoDirectQueue() {
        Queue queue = new Queue(RbConfigConstant.QUEUE_NAME, true, false, false);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Exchange rabbitmqDemoDirectExchange() {
        Exchange exchange = new DirectExchange(RbConfigConstant.EXCHANGE_NAME, true, false);
        // set vhost to /demo
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

    // topic

    @Bean
    public Queue topicQueueA() {
        Queue queue = new Queue(RbConfigConstant.TOPIC_QUEUE_A, true, false, false);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Queue topicQueueB() {
        Queue queue = new Queue(RbConfigConstant.TOPIC_QUEUE_B, true, false, false);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Queue topicQueueC() {
        Queue queue = new Queue(RbConfigConstant.TOPIC_QUEUE_C, true, false, false);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Exchange topicExchange() {
        Exchange exchange = new TopicExchange(RbConfigConstant.TOPIC_EXCHANGE_NAME, true, false);

        rabbitAdmin.declareExchange(exchange);
        return exchange;
    }

    @Bean
    public Binding topicBindingA() {
        return BindingBuilder
                .bind(topicQueueA())
                .to(topicExchange())
                .with(RbConfigConstant.TOPIC_ROUTING_KEY_A).noargs();
    }

    @Bean
    public Binding topicBindingB() {
        return BindingBuilder
                .bind(topicQueueB())
                .to(topicExchange())
                .with(RbConfigConstant.TOPIC_ROUTING_KEY_STAR).noargs();
    }

    @Bean
    public Binding topicBindingC() {
        return BindingBuilder
                .bind(topicQueueC())
                .to(topicExchange())
                .with(RbConfigConstant.TOPIC_ROUTING_KEY_HASH).noargs();
    }

    // fanout
    @Bean
    public Queue fanoutQueueA() {
        Queue queue = new Queue(RbConfigConstant.FANOUT_QUEUE_A, true, false, false);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Queue fanoutQueueB() {
        Queue queue = new Queue(RbConfigConstant.FANOUT_QUEUE_B, true, false, false);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Exchange fanoutExchange() {
        Exchange exchange = new FanoutExchange(RbConfigConstant.FANOUT_EXCHANGE_NAME, true, false);
        rabbitAdmin.declareExchange(exchange);
        return exchange;
    }

    @Bean
    public Binding fanoutBindingA(Queue fanoutQueueA, FanoutExchange fanoutExchange) {
        return BindingBuilder
                .bind(fanoutQueueA)
                .to(fanoutExchange);
    }

    @Bean
    public Binding fanoutBindingB() {
        return BindingBuilder
                .bind(fanoutQueueB())
                .to(fanoutExchange()).with("").noargs();
    }

    // header
    @Bean
    public Queue headersQueueA() {
        Queue queue = new Queue(RbConfigConstant.Headers_QUEUE_A, true, false, false);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Queue headersQueueB() {
        Queue queue = new Queue(RbConfigConstant.Headers_QUEUE_B, true, false, false);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Exchange headersExchange() {
        Exchange exchange = new HeadersExchange(RbConfigConstant.Headers_EXCHANGE_NAME, true, false);
        rabbitAdmin.declareExchange(exchange);
        return exchange;
    }

    @Bean
    public Binding headersBindingA(Queue headersQueueA, HeadersExchange headersExchange) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("name", "leo");
        headers.put("nickname", "bark");
        return BindingBuilder
                .bind(headersQueueA)
                .to(headersExchange).whereAll(headers).match();
    }

    @Bean
    public Binding headersBindingB(Queue headersQueueB, HeadersExchange headersExchange) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("gender", "male");
        headers.put("hobby", "jogging");
        return BindingBuilder
                .bind(headersQueueB)
                .to(headersExchange).whereAny(headers).match();
    }

}
