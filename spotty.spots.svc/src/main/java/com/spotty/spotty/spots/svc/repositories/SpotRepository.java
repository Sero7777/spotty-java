package com.spotty.spotty.spots.svc.repositories;

import com.spotty.spotty.spots.svc.domain.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpotRepository extends JpaRepository<Spot, UUID> {

}
