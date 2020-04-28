package com.fms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fms.model.EventDetail;
import com.fms.service.EventDetailService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/eventdetail")
public class EventDetailController {

	@Autowired
	private EventDetailService eventDetailService;

	@PostMapping("/eventdata")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<EventDetail> insertEvents(@RequestBody EventDetail event) {
		return eventDetailService.insertEventDetail(event);
	}

	@GetMapping("/eventdata")
	public Flux<EventDetail> fetchAllEvents() {
		return eventDetailService.fetchAllEventDetails();
	}

	@GetMapping("/eventdata/{eventId}")
	public Mono<ResponseEntity<EventDetail>> fetchEvents(@PathVariable String eventId) {
		Mono<ResponseEntity<EventDetail>> response = eventDetailService.fetchEventDetail(eventId)
				.map(i -> new ResponseEntity<>(i, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		return response;
	}
}
