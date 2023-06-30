package com.gznznzjsn.outstagram.service.impl.driver;

import com.gznznzjsn.outstagram.model.node.Tag;
import com.gznznzjsn.outstagram.persistence.repository.TagRepository;
import com.gznznzjsn.outstagram.service.TagService;
import lombok.RequiredArgsConstructor;
import org.neo4j.driver.TransactionContext;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TagServiceImplDriver implements TagService {

    private final TagRepository repository;

    @Override
    public Tag retrieveOrCreateAndRetrieve(
            final String name,
            final TransactionContext tx
    ) {
        return repository.findByName(name, tx)
                .orElseGet(() -> {
                    Tag tag = new Tag(UUID.randomUUID(), name);
                    repository.create(tag, tx);
                    return tag;
                });
    }

}
