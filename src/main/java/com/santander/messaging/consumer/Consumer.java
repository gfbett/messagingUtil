package com.santander.messaging.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.messaging.model.Message;
import io.swagger.model.MessagingConsumerConfig;

import java.util.Map;

public abstract class Consumer {

    protected ObjectMapper objectMapper;

    public Consumer() {
        objectMapper = new ObjectMapper();
    }

    public void consumeMessages(MessagingConsumerConfig config) {
        this.initConsumer(config.getProperties());
        startConsumer(this::processMessageString);
    }

    private void processMessageString(String messageString) {
        try {
            Message message  = null;
            message = objectMapper.readValue(messageString, Message.class);
            System.out.println("Received: " + message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing message");
        }
    }

    protected abstract void initConsumer(Map<String, String> producerConfig);

    protected abstract void startConsumer(java.util.function.Consumer<String> consumer);


}
