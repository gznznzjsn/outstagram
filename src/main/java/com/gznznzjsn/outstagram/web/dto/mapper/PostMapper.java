package com.gznznzjsn.outstagram.web.dto.mapper;

import com.gznznzjsn.outstagram.model.node.Post;
import com.gznznzjsn.outstagram.web.dto.PostDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {

    /**
     * Map {@link PostDto} to {@link Post}.
     *
     * @param dto dto of account
     * @return model of account
     */
    Post toModel(PostDto dto);

    /**
     * Map {@link Post} to {@link PostDto}.
     *
     * @param model model of account
     * @return dto of account
     */
    PostDto toDto(Post model);

}
