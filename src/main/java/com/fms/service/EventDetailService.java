package com.fms.service;

import com.fms.model.EventDetail;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EventDetailService {

	
	public Flux<EventDetail> fetchAllEventDetails();
	
	public Mono<EventDetail> fetchEventDetail(String eventId);
	
	public Mono<EventDetail> insertEventDetail(EventDetail data);
}
