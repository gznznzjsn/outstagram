package com.gznznzjsn.outstagram.persistence.repository;

import com.gznznzjsn.outstagram.model.relationship.Publication;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PublicationRepository {

    Long create(Publication publication);

}
