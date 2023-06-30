package com.gznznzjsn.outstagram.persistence.repository;

import com.gznznzjsn.outstagram.model.node.Place;
import org.neo4j.driver.TransactionContext;

import java.util.Optional;

public interface PlaceRepository {

    Optional<Place> findByName(String name, TransactionContext tx);

    void create(Place place, TransactionContext tx);

}
