package com.api.config;

import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;

@Configuration
class CassandraConfig  {


//    @Value("$ {spring.data.cassandra.port}")
//    private String port;
//
//    @Value("$ {spring.data.cassandra-keyspace}")
//    private String keySpace;
//
//
//    @Override
//    protected String getKeyspaceName() {
//        return keySpace;
//    }
//
//
//    @Override
//    protected int getPort() {
//        return port;
//    }
//
//    @Override
//    protected String getLocalDataCenter() {
//        return "datacenter1";
//    }

    public @Bean
    CqlSession session() {
        return CqlSession.builder().withKeyspace("onlinecourseregistration").build();
    }

}