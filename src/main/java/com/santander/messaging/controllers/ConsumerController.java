package com.santander.messaging.controllers;

import com.santander.messaging.consumer.Consumer;
import com.santander.messaging.consumer.ConsumerFactory;
import io.swagger.api.ConsumeApi;
import io.swagger.model.ConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ConsumerController implements ConsumeApi {

    @Autowired
    ConsumerFactory consumerFactory;

    @Override
    public ResponseEntity<Void> consume(@Valid ConsumerConfig consumerConfig) {
        System.out.println("Creating consumer for: " + consumerConfig.getProvider());
        Consumer consumer = consumerFactory.getConsumer(consumerConfig.getProvider());
        consumer.consumeMessages(consumerConfig);
        return ResponseEntity.ok().build();
    }
}
