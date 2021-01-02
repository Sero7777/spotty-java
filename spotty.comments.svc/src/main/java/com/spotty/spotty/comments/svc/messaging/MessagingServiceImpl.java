package com.spotty.spotty.comments.svc.messaging;

import com.spotty.spotty.comments.svc.domain.Comment;
import com.spotty.spotty.comments.svc.models.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MessagingServiceImpl implements MessagingService {

    private final KafkaTemplate<String, CommentDto> kafkaTemplate;
    private final KafkaTemplate<String, String> kafkaTemplateCommentDeleteEvent;

    @Value("${kafka.topic.commentcreated}")
    private String commentCreatedTopic;

    @Value("${kafka.topic.commentupdated}")
    private String commentUpdatedTopic;

    @Value("${kafka.topic.commentdeleted}")
    private String commentDeletedTopic;

    @Override
    public void publishCommentCreatedEvent(CommentDto comment) {
        Message<CommentDto> message = MessageBuilder.withPayload(comment).setHeader(KafkaHeaders.TOPIC, commentCreatedTopic).build();
        kafkaTemplate.send(message);
    }

    @Override
    public void publishCommentUpdatedEvent(CommentDto comment) {
        Message<CommentDto> message = MessageBuilder.withPayload(comment).setHeader(KafkaHeaders.TOPIC, commentUpdatedTopic).build();
        kafkaTemplate.send(message);
    }

    @Override
    public void publishCommentDeletedEvent(String uuid) {
        Message<String> message = MessageBuilder.withPayload(uuid).setHeader(KafkaHeaders.TOPIC, commentDeletedTopic).build();
        kafkaTemplateCommentDeleteEvent.send(message);
    }
}
