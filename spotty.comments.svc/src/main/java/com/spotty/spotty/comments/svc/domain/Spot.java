package com.spotty.spotty.comments.svc.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Spot {

    @Id
    @Column(length = 36, updatable = false, nullable = false)
    private UUID id;

    @Column(updatable = false)
    private Timestamp createdDate;

    private Timestamp lastUpdatedDate;

    private String title;
    private String description;

    @OneToMany(mappedBy = "spot", orphanRemoval = true, cascade = CascadeType.ALL)
    List<Comment> comments;
}
