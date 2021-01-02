package com.spotty.spotty.spots.svc.models;

import com.spotty.spotty.spots.svc.annotations.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpotUpdateDto {

    @UUID(message = "The provided uuid was not valid.")
    private String id;

    @Null(message = "There should be no creation date when creating a spot.")
    private Timestamp createdDate;

    @Null(message = "There should be no updated date when creating a spot.")
    private Timestamp lastUpdatedDate;

    @Size(min = 5, max = 50, message = "The title should have a length between 5 and 50 characters.")
    private String title;

    @Size(min = 20, max = 250, message = "The description should have a length between 20 and 250 characters.")
    private String description;
}
