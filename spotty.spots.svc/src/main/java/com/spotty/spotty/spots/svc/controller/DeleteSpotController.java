package com.spotty.spotty.spots.svc.controller;

import com.spotty.spotty.spots.svc.services.SpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/spots")
@RequiredArgsConstructor
public class DeleteSpotController {

    private final SpotService spotService;

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSpot(@PathVariable String id) {
        spotService.deleteSpot(id);

        return new ResponseEntity(HttpStatus.OK);
    }
}
