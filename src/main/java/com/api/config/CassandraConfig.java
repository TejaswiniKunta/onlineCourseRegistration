package com.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;

@Configuration
class CassandraConfig extends AbstractCassandraConfiguration {


    @Value("${spring.data.cassandra.port}")
    private int port;

    @Value("${spring.data.cassandra-keyspace}")
    private String keySpace;

    @Override
    protected String getKeyspaceName() {
        return keySpace;
    }


    @Override
    protected int getPort() {
        return port;
    }

    @Override
    protected String getLocalDataCenter() {
        return "datacenter1";
    }



}