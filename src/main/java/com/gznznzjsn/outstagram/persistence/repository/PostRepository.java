package com.gznznzjsn.outstagram.persistence.repository;

import com.gznznzjsn.outstagram.model.node.Post;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostRepository {

    void create(Post post);

}
