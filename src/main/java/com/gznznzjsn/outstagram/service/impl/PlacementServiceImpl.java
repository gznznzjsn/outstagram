package com.gznznzjsn.outstagram.service.impl;

import com.gznznzjsn.outstagram.model.node.Place;
import com.gznznzjsn.outstagram.model.node.Post;
import com.gznznzjsn.outstagram.model.relationship.Placement;
import com.gznznzjsn.outstagram.persistence.repository.PlacementRepository;
import com.gznznzjsn.outstagram.service.PlaceService;
import com.gznznzjsn.outstagram.service.PlacementService;
import lombok.RequiredArgsConstructor;
import org.neo4j.driver.TransactionContext;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlacementServiceImpl implements PlacementService {

    private final PlacementRepository repository;
    private final PlaceService placeService;

    @Override
    public void create(UUID postId, String placeName, TransactionContext tx) {
        Place place = placeService.retrieveOrCreateAndRetrieve(placeName, tx);
        var placement = Placement.builder()
                .id(UUID.randomUUID())
                .source(
                        Post.builder()
                                .id(postId)
                                .build()
                )
                .target(place)
                .build();
        repository.create(placement, tx);
    }

}
