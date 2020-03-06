package com.example.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName QueueConfiguration
 * @Decription: Queue队列注入
 * @Date: 2020/3/3
 * @Author: 张瑞
 * @Version: 1.0
 */
@Configuration
public class QueueConfiguration {

    /**
     * @Author 张瑞
     * @Decription: 注入第一个队列实例
     * @Date 2020/3/3 9:35
     * @Param []
     * @return org.springframework.amqp.core.Queue
     **/
    @Bean(name="myFirstQueue")
    public Queue getFirstQueue(){
        return new Queue("My-First-Queue");
    }

    /**
     * @Author 张瑞
     * @Decription: 注入第二个队列实例
     * @Date 2020/3/3 9:35
     * @Param []
     * @return org.springframework.amqp.core.Queue
     **/
    @Bean(name="myTwoQueue")
    public Queue getTwoQueue(){
        return new Queue("My-Two-Queue");
    }

    /**
     * @Author 张瑞
     * @Decription: 注入第三个队列实例
     * @Date 2020/3/3 9:35
     * @Param []
     * @return org.springframework.amqp.core.Queue
     **/
    @Bean(name="myThreeQueue")
    public Queue getThreeQueue(){
        return new Queue("My-Three-Queue");
    }
}