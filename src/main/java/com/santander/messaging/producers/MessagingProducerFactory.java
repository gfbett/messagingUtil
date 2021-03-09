package com.santander.messaging.producers;

import io.swagger.model.MessagingProducerConfig;
import org.springframework.stereotype.Service;

@Service
public class MessagingProducerFactory {
    public Producer getProducer(MessagingProducerConfig.ProviderEnum type) {
        switch(type) {
            case ZEROMQ:
                return new ZeroMQProducer();
            case KAFKA:
                return new KafkaProducer();
            default:
                throw new IllegalArgumentException("Unkown Producer" + type);
        }
    }
}
