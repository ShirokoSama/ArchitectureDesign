package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories
@EnableDiscoveryClient
@EnableJpaRepositories
public class TestServiceProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestServiceProviderApplication.class, args);
	}
}
