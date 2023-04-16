package com.leo.rabbitmq.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

 
import com.leo.rabbitmq.service.RabbitMQService;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/test")
public class SendMessageRestController {

        Logger logger = LoggerFactory.getLogger(SendMessageRestController.class);
    
        @Resource(name="rabbitMQService")
        private RabbitMQService rabbitMQService;
    
        @RequestMapping("/send")
        public String send(@RequestParam(name="msg") String msg) {
            System.out.println(msg);
            rabbitMQService.send(msg);
            return "send success";
        }

        @RequestMapping("/sendTopic")
        public String sendTopic(@RequestParam(name="msg") String msg,@RequestParam(name="routingKey") String routingKey) {
            System.out.println(msg);
            rabbitMQService.sendTopic(msg,routingKey);
            return "send success";
        }

        @RequestMapping("/sendFanout")
        public String sendFanout(@RequestParam(name="msg") String msg) {
            System.out.println(msg);
            rabbitMQService.sendFanout(msg);
            return "send success";
        }

      
        @RequestMapping("/")
        public String index() {
            logger.info("index");
             
            return "index";
        }
    
}
