package com.spotty.spotty.spots.svc.controller;

import com.spotty.spotty.spots.svc.models.SpotDto;
import com.spotty.spotty.spots.svc.services.SpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/spots")
@RequiredArgsConstructor
public class GetSpotController {

    private final SpotService spotService;

    @GetMapping("/all")
    public ResponseEntity getAllSpots() {
        return new ResponseEntity<>(spotService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getSpot(@PathVariable String id) {
        SpotDto spotDto = spotService.getById(id);

        return new ResponseEntity<>(spotDto, HttpStatus.OK);
    }
}
