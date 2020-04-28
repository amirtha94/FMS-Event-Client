package com.fms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;

import com.fms.model.EventDetail;
import com.fms.repository.EventDetailsRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EventDetailServiceImpl implements EventDetailService{

	
	@Autowired
	ReactiveMongoTemplate montoTemplate;

	@Autowired
	private EventDetailsRepository eventDetailRepo;

	@Override
	public Flux<EventDetail> fetchAllEventDetails() {
		return eventDetailRepo.findAll();
	}

	@Override
	public Mono<EventDetail> fetchEventDetail(String eventId) {
		return eventDetailRepo.findById(eventId);
	}

	@Override
	public Mono<EventDetail> insertEventDetail(EventDetail entity) {
		return eventDetailRepo.save(entity);
	}
}
