package com.gznznzjsn.outstagram.persistence.repository;

import com.gznznzjsn.outstagram.model.node.Place;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface PlaceRepository {

    Optional<Place> findByName(String name);

    void create(Place place);

}
