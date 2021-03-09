package com.santander.messaging.producers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.messaging.model.Message;
import io.swagger.model.MessagingProducerConfig;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

public abstract class Producer {

    private ObjectMapper objectMapper;

    public Producer() {
        objectMapper = new ObjectMapper();
    }

    public void produceMessages(MessagingProducerConfig producerConfig) {
        initProducer(producerConfig.getProperties());
        sendMessages(producerConfig.getQuantity(), producerConfig.getSizeFrom(), producerConfig.getSizeTo());
    }

    private void sendMessages(int quantity, int sizeFrom, int sizeTo) {
        for (int i = 0; i < quantity; i++) {
            String payload = RandomStringUtils.randomAlphabetic(sizeFrom, sizeTo);
            Message message = new Message(i, payload);
            System.out.println("Sending: " + message);
            String messageString;
            try {
                messageString = objectMapper.writeValueAsString(message);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error converting to json", e);
            }
            sendMessage(messageString);
        }

    }

    protected abstract void initProducer(Map<String, String> producerConfig);

    protected abstract void sendMessage(String message);
}
