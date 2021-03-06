
package com.fms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

@Configuration
public class MongoConfig extends AbstractReactiveMongoConfiguration {

	@Override
	protected String getDatabaseName() {
		return "fms_event";
	}

	public @Bean ReactiveMongoTemplate reactiveMongoTemplate() {
		return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
	}

	@Override
	public MongoClient reactiveMongoClient() {
		return MongoClients.create("mongodb://admin:admin@localhost:27017/fms_event");
	}

}
