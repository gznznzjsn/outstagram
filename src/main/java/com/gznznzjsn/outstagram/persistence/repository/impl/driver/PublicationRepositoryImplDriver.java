package com.gznznzjsn.outstagram.persistence.repository.impl.driver;

import com.gznznzjsn.outstagram.model.exception.InternalLogicException;
import com.gznznzjsn.outstagram.model.relationship.Publication;
import com.gznznzjsn.outstagram.persistence.repository.PublicationRepository;
import org.neo4j.driver.Result;
import org.neo4j.driver.TransactionContext;
import org.neo4j.driver.Values;
import org.springframework.stereotype.Repository;

@Repository
public class PublicationRepositoryImplDriver implements PublicationRepository {

    @Override
    public void create(Publication publication, TransactionContext tx) {
        //language=Cypher
        String query = """
                MATCH (a:ACCOUNT), (b:POST)
                WHERE a.id = $source_id AND b.id = $target_id
                CREATE (a)-[:PUBLISHED {
                        id: $id,
                        created_at: $createdAt,
                        is_pinned: $isPinned
                }]->(b);
                        """;
        Result result = tx.run(
                query,
                Values.parameters(
                        "source_id", publication.getSource().getId().toString(),
                        "target_id", publication.getTarget().getId().toString(),
                        "id", publication.getId().toString(),
                        "createdAt", publication.getCreatedAt(),
                        "isPinned", publication.getIsPinned()
                )
        );
        int amount = result.consume().counters().relationshipsCreated();
        if (amount != 1) {
            throw new InternalLogicException(
                    "This method must create 1 publication at once,"
                    + " but %d were provided!"
                            .formatted(amount)
            );
        }
    }

}
