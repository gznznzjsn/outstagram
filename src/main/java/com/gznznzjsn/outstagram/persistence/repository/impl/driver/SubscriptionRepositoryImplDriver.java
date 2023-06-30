package com.gznznzjsn.outstagram.persistence.repository.impl.driver;

import com.gznznzjsn.outstagram.model.exception.InternalLogicException;
import com.gznznzjsn.outstagram.model.node.Account;
import com.gznznzjsn.outstagram.model.relationship.Subscription;
import com.gznznzjsn.outstagram.persistence.repository.SubscriptionRepository;
import org.neo4j.driver.Result;
import org.neo4j.driver.TransactionContext;
import org.neo4j.driver.Values;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public class SubscriptionRepositoryImplDriver implements SubscriptionRepository {

    @Override
    public void create(Subscription subscription, TransactionContext tx) {
        //language=Cypher
        String query = """
                MATCH (a:ACCOUNT), (b:ACCOUNT)
                WHERE a.id = $source_id AND b.id = $target_id
                CREATE (a)-[:SUBSCRIBED {
                        id: $id,
                        created_at: $createdAt
                }]->(b)
                """;
        Result result = tx.run(
                query,
                Values.parameters(
                        "source_id", subscription.getSource().getId().toString(),
                        "target_id", subscription.getTarget().getId().toString(),
                        "id", subscription.getId().toString(),
                        "createdAt", subscription.getCreatedAt().toString()
                )
        );
        int amount = result.consume().counters().nodesDeleted();
        if (amount != 1) {
            throw new InternalLogicException(
                    "This method must create 1 subscription at once,"
                    + " but %d were provided!"
                            .formatted(amount)
            );
        }
    }

    @Override
    public void delete(Subscription subscription, TransactionContext tx) {
        //language=Cypher
        String query = """
                MATCH (a:ACCOUNT) -[s:SUBSCRIBED]-> (b:ACCOUNT)
                WHERE a.id = $source_id AND b.id = $target_id
                DELETE s
                """;
        Result result = tx.run(
                query,
                Values.parameters(
                        "source_id", subscription.getSource().getId().toString(),
                        "target_id", subscription.getTarget().getId().toString()
                )
        );
        int amount = result.consume().counters().nodesDeleted();
        if (amount != 1) {
            throw new InternalLogicException(
                    "This method must delete 1 subscription at once,"
                    + " but %d were provided!"
                            .formatted(amount)
            );
        }
    }

    @Override
    public List<Subscription> readSubscriptions(UUID accountId, TransactionContext tx) {
        //language=Cypher
        String query = """
                MATCH (a:ACCOUNT) -[s:SUBSCRIBED]-> (b:ACCOUNT)
                WHERE a.id = $accountId
                RETURN
                    s.id AS id,
                    a.id AS SOURCE_id,
                    a.name AS SOURCE_name,
                    a.description AS SOURCE_description,
                    a.is_private AS SOURCE_is_private,
                    a.created_at AS SOURCE_created_at,
                    b.id AS TARGET_id,
                    b.name AS TARGET_name,
                    b.description AS TARGET_description,
                    b.is_private AS TARGET_is_private,
                    b.created_at AS TARGET_created_at,
                    s.created_at AS created_at
                """;
        Result result = tx.run(
                query,
                Values.parameters("accountId", accountId.toString())
        );
        return result.list().stream()
                .map(
                        r -> Subscription.builder()
                                .id(UUID.fromString(r.get("id").asString()))
                                .source(
                                        Account.builder()
                                                .id(UUID.fromString(r.get("SOURCE_id").asString()))
                                                .name(r.get("SOURCE_name").asString())
                                                .description(r.get("SOURCE_description").asString())
                                                .isPrivate(r.get("SOURCE_is_private").asBoolean())
                                                .createdAt(LocalDateTime.parse(r.get("SOURCE_created_at").asString()))
                                                .build()
                                )
                                .target(
                                        Account.builder()
                                                .id(UUID.fromString(r.get("TARGET_id").asString()))
                                                .name(r.get("TARGET_name").asString())
                                                .description(r.get("TARGET_description").asString())
                                                .isPrivate(r.get("TARGET_is_private").asBoolean())
                                                .createdAt(LocalDateTime.parse(r.get("TARGET_created_at").asString()))
                                                .build()
                                )
                                .createdAt(LocalDateTime.parse(r.get("created_at").asString()))
                                .build()
                )
                .toList();
    }

    @Override
    public List<Subscription> readSubscribers(UUID accountId, TransactionContext tx) {
        //language=Cypher
        String query = """
                MATCH (a:ACCOUNT) -[s:SUBSCRIBED]-> (b:ACCOUNT)
                WHERE b.id = $accountId
                RETURN
                    s.id AS id,
                    a.id AS SOURCE_id,
                    a.name AS SOURCE_name,
                    a.description AS SOURCE_description,
                    a.is_private AS SOURCE_is_private,
                    a.created_at AS SOURCE_created_at,
                    b.id AS TARGET_id,
                    b.name AS TARGET_name,
                    b.description AS TARGET_description,
                    b.is_private AS TARGET_is_private,
                    b.created_at AS TARGET_created_at,
                    s.created_at AS created_at
                """;
        Result result = tx.run(
                query,
                Values.parameters("accountId", accountId.toString())
        );
        return result.list().stream()
                .map(
                        r -> Subscription.builder()
                                .id(UUID.fromString(r.get("id").asString()))
                                .source(
                                        Account.builder()
                                                .id(UUID.fromString(r.get("SOURCE_id").asString()))
                                                .name(r.get("SOURCE_name").asString())
                                                .description(r.get("SOURCE_description").asString())
                                                .isPrivate(r.get("SOURCE_is_private").asBoolean())
                                                .createdAt(LocalDateTime.parse(r.get("SOURCE_created_at").asString()))
                                                .build()
                                )
                                .target(
                                        Account.builder()
                                                .id(UUID.fromString(r.get("TARGET_id").asString()))
                                                .name(r.get("TARGET_name").asString())
                                                .description(r.get("TARGET_description").asString())
                                                .isPrivate(r.get("TARGET_is_private").asBoolean())
                                                .createdAt(LocalDateTime.parse(r.get("TARGET_created_at").asString()))
                                                .build()
                                )
                                .createdAt(LocalDateTime.parse(r.get("created_at").asString()))
                                .build()
                )
                .toList();
    }

}
