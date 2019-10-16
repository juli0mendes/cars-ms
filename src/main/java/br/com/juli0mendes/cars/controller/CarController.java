package br.com.juli0mendes.cars.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.juli0mendes.cars.events.CarEvents;
import br.com.juli0mendes.cars.model.Car;
import br.com.juli0mendes.cars.service.FluxCarService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@RestController
@RequestMapping("/cars")
public class CarController {

	private final FluxCarService fluxCarService;

	public CarController(FluxCarService fluxCarService) {
		this.fluxCarService = fluxCarService;
	}

	@GetMapping
	public Flux<Car> findAll() {
		return this.fluxCarService.findAll();
	}

	@GetMapping("/{id}")
	public Mono<Car> findById(@PathVariable("id") String id) {
		return this.fluxCarService.findById(id);
	}

	@GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/{id}/events")
	public Flux<CarEvents> eventsOfStreams(@PathVariable("id") String id) {
		return this.fluxCarService.streams(id);
	}
}
