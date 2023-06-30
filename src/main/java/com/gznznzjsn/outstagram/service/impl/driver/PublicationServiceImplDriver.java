package com.gznznzjsn.outstagram.service.impl.driver;

import com.gznznzjsn.outstagram.model.node.Account;
import com.gznznzjsn.outstagram.model.node.Post;
import com.gznznzjsn.outstagram.model.relationship.Publication;
import com.gznznzjsn.outstagram.persistence.repository.PublicationRepository;
import com.gznznzjsn.outstagram.service.PublicationService;
import lombok.RequiredArgsConstructor;
import org.neo4j.driver.TransactionContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PublicationServiceImplDriver implements PublicationService {

    private final PublicationRepository repository;

    @Override
    public void create(UUID accountId, UUID postId, TransactionContext tx) {
        var publication = Publication.builder()
                .id(UUID.randomUUID())
                .source(
                        Account.builder()
                                .id(accountId)
                                .build()
                )
                .target(
                        Post.builder()
                                .id(postId)
                                .build()
                )
                .createdAt(LocalDateTime.now())
                .isPinned(false)
                .build();
        repository.create(publication, tx);
    }

}
