package com.gznznzjsn.outstagram.model.node;

import com.gznznzjsn.outstagram.model.relationship.Commented;
import com.gznznzjsn.outstagram.model.relationship.Liked;
import com.gznznzjsn.outstagram.model.relationship.Subscribed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
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
    private Set<Commented> comments;
    private Set<Liked> likes;
    private Set<Subscribed> subscriptions;

}
