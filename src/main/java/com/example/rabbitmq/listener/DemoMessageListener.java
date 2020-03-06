package com.example.rabbitmq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName DemoMessageListener
 * @Decription: 注入消息监听器
 * @Date: 2020/3/3
 * @Author: 张瑞
 * @Version: 1.0
 */
@Component
public class DemoMessageListener {

    @RabbitListener(queues = "My-First-Queue") //指定Queue队列
    public void firstConsumer(String string){
        System.out.println("我是:My-First-Queue" + "\tString:"+string);
    }

    @RabbitListener(queues = "My-Two-Queue") //指定Queue队列
    public void twoConsumer(Integer string){
        System.out.println("我是:My-Two-Queue" + "\tInteger:"+string);
    }

    @RabbitListener(queues = "My-Three-Queue") //指定Queue队列
    public void threeConsumer(String string){
        System.out.println("我是:My-Three-Queue" + "\tString:"+string);
    }
}