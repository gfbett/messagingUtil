package com.santander.messaging.controllers;

import com.santander.messaging.producers.MessagingProducerFactory;
import com.santander.messaging.producers.Producer;
import io.swagger.api.ProduceApi;
import io.swagger.model.MessagingProducerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
public class ProducerController implements ProduceApi {

    @Autowired
    MessagingProducerFactory messagingProducerFactory;

    @Override
    public ResponseEntity<Void> produce(@Valid MessagingProducerConfig producerConfig) {
        log.info("Creating producer for: " + producerConfig.getProvider());
        Producer producer = messagingProducerFactory.getProducer(producerConfig.getProvider());
        producer.produceMessages(producerConfig);
        return ResponseEntity.ok().build();
    }
}
