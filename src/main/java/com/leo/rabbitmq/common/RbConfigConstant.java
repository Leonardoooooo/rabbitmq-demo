package com.leo.rabbitmq.common;

public class RbConfigConstant {

    // direct

    public static final String EXCHANGE_NAME = "exchange_demo";
    public static final String QUEUE_NAME = "queue_demo";
    public static final String ROUTING_KEY = "routingkey_demo";
    public static final String QUEUE_NAME2 = "topicDemo1";

    // topic

    public static final String TOPIC_EXCHANGE_NAME = "topicExchange";
    public static final String TOPIC_QUEUE_A = "topic.queue.a";
    public static final String TOPIC_QUEUE_B = "topic.queue.b";
    public static final String TOPIC_QUEUE_C = "topic.queue.c";

    public static final String TOPIC_ROUTING_KEY_A = "topic.routingkey.a";
    public static final String TOPIC_ROUTING_KEY_STAR = "topic.routingkey.*";
    public static final String TOPIC_ROUTING_KEY_HASH = "topic.routingkey.#";

    // fanout
    public static final String FANOUT_EXCHANGE_NAME = "fanoutExchange";
    public static final String FANOUT_QUEUE_A = "fanout.queue.a";
    public static final String FANOUT_QUEUE_B = "fanout.queue.b";
    
    // headers
    public static final String Headers_EXCHANGE_NAME = "headersExchange";
    public static final String Headers_QUEUE_A = "headers.queue.a";
    public static final String Headers_QUEUE_B = "headers.queue.b";

}
