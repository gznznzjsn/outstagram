package com.gznznzjsn.outstagram.service;

import com.gznznzjsn.outstagram.model.node.Post;

import java.util.List;
import java.util.UUID;

public interface PostService {

    void create(UUID accountId, Post post, String placeName, List<String> tagNames);

}
