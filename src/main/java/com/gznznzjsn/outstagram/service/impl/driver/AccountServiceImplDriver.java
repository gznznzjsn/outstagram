package com.gznznzjsn.outstagram.service.impl.driver;

import com.gznznzjsn.outstagram.model.node.Account;
import com.gznznzjsn.outstagram.persistence.repository.AccountRepository;
import com.gznznzjsn.outstagram.persistence.repository.Neo4jCustomDriver;
import com.gznznzjsn.outstagram.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.neo4j.driver.Session;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImplDriver implements AccountService {

    private final Neo4jCustomDriver driver;
    private final AccountRepository repository;

    @Override
    public void create(final Account account) {
        try (Session session = driver.getSession()) {
            session.executeWriteWithoutResult(tx -> {
                account.setId(UUID.randomUUID());
                account.setCreatedAt(LocalDateTime.now());
                repository.create(account, tx);
            });
        }
    }

}
