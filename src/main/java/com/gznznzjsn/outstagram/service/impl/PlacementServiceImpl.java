package com.gznznzjsn.outstagram.service.impl;

import com.gznznzjsn.outstagram.model.exception.InternalLogicException;
import com.gznznzjsn.outstagram.model.node.Place;
import com.gznznzjsn.outstagram.model.node.Post;
import com.gznznzjsn.outstagram.model.relationship.Placement;
import com.gznznzjsn.outstagram.persistence.repository.PlacementRepository;
import com.gznznzjsn.outstagram.service.PlaceService;
import com.gznznzjsn.outstagram.service.PlacementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlacementServiceImpl implements PlacementService {

    private final PlacementRepository repository;
    private final PlaceService placeService;

    @Override
    @Transactional
    public void create(UUID postId, String placeName) {
        Place place = placeService.retrieveOrCreateAndRetrieve(placeName);
        var placement = Placement.builder()
                .id(UUID.randomUUID())
                .source(
                        Post.builder()
                                .id(postId)
                                .build()
                )
                .target(place)
                .build();
        Long amount = repository.create(placement);
        if (amount != 1) {
            throw new InternalLogicException(
                    "This method must create 1 placement at once,"
                    + " but %d were provided!"
                            .formatted(amount)
            );
        }
    }

}
