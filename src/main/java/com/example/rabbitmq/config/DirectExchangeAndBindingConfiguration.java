package com.example.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName DirectExchangeAndBindingConfiguration
 * @Decription: direct路由策略（路由键routingKey）的交换机主、Queue与Exchange的绑定注入
 * @Date: 2020/3/3
 * @Author: 张瑞
 * @Version: 1.0
 */
@Configuration
public class DirectExchangeAndBindingConfiguration {

    /**
     * @Author 张瑞
     * @Decription: 注入Direct路由策略的Exchange交换机实例
     * @Date 2020/3/3 9:53
     * @Param []
     * @return org.springframework.amqp.core.DirectExchange
     **/
    @Bean(name="myDirectExchange")
    DirectExchange getDirectExchange(){
        return new DirectExchange("My-Direct-Exchange");
    }

    /**
     * @Author 张瑞
     * @Decription: 将第一个Queuem绑定到directExchange，并指定路由键为“routingKey.First”
     * @Date 2020/3/3 9:56
     * @Param [myFirstQueue, myDirectExchange]
     * @return org.springframework.amqp.core.Binding
     **/
    @Bean
    Binding bindingQueueOneToDirectExchange(@Qualifier("myFirstQueue") Queue myFirstQueue,
                                            @Qualifier("myDirectExchange") DirectExchange myDirectExchange){
        return BindingBuilder.bind(myFirstQueue).to(myDirectExchange).with("routingKey.First");
    }

    /**
     * @Author 张瑞
     * @Decription: 将第二个Queuem绑定到directExchange，并指定路由键为“routingKey.Two”
     * @Date 2020/3/3 9:56
     * @Param [myTwoQueue, myDirectExchange]
     * @return org.springframework.amqp.core.Binding
     **/
    @Bean
    Binding bindingQueueTwoToDirectExchange(@Qualifier("myTwoQueue") Queue myTwoQueue,
                                            @Qualifier("myDirectExchange") DirectExchange myDirectExchange){
        return BindingBuilder.bind(myTwoQueue).to(myDirectExchange).with("routingKey.Two");
    }

    /**
     * @Author 张瑞
     * @Decription: 将第三个Queuem绑定到directExchange，并指定路由键为“routingKey.Three”
     * @Date 2020/3/3 9:56
     * @Param [myThreeQueue, myDirectExchange]
     * @return org.springframework.amqp.core.Binding
     **/
    @Bean
    Binding bindingQueueThreeToDirectExchange(@Qualifier("myThreeQueue") Queue myThreeQueue,
                                            @Qualifier("myDirectExchange") DirectExchange myDirectExchange){
        return BindingBuilder.bind(myThreeQueue).to(myDirectExchange).with("routingKey.Three");
    }
}