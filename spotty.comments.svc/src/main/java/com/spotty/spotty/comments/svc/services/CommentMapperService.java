package com.spotty.spotty.comments.svc.services;

import com.spotty.spotty.comments.svc.domain.Comment;
import com.spotty.spotty.comments.svc.models.CommentDto;

public interface CommentMapperService {
    CommentDto mapCommentToCommentDto(Comment comment);
}
