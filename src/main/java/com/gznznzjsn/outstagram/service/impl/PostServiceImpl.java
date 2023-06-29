package com.gznznzjsn.outstagram.service.impl;

import com.gznznzjsn.outstagram.model.node.Post;
import com.gznznzjsn.outstagram.persistence.repository.PostRepository;
import com.gznznzjsn.outstagram.service.PlacementService;
import com.gznznzjsn.outstagram.service.PostService;
import com.gznznzjsn.outstagram.service.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository repository;
    private final PlacementService placementService;
    //    private final TaggingService taggingService;
    private final PublicationService publicationService;


    @Override
    @Transactional
    public void create(UUID accountId, Post post, String placeName, List<String> tagNames) {
        UUID postId = UUID.randomUUID();
        post.setId(postId);
        repository.create(post);
        publicationService.create(accountId, postId);
        placementService.create(postId, placeName);
        for (String tagName : tagNames) {
        }
    }

}
