package com.gznznzjsn.outstagram.service.impl.driver;

import com.gznznzjsn.outstagram.model.node.Post;
import com.gznznzjsn.outstagram.persistence.repository.Neo4jCustomDriver;
import com.gznznzjsn.outstagram.persistence.repository.PostRepository;
import com.gznznzjsn.outstagram.service.PlacementService;
import com.gznznzjsn.outstagram.service.PostService;
import com.gznznzjsn.outstagram.service.PublicationService;
import lombok.RequiredArgsConstructor;
import org.neo4j.driver.Session;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImplDriver implements PostService {

    private final Neo4jCustomDriver driver;
    private final PostRepository repository;
    private final PlacementService placementService;
    //    private final TaggingService taggingService;
    private final PublicationService publicationService;


    @Override
    public void create(UUID accountId, Post post, String placeName, List<String> tagNames) {
        try (Session session = driver.getSession()) {
            session.executeWriteWithoutResult(tx -> {
                UUID postId = UUID.randomUUID();
                post.setId(postId);
                repository.create(post, tx);
                publicationService.create(accountId, postId, tx);
                placementService.create(postId, placeName, tx);
                for (String tagName : tagNames) {
                }
            });
        }
    }

}
