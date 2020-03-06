package com.example.rabbitmq;

import com.example.rabbitmq.listener.DemoMessageListener;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class RabbitmqApplicationTests {

    /**
     * @Author 张瑞
     * @Decription: 装配模板
     * @Date 2020/3/5 17:27
     * @Param 
     * @return 
     **/
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private RabbitMessagingTemplate rabbitMessagingTemplate;

    @Test
    public void fanoutExchangeTest(){
        amqpTemplate.convertAndSend("My-Fanout-Exchange","","123");
    }

    @Test
    public void directExchangeTest(){
        amqpTemplate.convertAndSend("My-Direct-Exchange","routingKey.First","12345");
    }

    @Test
    public void topicExchangeTest(){
        amqpTemplate.convertAndSend("My-Topic-Exchange","routingKey.myTest","123456789");
    }

    @Test
    public void topicExchangeTest1(){
        amqpTemplate.convertAndSend("My-Topic-Exchange","routingKey.topic","123456789");
    }

    @Test
    public void headersExchangeTest(){
        Map<String,Object> headers = new HashMap<>();
        headers.put("name","wuqi");
        headers.put("age","26");
        rabbitMessagingTemplate.convertAndSend("My-Headers-Exchange","routingKey.topic","123456789",headers);
    }
}
