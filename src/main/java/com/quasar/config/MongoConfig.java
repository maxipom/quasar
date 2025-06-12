package com.quasar.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {
    @Value("${mongo.uri}")
    private String mongoUri;

    @Value("${mongo.db}")
    private String mongoDbName;

    @Bean
    public MongoClient mongoClient() {
        System.out.println("Connecting to MongoDB at: " + this.mongoUri);
        return MongoClients.create(this.mongoUri);
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        System.out.println("Connecting to MongoDB at mongoDbName: " + this.mongoDbName);

        return new MongoTemplate(mongoClient, this.mongoDbName);
    }
}
