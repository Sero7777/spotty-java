package com.spotty.spotty.comments.svc.messaging;

import com.spotty.spotty.comments.svc.domain.Spot;
import com.spotty.spotty.comments.svc.exceptions.SpotNotFoundException;
import com.spotty.spotty.comments.svc.repositories.CommentRepository;
import com.spotty.spotty.comments.svc.repositories.SpotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final SpotRepository spotRepository;
    private final CommentRepository commentRepository;

    @KafkaListener(topics = "${kafka.topic.spotcreated}", groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    @KafkaHandler
    public void spotCreatedConsumer(@Payload Spot spot, @Headers MessageHeaders headers) {
        spotRepository.save(spot);
    }

    @KafkaListener(topics = "${kafka.topic.spotupdated}", groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    @KafkaHandler
    public void spotUpdatedConsumer(@Payload Spot spot, @Headers MessageHeaders headers) {
        Spot spotFromDb = spotRepository.findById(spot.getId()).orElseThrow(SpotNotFoundException::new);
        spotRepository.save(updateSpotProperties(spotFromDb, spot));
    }

    @KafkaListener(topics = "${kafka.topic.spotdeleted}", groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactoryDeleteSpotEvents")
    @KafkaHandler
    public void spotDeletedConsumer(@Payload String uuid, @Headers MessageHeaders headers) {
        UUID uuid1 = UUID.fromString(uuid);
        Spot spotFromDb = spotRepository.findById(uuid1).orElseThrow(SpotNotFoundException::new);
        spotRepository.delete(spotFromDb);
    }

    private Spot updateSpotProperties(Spot spotFromDb, Spot newSpot) {
        spotFromDb.setTitle(newSpot.getTitle());
        spotFromDb.setDescription(newSpot.getDescription());
        spotFromDb.setLastUpdatedDate(newSpot.getLastUpdatedDate());
        return spotFromDb;
    }
}
