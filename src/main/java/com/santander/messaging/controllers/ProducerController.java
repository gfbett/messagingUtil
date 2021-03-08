package com.santander.messaging.controllers;

import com.santander.messaging.producers.Producer;
import com.santander.messaging.producers.ProducerFactory;
import io.swagger.api.ProduceApi;
import io.swagger.model.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ProducerController implements ProduceApi {

    @Autowired
    ProducerFactory producerFactory;

    @Override
    public ResponseEntity<Void> produce(@Valid ProducerConfig producerConfig) {
        System.out.println("Creating producer for: " + producerConfig.getProvider());
        Producer producer = producerFactory.getProducer(producerConfig.getProvider());
        producer.produceMessages(producerConfig);
        return ResponseEntity.ok().build();
    }
}
