package com.gznznzjsn.outstagram.web.controller;

import com.gznznzjsn.outstagram.model.node.Post;
import com.gznznzjsn.outstagram.service.PostService;
import com.gznznzjsn.outstagram.web.dto.PostDto;
import com.gznznzjsn.outstagram.web.dto.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.Arguments;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService service;
    private final PostMapper mapper;

    @MutationMapping(name = "createPost")
    public void create(
            final @Argument UUID accountId,
            final @Arguments PostDto dto,
            final @Argument String placeName,
            final @Argument List<String> tagNames
    ) {
        Post post = mapper.toModel(dto);
        service.create(accountId, post, placeName, tagNames);
    }

}
