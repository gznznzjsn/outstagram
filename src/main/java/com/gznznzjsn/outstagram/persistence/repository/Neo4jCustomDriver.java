package com.gznznzjsn.outstagram.persistence.repository;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.neo4j.driver.SessionConfig;
import org.springframework.stereotype.Component;

@Component
public class Neo4jCustomDriver {

    private final Driver driver;
    private final String databaseName;

    public Neo4jCustomDriver() {
        this.driver = GraphDatabase.driver(
                "neo4j://localhost:7687",
                AuthTokens.basic("neo4j", "password")
        );
        this.databaseName = "outstagram";
    }

    public Session getSession() {
        return driver.session(SessionConfig.forDatabase(databaseName));
    }

}
