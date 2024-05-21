package com.tracking.management.system.trackingmicroservice.util.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;


@Configuration
public class MessagingConfig {
    private final CachingConnectionFactory cachingConnectionFactory;

    @Value("${messaging.queue.tracking.status}")
    private String trackingStatusQueue;

    private static final String EXCHANGE_FALBACK = "x.process-failure-transportation";

    public MessagingConfig(CachingConnectionFactory cachingConnectionFactory) {
        this.cachingConnectionFactory = cachingConnectionFactory;
    }

    @Bean
    public Queue createClientValidateQueue() {
        return QueueBuilder.durable(trackingStatusQueue)
                .withArgument("x-dead-letter-exchange", EXCHANGE_FALBACK)
                .withArgument("x-dead-letter-routing-key", "transportation")
                .build();
    }

    @Bean
    public RetryOperationsInterceptor retryInterceptor() {
        return RetryInterceptorBuilder.stateless().maxAttempts(5)
                .backOffOptions(2000, 2.0, 100000)
                .recoverer(new RejectAndDontRequeueRecoverer())
                .build();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();

        configurer.configure(factory, cachingConnectionFactory);
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        factory.setBatchSize(5);
        factory.setAdviceChain(retryInterceptor());

        return factory;
    }


    @Bean
    public Declarables createDeadLetterSchema() {
        return new Declarables(
                new DirectExchange(EXCHANGE_FALBACK),
                new Queue("q.fall-back-process-transportation"),
                new Binding(trackingStatusQueue, Binding.DestinationType.QUEUE, EXCHANGE_FALBACK, "transportation", null)
        );
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter converter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setMessageConverter(converter);

        return rabbitTemplate;
    }

}
