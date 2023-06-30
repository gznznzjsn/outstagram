package com.gznznzjsn.outstagram.service.impl;

import com.gznznzjsn.outstagram.model.node.Place;
import com.gznznzjsn.outstagram.persistence.repository.PlaceRepository;
import com.gznznzjsn.outstagram.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.neo4j.driver.TransactionContext;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository repository;

    @Override
    public Place retrieveOrCreateAndRetrieve(String name, TransactionContext tx) {
        return repository.findByName(name, tx)
                .orElseGet(() -> {
                    Place place = new Place(UUID.randomUUID(), name);
                    repository.create(place, tx);
                    return place;
                });
    }

}
