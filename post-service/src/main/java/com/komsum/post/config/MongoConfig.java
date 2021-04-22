package com.komsum.post.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import java.util.Collection;
import java.util.Collections;

import static java.util.Collections.singletonList;

//@Configuration
public class MongoConfig
//        extends AbstractMongoClientConfiguration
        {

//    @Value("${spring.data.mongodb.database}")
//    private String database;
//
//    @Value("${spring.data.mongodb.username}")
//    private String username;
//
//    @Value("${spring.data.mongodb.password}")
//    private String password;
//
//    @Override
//    protected String getDatabaseName() {
//        return database;
//    }
//
//    @Override
//    protected void configureClientSettings(MongoClientSettings.Builder builder) {
//        builder
//                .credential(MongoCredential.createCredential(username, database, password.toCharArray()))
//                .applyToClusterSettings(settings  -> {
//                    settings.hosts(singletonList(new ServerAddress("127.0.0.1", 27017)));
//                });
//    }

    //    @Override
//    public MongoClient mongoClient() {
//        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/" + database);
//        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
//            .applyConnectionString(connectionString)
//                .credential()
//            .build();
//
//        return MongoClients.create(mongoClientSettings);
//    }
 
//    @Override
//    public Collection getMappingBasePackages() {
//        return Collections.singleton("com.komsum.post.config");
//    }
}
