package com.spotty.spotty.spots.svc.controller;

import com.spotty.spotty.spots.svc.models.SpotDto;
import com.spotty.spotty.spots.svc.services.SpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/spots")
@RequiredArgsConstructor
public class CreateSpotController {

    private final SpotService spotService;

    @PostMapping
    public ResponseEntity createNewSpot(@RequestBody @Valid SpotDto spotDto){
        SpotDto savedSpotDto = spotService.saveNewSpot(spotDto);

        if (savedSpotDto != null) {
            return new ResponseEntity(savedSpotDto, HttpStatus.CREATED);
        }

        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
