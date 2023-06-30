package com.gznznzjsn.outstagram.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;


public record PostDto(

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        UUID id,

        List<String> photos,

        String description

) {
}

