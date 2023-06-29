package com.gznznzjsn.outstagram.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record PlaceDto(

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        UUID id,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        String name

) {
}
