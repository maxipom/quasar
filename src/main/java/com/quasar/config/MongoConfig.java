package com.quasar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient() {
        Dotenv dotenv = Dotenv.load();
        String uri = dotenv.get("MONGO_URI");
        return MongoClients.create(uri);
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        Dotenv dotenv = Dotenv.load();
        String dbName = dotenv.get("MONGO_DB_NAME");
        return new MongoTemplate(mongoClient, dbName);
    }
}
