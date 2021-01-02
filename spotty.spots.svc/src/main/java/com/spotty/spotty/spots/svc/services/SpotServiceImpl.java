package com.spotty.spotty.spots.svc.services;

import com.spotty.spotty.spots.svc.exceptions.SpotNotFoundException;
import com.spotty.spotty.spots.svc.messaging.MessagingService;
import com.spotty.spotty.spots.svc.repositories.SpotRepository;
import com.spotty.spotty.spots.svc.domain.Spot;
import com.spotty.spotty.spots.svc.models.SpotDto;
import com.spotty.spotty.spots.svc.models.SpotUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SpotServiceImpl implements SpotService {

    private final SpotRepository spotRepository;
    private final SpotMapperService spotMapperService;
    private final MessagingService messagingService;

    @Override
    public SpotDto saveNewSpot(SpotDto spotDto) {
        Spot savedSpot = spotRepository.save(spotMapperService.mapSpotDtoToSpot(spotDto));

        messagingService.publishSpotCreatedEvent(savedSpot);

        return spotMapperService.mapSpotToSpotDto(savedSpot);
    }

    @Override
    public SpotDto getById(String id) {
        UUID uuid = UUID.fromString(id);
        Spot spot = spotRepository.findById(uuid).orElseThrow(SpotNotFoundException::new);

        return spotMapperService.mapSpotToSpotDto(spot);
    }

    @Override
    public List<SpotDto> getAll() {
        List<Spot> spots = spotRepository.findAll();
        return spots.stream().map(spotMapperService::mapSpotToSpotDto).collect(Collectors.toList());
    }

    @Override
    public SpotDto updateSpot(SpotUpdateDto spotDto) {
        UUID uuid = UUID.fromString(spotDto.getId());
        Spot spot = spotRepository.findById(uuid).orElseThrow(SpotNotFoundException::new);

        updateSpotProperties(spotDto, spot);
        Spot updatedSpot = spotRepository.save(spot);

        messagingService.publishSpotUpdatedEvent(updatedSpot);

        return spotMapperService.mapSpotToSpotDto(updatedSpot);
    }

    @Override
    public void deleteSpot(String id) {
        UUID uuid = UUID.fromString(id);
        Spot spotFromDb = spotRepository.findById(uuid).orElseThrow(SpotNotFoundException::new);

        spotRepository.delete(spotFromDb);

        messagingService.publishSpotDeletedEvent(id);
    }

    private void updateSpotProperties(SpotUpdateDto spotDto, Spot spot) {
        spot.setTitle(spotDto.getTitle());
        spot.setDescription(spotDto.getDescription());
    }
}
