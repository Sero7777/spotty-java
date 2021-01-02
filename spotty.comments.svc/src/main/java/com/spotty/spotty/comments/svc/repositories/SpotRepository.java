package com.spotty.spotty.comments.svc.repositories;

import com.spotty.spotty.comments.svc.domain.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpotRepository extends JpaRepository<Spot, UUID> {
}
