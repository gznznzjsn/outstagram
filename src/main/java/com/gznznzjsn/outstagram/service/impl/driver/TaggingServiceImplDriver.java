package com.gznznzjsn.outstagram.service.impl.driver;

import com.gznznzjsn.outstagram.model.node.Post;
import com.gznznzjsn.outstagram.model.node.Tag;
import com.gznznzjsn.outstagram.model.relationship.Tagging;
import com.gznznzjsn.outstagram.persistence.repository.TaggingRepository;
import com.gznznzjsn.outstagram.service.TagService;
import com.gznznzjsn.outstagram.service.TaggingService;
import lombok.RequiredArgsConstructor;
import org.neo4j.driver.TransactionContext;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaggingServiceImplDriver implements TaggingService {

    private final TagService service;
    private final TaggingRepository repository;

    @Override
    public void create(UUID postId, String tagName, TransactionContext tx) {
        Tag tag = service.retrieveOrCreateAndRetrieve(tagName, tx);
        var tagging = Tagging.builder()
                .id(UUID.randomUUID())
                .source(Post.builder()
                        .id(postId)
                        .build())
                .target(tag)
                .build();
        repository.create(tagging, tx);
    }

}
