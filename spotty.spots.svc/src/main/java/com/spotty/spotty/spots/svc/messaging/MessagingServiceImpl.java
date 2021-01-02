package com.spotty.spotty.spots.svc.messaging;

import com.spotty.spotty.spots.svc.domain.Spot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MessagingServiceImpl implements MessagingService {

    private final KafkaTemplate<String, Spot> kafkaTemplate;
    private final KafkaTemplate<String, String> kafkaTemplateSpotDeleteEvent;

    @Value("${kafka.topic.spotcreated}")
    private String spotCreatedTopic;

    @Value("${kafka.topic.spotupdated}")
    private String spotUpdatedTopic;

    @Value("${kafka.topic.spotdeleted}")
    private String spotDeletedTopic;

    @Override
    public void publishSpotCreatedEvent(Spot spot) {
        Message<Spot> message = MessageBuilder.withPayload(spot).setHeader(KafkaHeaders.TOPIC, spotCreatedTopic).build();
        kafkaTemplate.send(message);
    }

    @Override
    public void publishSpotUpdatedEvent(Spot spot) {
        Message<Spot> message = MessageBuilder.withPayload(spot).setHeader(KafkaHeaders.TOPIC, spotUpdatedTopic).build();
        kafkaTemplate.send(message);
    }

    @Override
    public void publishSpotDeletedEvent(String uuid) {
        Message<String> message = MessageBuilder.withPayload(uuid).setHeader(KafkaHeaders.TOPIC, spotDeletedTopic).build();
        kafkaTemplateSpotDeleteEvent.send(message);
    }
}
