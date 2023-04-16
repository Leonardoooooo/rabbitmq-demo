package com.leo.rabbitmq.service;

public interface RabbitMQService {
    
        void send(String msg);

        void sendTopic(String msg,String routingKey);

        void sendFanout(String msg);
    
}
