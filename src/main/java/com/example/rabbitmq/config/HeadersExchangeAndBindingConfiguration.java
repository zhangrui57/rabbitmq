package com.example.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName HeadersExchangeAndBindingConfiguration
 * @Decription:headers路由策略的交换机注入、Queue与Exchange的绑定注入
 * @Date: 2020/3/5
 * @Author: 张瑞
 * @Version: 1.0
 */
@Configuration
public class HeadersExchangeAndBindingConfiguration {
    /**
     * @Author 张瑞
     * @Decription: 注入Fanout路由策略的Exchange交换机实例
     * @Date 2020/3/3 9:39
     * @Param []
     * @return org.springframework.amqp.core.FanoutExchange
     **/
    @Bean(name = "myHeadersExchange")
    HeadersExchange getHeadersExchange(){
        return new HeadersExchange("My-Headers-Exchange");
    }

    /**
     * @Author 张瑞
     * @Decription: 将第一个队列，绑定到myTopicExchange对应的交换机
     * @Date 2020/3/3 9:44
     * @Param [myFirstQueue, myFanoutExchange]
     * @return org.springframework.amqp.core.Binding
     **/
    @Bean
    Binding bindingQueueOneToHeadersExchange(@Qualifier("myFirstQueue") Queue myFirstQueue,
                                           @Qualifier("myHeadersExchange") HeadersExchange myHeadersExchange){
        Map<String,Object> headers = new HashMap<>();
        headers.put("name","zhangrui");
        headers.put("age","27");
        return BindingBuilder.bind(myFirstQueue).to(myHeadersExchange).whereAll(headers).match();
    }

    /**
     * @Author 张瑞
     * @Decription: 将第一个队列，绑定到myTopicExchange对应的交换机
     * @Date 2020/3/3 9:44
     * @Param [myFirstQueue, myFanoutExchange]
     * @return org.springframework.amqp.core.Binding
     **/
    @Bean
    Binding bindingQueueTwoToHeadersExchange(@Qualifier("myTwoQueue") Queue myTwoQueue,
                                           @Qualifier("myHeadersExchange") HeadersExchange myHeadersExchange){
        Map<String,Object> headers = new HashMap<>();
        headers.put("name","wuqi");
        headers.put("age","27");
        return BindingBuilder.bind(myTwoQueue).to(myHeadersExchange).whereAny(headers).match();
    }
}