package com.spotty.spotty.query.svc.messaging;

import com.spotty.spotty.query.svc.domain.Comment;
import com.spotty.spotty.query.svc.domain.Spot;
import com.spotty.spotty.query.svc.models.CommentDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        return properties;
    }

    @Bean
    public ConsumerFactory<String, Spot> consumerFactoryCreateUpdateSpotEvents() {
        return new DefaultKafkaConsumerFactory<>(
                consumerConfigs(),
                new StringDeserializer(),
                new JsonDeserializer<>(Spot.class, false)
        );
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactoryDeleteSpotEvents() {
        return new DefaultKafkaConsumerFactory<>(
                consumerConfigs(),
                new StringDeserializer(),
                new JsonDeserializer<>(String.class, false)
        );
    }

    @Bean("kafkaListenerContainerFactoryCreateUpdateSpotEvents")
    public ConcurrentKafkaListenerContainerFactory<String, Spot> kafkaListenerContainerFactoryCreateUpdateSpotEvents() {
        ConcurrentKafkaListenerContainerFactory<String, Spot> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryCreateUpdateSpotEvents());
        return factory;
    }

    @Bean("kafkaListenerContainerFactoryDeleteSpotEvents")
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactoryDeleteSpotEvents() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryDeleteSpotEvents());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, CommentDto> consumerFactoryCreateUpdateCommentEvents() {
        return new DefaultKafkaConsumerFactory<>(
                consumerConfigs(),
                new StringDeserializer(),
                new JsonDeserializer<>(CommentDto.class, false)
        );
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactoryDeleteCommentEvents() {
        return new DefaultKafkaConsumerFactory<>(
                consumerConfigs(),
                new StringDeserializer(),
                new JsonDeserializer<>(String.class, false)
        );
    }

    @Bean("kafkaListenerContainerFactoryCreateUpdateCommentEvents")
    public ConcurrentKafkaListenerContainerFactory<String, CommentDto> kafkaListenerContainerFactoryCreateUpdateCommentEvents() {
        ConcurrentKafkaListenerContainerFactory<String, CommentDto> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryCreateUpdateCommentEvents());
        return factory;
    }

    @Bean("kafkaListenerContainerFactoryDeleteCommentEvents")
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactoryDeleteCommentEvents() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryDeleteCommentEvents());
        return factory;
    }
}
