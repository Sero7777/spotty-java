package com.spotty.spotty.comments.svc.services;

import com.spotty.spotty.comments.svc.models.CommentDto;
import com.spotty.spotty.comments.svc.models.CommentUpdateDto;

import java.util.List;

public interface CommentService {
    CommentDto saveNewComment(CommentDto commentDto);
    CommentDto updateComment(CommentUpdateDto commentDto);
    void deleteComment(String id);
    CommentDto getComment(String id);
    List<CommentDto> getAllComments();
}
