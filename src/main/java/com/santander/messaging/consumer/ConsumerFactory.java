package com.santander.messaging.consumer;

import io.swagger.model.ConsumerConfig;
import org.springframework.stereotype.Service;

@Service
public class ConsumerFactory {

    public Consumer getConsumer(ConsumerConfig.ProviderEnum provider) {
        switch(provider) {
            case ZEROMQ:
                return new ZeroMQConsumer();
            default:
                throw new IllegalArgumentException("Unknown consumer: " + provider);
        }
    }
}
