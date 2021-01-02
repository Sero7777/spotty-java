package com.spotty.spotty.query.svc.controller;

import com.spotty.spotty.query.svc.models.SpotDto;
import com.spotty.spotty.query.svc.services.QueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/query")
@RequiredArgsConstructor
public class GetController {

    private final QueryService queryService;

    @GetMapping("/all")
    public ResponseEntity getAllSpotsWithComments() {
        List<SpotDto> spotDtoList = queryService.getAllSpotsFromDb();
        return new ResponseEntity(spotDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getSpotWithCommentsById(@PathVariable String id) {
        SpotDto spot = queryService.getSpotById(id);
        return new ResponseEntity(spot, HttpStatus.OK);
    }

}
