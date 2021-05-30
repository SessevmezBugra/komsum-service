package com.komsum.tag.config;

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
