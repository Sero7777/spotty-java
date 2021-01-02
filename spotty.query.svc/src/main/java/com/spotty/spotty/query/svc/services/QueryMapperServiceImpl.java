package com.spotty.spotty.query.svc.services;

import com.spotty.spotty.query.svc.domain.Comment;
import com.spotty.spotty.query.svc.domain.Spot;
import com.spotty.spotty.query.svc.models.CommentDto;
import com.spotty.spotty.query.svc.models.SpotDto;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class QueryMapperServiceImpl implements QueryMapperService {
    @Override
    public SpotDto mapSpotToSpotDto(Spot spot) {
        return SpotDto.builder()
                .id(spot.getId())
                .createdDate(spot.getCreatedDate())
                .lastUpdatedDate(spot.getLastUpdatedDate())
                .title(spot.getTitle())
                .description(spot.getDescription())
                .comments(spot.getComments().stream().map(this::mapCommentToCommentDto).collect(Collectors.toList()))
                .build();
    }

    @Override
    public CommentDto mapCommentToCommentDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .createdDate(comment.getCreatedDate())
                .lastUpdatedDate(comment.getLastUpdatedDate())
                .text(comment.getText())
                .build();
    }
}
