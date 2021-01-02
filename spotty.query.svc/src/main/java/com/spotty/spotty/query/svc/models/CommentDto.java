package com.spotty.spotty.query.svc.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private UUID id;
    private String text;
    private Timestamp createdDate;
    private Timestamp lastUpdatedDate;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String spotId;
}
