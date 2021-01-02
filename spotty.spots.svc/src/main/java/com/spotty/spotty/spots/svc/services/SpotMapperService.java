package com.spotty.spotty.spots.svc.services;

import com.spotty.spotty.spots.svc.domain.Spot;
import com.spotty.spotty.spots.svc.models.SpotDto;

public interface SpotMapperService {
    Spot mapSpotDtoToSpot(SpotDto spotDto);
    SpotDto mapSpotToSpotDto(Spot spot);
}
