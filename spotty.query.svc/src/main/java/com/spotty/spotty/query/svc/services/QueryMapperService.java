package com.spotty.spotty.query.svc.services;

import com.spotty.spotty.query.svc.domain.Comment;
import com.spotty.spotty.query.svc.domain.Spot;
import com.spotty.spotty.query.svc.models.CommentDto;
import com.spotty.spotty.query.svc.models.SpotDto;

public interface QueryMapperService {
    SpotDto mapSpotToSpotDto(Spot spot);
    CommentDto mapCommentToCommentDto(Comment comment);
}
