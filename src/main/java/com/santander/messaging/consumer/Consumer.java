package com.santander.messaging.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.ConsumerConfig;

import java.util.Map;

public abstract class Consumer {

    protected ObjectMapper objectMapper;

    public Consumer() {
        objectMapper = new ObjectMapper();
    }

    public void consumeMessages(ConsumerConfig config) {
        this.initConsumer(config.getProperties());
        consume();
    }

    protected abstract void initConsumer(Map<String, String> producerConfig);

    protected abstract void consume();

}
