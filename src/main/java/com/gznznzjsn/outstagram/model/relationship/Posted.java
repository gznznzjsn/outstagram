package com.gznznzjsn.outstagram.model.relationship;

import com.gznznzjsn.outstagram.model.node.Post;
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
public class Posted {

    private UUID id;
    private Boolean isPinned;
    private LocalDateTime createdAt;
    private Post post;

}
