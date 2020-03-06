package com.example.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName TopicExchangeAndBindingConfiguration
 * @Decription:topic路由策略（支持模糊匹配）的交换机注入、Queue与Exchange的绑定注入
 * @Date: 2020/3/5
 * @Author: 张瑞
 * @Version: 1.0
 */
@Configuration
public class TopicExchangeAndBindingConfiguration {
    /**
     * @Author 张瑞
     * @Decription: 注入Fanout路由策略的Exchange交换机实例
     * @Date 2020/3/3 9:39
     * @Param []
     * @return org.springframework.amqp.core.FanoutExchange
     **/
    @Bean(name = "myTopicExchange")
    TopicExchange getTopicExchange(){
        return new TopicExchange("My-Topic-Exchange");
    }

    /**
     * @Author 张瑞
     * @Decription: 将第一个队列，绑定到myTopicExchange对应的交换机
     * @Date 2020/3/3 9:44
     * @Param [myFirstQueue, myFanoutExchange]
     * @return org.springframework.amqp.core.Binding
     **/
    @Bean
    Binding bindingQueueOneToTopicExchange(@Qualifier("myFirstQueue") Queue myFirstQueue,
                                            @Qualifier("myTopicExchange") TopicExchange myTopicExchange){
        return BindingBuilder.bind(myFirstQueue).to(myTopicExchange).with("routingKey.#");
    }

    /**
     * @Author 张瑞
     * @Decription: 将第二个队列，绑定到myTopicExchange对应的交换机
     * @Date 2020/3/3 9:44
     * @Param [myTwoQueue, myFanoutExchange]
     * @return org.springframework.amqp.core.Binding
     **/
    @Bean
    Binding bindingQueueTwoToTopicExchange(@Qualifier("myTwoQueue") Queue myTwoQueue,
                                           @Qualifier("myTopicExchange") TopicExchange myTopicExchange){
        return BindingBuilder.bind(myTwoQueue).to(myTopicExchange).with("#.topic");
    }

    /**
     * @Author 张瑞
     * @Decription: 将第三个队列，绑定到myTopicExchange对应的交换机
     * @Date 2020/3/3 9:44
     * @Param [myThreeQueue, myFanoutExchange]
     * @return org.springframework.amqp.core.Binding
     **/
    @Bean
    Binding bindingQueueThreeToTopicExchange(@Qualifier("myThreeQueue") Queue myThreeQueue,
                                           @Qualifier("myTopicExchange") TopicExchange myTopicExchange){
        return BindingBuilder.bind(myThreeQueue).to(myTopicExchange).with("#");
    }
}