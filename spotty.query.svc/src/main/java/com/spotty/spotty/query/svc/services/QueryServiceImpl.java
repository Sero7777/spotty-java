package com.spotty.spotty.query.svc.services;

import com.spotty.spotty.query.svc.domain.Spot;
import com.spotty.spotty.query.svc.exceptions.SpotNotFoundException;
import com.spotty.spotty.query.svc.models.SpotDto;
import com.spotty.spotty.query.svc.repositories.SpotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QueryServiceImpl implements QueryService {

    private final SpotRepository spotRepository;
    private final QueryMapperService queryMapperService;

    @Override
    public List<SpotDto> getAllSpotsFromDb() {
        List<Spot> spots = spotRepository.findAll();

        return spots.stream().map(queryMapperService::mapSpotToSpotDto).collect(Collectors.toList());
    }

    @Override
    public SpotDto getSpotById(String id) {
        UUID uuid = UUID.fromString(id);

        Spot spotFromDb = spotRepository.findById(uuid).orElseThrow(SpotNotFoundException::new);

        return queryMapperService.mapSpotToSpotDto(spotFromDb);
    }
}
