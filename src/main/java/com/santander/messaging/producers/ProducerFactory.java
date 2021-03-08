package com.santander.messaging.producers;

import io.swagger.model.ProducerConfig;
import org.springframework.stereotype.Service;

@Service
public class ProducerFactory {
    public Producer getProducer(ProducerConfig.ProviderEnum type) {
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
