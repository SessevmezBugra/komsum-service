package com.komsum.feed.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableCassandraRepositories(basePackages = "com.komsum.feed")
public class CassandraConfig
        extends AbstractCassandraConfiguration {


    @Value("${spring.data.cassandra.keyspace-name}")
    private String keyspace;

    @Value("${spring.data.cassandra.contact-points}")
    private String contactPoints;

    @Value("${spring.data.cassandra.port}")
    private int port;
    
    @Value("${spring.data.cassandra.data-center}")
    private String dataCenter;



    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        CreateKeyspaceSpecification specification = CreateKeyspaceSpecification.
                createKeyspace(keyspace)
                .ifNotExists()
                .with(KeyspaceOption.DURABLE_WRITES, true)
                .withSimpleReplication();

        return Arrays.asList(specification);
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Override
    public String getContactPoints() {
        return contactPoints;
    }

    @Override
    public String getKeyspaceName() {
        return keyspace;
    }

    @Override
    public String[] getEntityBasePackages() {
            return new String[]{"com.komsum.feed.entity"};
    }
    
    @Override
    protected String getLocalDataCenter() {
    	return dataCenter;
    }
}