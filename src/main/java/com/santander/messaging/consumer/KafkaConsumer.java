package com.santander.messaging.consumer;

import lombok.Setter;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.support.Acknowledgment;

import java.util.HashMap;
import java.util.Map;

public class KafkaConsumer extends Consumer {

    private KafkaMessageListenerContainer<String, String> container;
    private Listener messageListener;

    @Override
    protected void initConsumer(Map<String, String> consumerConfig) {
        String topic = consumerConfig.get("topic");
        ContainerProperties containerProps = new ContainerProperties(topic);
        messageListener = new Listener();
        containerProps.setMessageListener(messageListener);
        Map<String, Object > props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, consumerConfig.get("bootstrap"));
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerConfig.get("groupId"));
        DefaultKafkaConsumerFactory<String, String> cf = new DefaultKafkaConsumerFactory<>(props);
        container = new KafkaMessageListenerContainer<>(cf, containerProps);
    }

    @Override
    protected void startConsumer(java.util.function.Consumer<String> consumer) {
        messageListener.setConsumer(consumer);
        container.start();
    }

    private static class Listener implements MessageListener<String, String> {

        @Setter
        private java.util.function.Consumer<String> consumer;

        @Override
        public void onMessage(ConsumerRecord<String, String> record) {
            consumer.accept(record.value());
        }

        @Override
        public void onMessage(ConsumerRecord<String, String> data, Acknowledgment acknowledgment) {

        }

        @Override
        public void onMessage(ConsumerRecord<String, String> data, org.apache.kafka.clients.consumer.Consumer<?, ?> consumer) {

        }

        @Override
        public void onMessage(ConsumerRecord<String, String> data, Acknowledgment acknowledgment, org.apache.kafka.clients.consumer.Consumer<?, ?> consumer) {

        }
    }
}
