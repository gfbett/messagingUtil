package com.santander.messaging.producers;

import com.santander.messaging.utils.KafkaUtils;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

import static com.santander.messaging.utils.KafkaUtils.addTrustStore;
import static com.santander.messaging.utils.KafkaUtils.putKafkaProps;

public class KafkaProducer extends Producer {

    private KafkaTemplate<String, String> template;
    private String topic;

    @Override
    protected void initProducer(Map<String, String> producerConfig) {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, producerConfig.get("bootstrap"));
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        addTrustStore(producerConfig, props);
        putKafkaProps(props, producerConfig);
        ProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<>(props);
        template = new KafkaTemplate<>(producerFactory);
        topic = producerConfig.get("topic");
    }



    @Override
    protected void sendMessage(String message) {
        template.send(topic, message);
    }
}
