package com.spotty.spotty.comments.svc.models;

import com.spotty.spotty.comments.svc.annotations.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CommentUpdateDto {

    @UUID
    private String id;

    @Size(min = 36, max = 36)
    private String spotId;

    @Size(min = 5, max = 50)
    private String text;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp lastUpdatedDate;
}
