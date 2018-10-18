package com.xx.webframework.amqp;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue sampleQueue(){
        return new Queue("sample");
    }

    @Bean
    public Exchange exchange(){
        return new DirectExchange("sampleExchange");
    }

    @Bean
    public Binding binding(Queue queue,DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("routekey");
    }
}
