package com.memcached.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.btmatthews.springboot.memcached.EnableMemcached;

import net.spy.memcached.ConnectionFactory;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.FailureMode;

@SpringBootApplication
@EnableCaching
@EnableMemcached
public class MemCachedDemoApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(MemCachedDemoApplication.class, args);
	}

	
	@Bean
    public ConnectionFactory memcachedConnection() {
        return new ConnectionFactoryBuilder()
                    .setDaemon(true)
                    .setFailureMode(FailureMode.Cancel)
                .build();
    }
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
