package com.gznznzjsn.outstagram.persistence.repository.impl.driver;

import com.gznznzjsn.outstagram.model.node.Tag;
import com.gznznzjsn.outstagram.persistence.repository.TagRepository;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.TransactionContext;
import org.neo4j.driver.Values;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class TagRepositoryImplDriver implements TagRepository {

    @Override
    public Optional<Tag> findByName(
            final String name,
            final TransactionContext tx
    ) {
        //language=Cypher
        String query = """
                MATCH (a:TAG)
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
                Tag.builder()
                        .id(UUID.fromString(record.get("id").asString()))
                        .name(record.get("name").asString())
                        .build()
        );
    }

    @Override
    public void create(Tag tag, TransactionContext tx) {
        //language=Cypher
        String query = """
                 CREATE (:TAG {
                            id: $id,
                            name: $name
                        });
                """;
        tx.run(
                query,
                Values.parameters(
                        "id", tag.getId().toString(),
                        "name", tag.getName()
                )
        );
    }

}
