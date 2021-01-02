package com.spotty.spotty.query.svc.models;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Builder
@Data
public class SpotDto {
    private UUID id;
    private Timestamp createdDate;
    private Timestamp lastUpdatedDate;
    private String title;
    private String description;
    private List<CommentDto> comments;
}