package com.fms.service;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.stereotype.Service;

import com.fms.model.Dashboard;
import com.fms.model.Events;
import com.fms.repository.EventsRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Service
@Slf4j
public class EventServiceImpl implements EventService {

	@Autowired
	ReactiveMongoTemplate reactiveMongoTemplate;

	@Autowired
	EventsRepository eventRepo;

	@Override
	public Flux<Dashboard> getAggregateData() {
		try {
			GroupOperation dashboardOperation = group().count().as("totalevents").sum("livesImpacted")
					.as("totalLivesImpacted").sum("totalVolunteers").as("totalVolunteersCount").sum("totalVolunteers")
					.as("totalParticipants");

			Aggregation aggregation = newAggregation(dashboardOperation);
			Flux<Dashboard> result = reactiveMongoTemplate.aggregate(aggregation, Events.class, Dashboard.class);

			return result;
		} catch (Exception e) {
			log.error("Exception occured at getAggregateData() {}", e.getMessage());
			return null;
		}

	}

	@Override
	public Flux<Events> getAllEvents() {
		try {
			return eventRepo.findAll();
		} catch (Exception e) {
			log.error("Exception occured at getAllEvents() {}", e.getMessage());
			return null;
		}
	}
}
