package com.api.config;



import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import java.util.List;


@ComponentScan
@Configuration
public class ElasticSearchConfig {

    @Value("$ {elasticsearch.clustername}")
    private String elasticSearchClusterName;
    @Value("# {'$ {elasticsearch.host}'.split(',')}")
    private List<String> elasticSearchHost;
    @Value("$ {elasticsearch.port}")
    private String elasticSearchPort;


    public String getElasticSearchClusterName() {
        return elasticSearchClusterName;
    }

    public void setElasticSearchClusterName(String elasticSearchClusterName) {
        this.elasticSearchClusterName = elasticSearchClusterName;
    }

    public List<String> getElasticSearchHost() {
        return elasticSearchHost;
    }

    public void setElasticSearchHost(List<String> elasticSearchHost) {
        this.elasticSearchHost = elasticSearchHost;
    }

    public String getElasticSearchPort() {
        return elasticSearchPort;
    }

    public void setElasticSearchPort(String elasticSearchPort) {
        this.elasticSearchPort = elasticSearchPort;
    }


    @Bean
    public RestHighLevelClient client() {
        ClientConfiguration clientConfiguration
                = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();

        return RestClients.create(clientConfiguration).rest();
    }


      @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchRestTemplate(client());
    }



}
