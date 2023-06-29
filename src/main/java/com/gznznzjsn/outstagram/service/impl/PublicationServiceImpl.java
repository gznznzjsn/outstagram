package com.gznznzjsn.outstagram.service.impl;

import com.gznznzjsn.outstagram.model.exception.InternalLogicException;
import com.gznznzjsn.outstagram.model.node.Account;
import com.gznznzjsn.outstagram.model.node.Post;
import com.gznznzjsn.outstagram.model.relationship.Publication;
import com.gznznzjsn.outstagram.persistence.repository.PublicationRepository;
import com.gznznzjsn.outstagram.service.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository repository;

    @Override
    @Transactional
    public void create(UUID accountId, UUID postId) {
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
        Long amount = repository.create(publication);
        if (amount != 1) {
            throw new InternalLogicException(
                    "This method must create 1 publication at once,"
                    + " but %d were provided!".formatted(amount)
            );
        }
    }

}
