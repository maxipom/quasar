package com.quasar.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;

@Profile("!test")
@Configuration
public class MongoConfig {
    @Value("${mongo.uri}")
    private String mongoUri;

    @Value("${mongo.db}")
    private String mongoDbName;

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(this.mongoUri);
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, this.mongoDbName);
    }
}
