package com.spotty.spotty.query.svc.messaging;

import com.spotty.spotty.query.svc.domain.Comment;
import com.spotty.spotty.query.svc.domain.Spot;
import com.spotty.spotty.query.svc.exceptions.CommentNotFoundException;
import com.spotty.spotty.query.svc.exceptions.SpotNotFoundException;
import com.spotty.spotty.query.svc.models.CommentDto;
import com.spotty.spotty.query.svc.repositories.CommentRepository;
import com.spotty.spotty.query.svc.repositories.SpotRepository;
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
            containerFactory = "kafkaListenerContainerFactoryCreateUpdateSpotEvents")
    @KafkaHandler
    public void spotCreatedConsumer(@Payload Spot spot, @Headers MessageHeaders headers) {
        spotRepository.save(spot);
    }

    @KafkaListener(topics = "${kafka.topic.spotupdated}", groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactoryCreateUpdateSpotEvents")
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

    @KafkaListener(topics = "${kafka.topic.commentcreated}", groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactoryCreateUpdateCommentEvents")
    @KafkaHandler
    public void commentCreatedConsumer(@Payload CommentDto comment, @Headers MessageHeaders headers) {
        UUID uuid = UUID.fromString(comment.getSpotId());
        Spot spotFromDb = spotRepository.findById(uuid).orElseThrow(SpotNotFoundException::new);
        commentRepository.save(commentDtoToCommentMapper(comment, spotFromDb));
    }

    @KafkaListener(topics = "${kafka.topic.commentupdated}", groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactoryCreateUpdateCommentEvents")
    @KafkaHandler
    public void commentUpdatedConsumer(@Payload CommentDto comment, @Headers MessageHeaders headers) {
        Comment commentFromDb = commentRepository.findById(comment.getId()).orElseThrow(CommentNotFoundException::new);
        commentRepository.save(updateCommentProperties(commentFromDb, comment));
    }

    @KafkaListener(topics = "${kafka.topic.commentdeleted}", groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactoryDeleteCommentEvents")
    @KafkaHandler
    public void commentDeletedConsumer(@Payload String uuid, @Headers MessageHeaders headers) {
        UUID uuid1 = UUID.fromString(uuid);
        Comment commentFromDb = commentRepository.findById(uuid1).orElseThrow(CommentNotFoundException::new);
        commentRepository.delete(commentFromDb);
    }

    private Spot updateSpotProperties(Spot spotFromDb, Spot newSpot) {
        spotFromDb.setTitle(newSpot.getTitle());
        spotFromDb.setDescription(newSpot.getDescription());
        spotFromDb.setLastUpdatedDate(newSpot.getLastUpdatedDate());
        return spotFromDb;
    }

    private Comment updateCommentProperties(Comment commentFromDb, CommentDto comment) {
        commentFromDb.setText(comment.getText());
        return commentFromDb;
    }

    private Comment commentDtoToCommentMapper(CommentDto commentDto, Spot spot) {
        return Comment.builder()
                .id(commentDto.getId())
                .createdDate(commentDto.getCreatedDate())
                .lastUpdatedDate(commentDto.getLastUpdatedDate())
                .spot(spot)
                .text(commentDto.getText())
                .build();
    }
}
