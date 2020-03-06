package com.example.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName FanoutExchangeAndBindingConfiguration
 * @Decription: fanout路由策略（广播机制）的交换机注入、Queue与Exchange的绑定注入
 * @Date: 2020/3/3
 * @Author: 张瑞
 * @Version: 1.0
 */
@Configuration
public class FanoutExchangeAndBindingConfiguration {

    /**
     * @Author 张瑞
     * @Decription: 注入Fanout路由策略的Exchange交换机实例
     * @Date 2020/3/3 9:39
     * @Param []
     * @return org.springframework.amqp.core.FanoutExchange
     **/
    @Bean(name = "myFanoutExchange")
    FanoutExchange getFanoutExchange(){
        return new FanoutExchange("My-Fanout-Exchange");
    }

    /**
     * @Author 张瑞
     * @Decription: 将第一个队列，绑定到myFanoutExchange对应的交换机
     * @Date 2020/3/3 9:44
     * @Param [myFirstQueue, myFanoutExchange]
     * @return org.springframework.amqp.core.Binding
     **/
    @Bean
    Binding bindingQueueOneToFanoutExchange(@Qualifier("myFirstQueue") Queue myFirstQueue,
                                            @Qualifier("myFanoutExchange") FanoutExchange myFanoutExchange){
        return BindingBuilder.bind(myFirstQueue).to(myFanoutExchange);
    }

    /**
     * @Author 张瑞
     * @Decription: 将第二个队列，绑定到myFanoutExchange对应的交换机
     * @Date 2020/3/3 9:44
     * @Param [myFirstQueue, myFanoutExchange]
     * @return org.springframework.amqp.core.Binding
     **/
    @Bean
    Binding bindingQueueTwoToFanoutExchange(@Qualifier("myTwoQueue") Queue myTwoQueue,
                                            @Qualifier("myFanoutExchange") FanoutExchange myFanoutExchange){
        return BindingBuilder.bind(myTwoQueue).to(myFanoutExchange);
    }

    /**
     * @Author 张瑞
     * @Decription: 将第三个队列，绑定到myFanoutExchange对应的交换机
     * @Date 2020/3/3 9:44
     * @Param [myFirstQueue, myFanoutExchange]
     * @return org.springframework.amqp.core.Binding
     **/
    @Bean
    Binding bindingQueueThreeToFanoutExchange(@Qualifier("myThreeQueue") Queue myThreeQueue,
                                            @Qualifier("myFanoutExchange") FanoutExchange myFanoutExchange){
        return BindingBuilder.bind(myThreeQueue).to(myFanoutExchange);
    }
}