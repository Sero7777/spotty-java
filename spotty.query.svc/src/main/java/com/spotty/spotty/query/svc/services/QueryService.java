package com.spotty.spotty.query.svc.services;

import com.spotty.spotty.query.svc.models.SpotDto;

import java.util.List;

public interface QueryService {

    List<SpotDto> getAllSpotsFromDb();
    SpotDto getSpotById(String id);
}
