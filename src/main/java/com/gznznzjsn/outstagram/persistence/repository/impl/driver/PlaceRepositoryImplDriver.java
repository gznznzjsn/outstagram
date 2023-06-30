package com.gznznzjsn.outstagram.persistence.repository.impl.driver;

import com.gznznzjsn.outstagram.model.node.Place;
import com.gznznzjsn.outstagram.persistence.repository.PlaceRepository;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.TransactionContext;
import org.neo4j.driver.Values;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class PlaceRepositoryImplDriver implements PlaceRepository {

    @Override
    public Optional<Place> findByName(String name, TransactionContext tx) {
        //language=Cypher
        String query = """
                MATCH (a:PLACE)
                WHERE a.name = $name
                RETURN
                    a.id AS id,
                    a.name AS name
                """;
        Result run = tx.run(
                query,
                Values.parameters("name", name)
        );
        if (!run.hasNext()) {
            return Optional.empty();
        }
        Record record = run.single();
        return Optional.of(
                Place.builder()
                        .id(UUID.fromString(record.get("id").asString()))
                        .name(record.get("name").asString())
                        .build()
        );
    }

    @Override
    public void create(Place place, TransactionContext tx) {
        //language=Cypher
        String query = """
                 CREATE (:PLACE {
                            id: $id,
                            name: $name
                        });
                """;
        tx.run(
                query,
                Values.parameters(
                        "id", place.getId().toString(),
                        "name", place.getName()
                )
        );
    }

}
