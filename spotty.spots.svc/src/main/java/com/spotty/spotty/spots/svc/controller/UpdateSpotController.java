package com.spotty.spotty.spots.svc.controller;

import com.spotty.spotty.spots.svc.models.SpotDto;
import com.spotty.spotty.spots.svc.models.SpotUpdateDto;
import com.spotty.spotty.spots.svc.services.SpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/spots")
@RequiredArgsConstructor
public class UpdateSpotController {

    private final SpotService spotService;

    @PutMapping
    public ResponseEntity updateSpot(@RequestBody @Valid SpotUpdateDto spotDto){
        SpotDto spotDto1 = spotService.updateSpot(spotDto);

        if (spotDto1 != null) {
            return new ResponseEntity(spotDto1, HttpStatus.OK);
        }

        return new ResponseEntity("The spot could not be updated.", HttpStatus.BAD_REQUEST);
    }
}
