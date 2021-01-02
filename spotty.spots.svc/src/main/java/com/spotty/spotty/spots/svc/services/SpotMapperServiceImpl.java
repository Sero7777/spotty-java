package com.spotty.spotty.spots.svc.services;

import com.spotty.spotty.spots.svc.domain.Spot;
import com.spotty.spotty.spots.svc.models.SpotDto;
import org.springframework.stereotype.Service;

@Service
public class SpotMapperServiceImpl implements SpotMapperService {

    @Override
    public Spot mapSpotDtoToSpot(SpotDto spotDto) {
        return Spot.builder()
                .title(spotDto.getTitle())
                .description(spotDto.getDescription())
                .build();
    }

    @Override
    public SpotDto mapSpotToSpotDto(Spot spot) {
        return SpotDto.builder()
                .id(spot.getId())
                .createdDate(spot.getCreatedDate())
                .lastUpdatedDate(spot.getLastUpdatedDate())
                .title(spot.getTitle())
                .description(spot.getDescription())
                .build();
    }
}
