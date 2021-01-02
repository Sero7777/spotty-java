package com.spotty.spotty.comments.svc.services;

import com.spotty.spotty.comments.svc.domain.Comment;
import com.spotty.spotty.comments.svc.models.CommentDto;
import org.springframework.stereotype.Service;

@Service
public class CommentMapperServiceImpl implements CommentMapperService {
    @Override
    public CommentDto mapCommentToCommentDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId().toString())
                .createdDate(comment.getCreatedDate())
                .lastUpdatedDate(comment.getLastUpdatedDate())
                .text(comment.getText())
                .spotId(comment.getSpot().getId().toString())
                .build();
    }
}
