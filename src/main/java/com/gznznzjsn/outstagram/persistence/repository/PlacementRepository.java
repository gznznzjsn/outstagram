package com.gznznzjsn.outstagram.persistence.repository;

import com.gznznzjsn.outstagram.model.relationship.Placement;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlacementRepository {

    Long create(Placement placement);

}
