package com.gznznzjsn.outstagram.model.relationship;

import com.gznznzjsn.outstagram.model.node.Account;
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
public class Like {

    private UUID id;
    private Account source;
    private Post target;
    private LocalDateTime createdAt;

}
