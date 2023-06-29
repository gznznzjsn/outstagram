package com.gznznzjsn.outstagram.service.impl;

import com.gznznzjsn.outstagram.model.node.Place;
import com.gznznzjsn.outstagram.persistence.repository.PlaceRepository;
import com.gznznzjsn.outstagram.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository repository;

    @Override
    @Transactional
    public Place retrieveOrCreateAndRetrieve(String name) {
        return repository.findByName(name)
                .orElseGet(() -> {
                    Place place = new Place(UUID.randomUUID(), name);
                    repository.create(place);
                    return place;
                });
    }

}
