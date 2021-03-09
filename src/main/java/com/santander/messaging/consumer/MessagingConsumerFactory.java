package com.santander.messaging.consumer;

import io.swagger.model.MessagingConsumerConfig;
import org.springframework.stereotype.Service;

@Service
public class MessagingConsumerFactory {

    public Consumer getConsumer(MessagingConsumerConfig.ProviderEnum provider) {
        switch(provider) {
            case ZEROMQ:
                return new ZeroMQConsumer();
            case KAFKA:
                return new KafkaConsumer();
            default:
                throw new IllegalArgumentException("Unknown consumer: " + provider);
        }
    }
}
