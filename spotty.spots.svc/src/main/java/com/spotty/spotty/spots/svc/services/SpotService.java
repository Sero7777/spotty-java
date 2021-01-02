package com.spotty.spotty.spots.svc.services;

import com.spotty.spotty.spots.svc.models.SpotDto;
import com.spotty.spotty.spots.svc.models.SpotUpdateDto;

import java.util.List;

public interface SpotService {
    SpotDto saveNewSpot(SpotDto spotDto);
    SpotDto getById(String id);
    List<SpotDto> getAll();
    SpotDto updateSpot(SpotUpdateDto spotDto);
    void deleteSpot(String id);
}
