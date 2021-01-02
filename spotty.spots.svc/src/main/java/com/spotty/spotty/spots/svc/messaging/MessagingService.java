package com.spotty.spotty.spots.svc.messaging;

import com.spotty.spotty.spots.svc.domain.Spot;

public interface MessagingService {
    void publishSpotCreatedEvent(Spot spot);
    void publishSpotUpdatedEvent(Spot spot);
    void publishSpotDeletedEvent(String uuid);
}
