package com.fms.service;

import com.fms.model.Dashboard;
import com.fms.model.Events;

import reactor.core.publisher.Flux;

public interface EventService {

	public Flux<Dashboard> getAggregateData();

	Flux<Events> getAllEvents();
	
}
