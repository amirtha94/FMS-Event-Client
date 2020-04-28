package com.fms.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fms.model.Dashboard;
import com.fms.model.Events;
import com.fms.service.EventService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/event")
public class EventController {

	@Autowired
	EventService eventService;
	
	@GetMapping(value = "/getDashboardInfo")
	public Flux<Dashboard> fetchDashboard() {
		return eventService.getAggregateData();
	}
	
	@GetMapping(value = "/allevents")
	public Flux<Events> fetchAllEvents() {
		return eventService.getAllEvents();
	}
}
