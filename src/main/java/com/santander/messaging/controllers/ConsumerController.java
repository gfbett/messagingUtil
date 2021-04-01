package com.santander.messaging.controllers;

import com.santander.messaging.consumer.Consumer;
import com.santander.messaging.consumer.MessagingConsumerFactory;
import io.swagger.api.ConsumeApi;
import io.swagger.model.MessagingConsumerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
public class ConsumerController implements ConsumeApi {

    @Autowired
    MessagingConsumerFactory messagingConsumerFactory;

    @Override
    public ResponseEntity<Void> consume(@Valid MessagingConsumerConfig consumerConfig) {
        log.info("Creating consumer for: {}", consumerConfig.getProvider());
        Consumer consumer = messagingConsumerFactory.getConsumer(consumerConfig.getProvider());
        consumer.consumeMessages(consumerConfig);
        return ResponseEntity.ok().build();
    }
}
