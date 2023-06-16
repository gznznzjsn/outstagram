package com.gznznzjsn.outstagram.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public record AccountDto(

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        UUID id,

        String name,

        String description,

        Boolean isPrivate,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        LocalDateTime createdAt

) {
}
