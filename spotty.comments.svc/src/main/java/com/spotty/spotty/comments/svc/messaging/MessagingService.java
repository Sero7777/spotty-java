package com.spotty.spotty.comments.svc.messaging;

import com.spotty.spotty.comments.svc.domain.Comment;
import com.spotty.spotty.comments.svc.models.CommentDto;

public interface MessagingService {
    void publishCommentCreatedEvent(CommentDto comment);
    void publishCommentUpdatedEvent(CommentDto comment);
    void publishCommentDeletedEvent(String uuid);
}
