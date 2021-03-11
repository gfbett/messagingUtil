package com.santander.messaging.producers;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

public class KafkaProducer extends Producer {

    private KafkaTemplate<String, String> template;
    private String topic;

    @Override
    protected void initProducer(Map<String, String> producerConfig) {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, producerConfig.get("bootstrap"));
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        putKafkaProps(props, producerConfig);
        ProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<>(props);
        template = new KafkaTemplate<>(producerFactory);
        topic = producerConfig.get("topic");
    }

    private void putKafkaProps(Map<String, Object> props, Map<String, String> producerConfig) {
        String prefix = "kafka.";
        producerConfig.keySet().stream()
                .filter(key -> key.startsWith(prefix))
                .forEach(key -> {
                    String value = producerConfig.get(key);
                    String propkey = key.substring(prefix.length());
                    props.put(propkey, value);
                    System.out.println("Adding property " + propkey );
                });
    }

    @Override
    protected void sendMessage(String message) {
        template.send(topic, message);
    }
}
