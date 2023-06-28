package com.gznznzjsn.outstagram.model.node;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    private UUID id;
    private String name;
    private String description;
    private Boolean isPrivate;
    private LocalDateTime createdAt;

}
