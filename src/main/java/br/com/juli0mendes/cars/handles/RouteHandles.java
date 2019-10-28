package br.com.juli0mendes.cars.handles;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.created;

import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.juli0mendes.cars.events.CarEvents;
import br.com.juli0mendes.cars.model.Car;
import br.com.juli0mendes.cars.service.FluxCarService;
import reactor.core.publisher.Mono;

@Component
public class RouteHandles {

	private final FluxCarService fluxCarService;

	public RouteHandles(FluxCarService fluxCarService) {
		this.fluxCarService = fluxCarService;
	}

	public Mono<ServerResponse> findAll(ServerRequest serverRequest) {
		return ServerResponse.ok()
				.body(this.fluxCarService.findAll(), Car.class)
				.doOnError(throwable -> new IllegalStateException("My godness NOOOOO!!"));
	}

	public Mono<ServerResponse> findById(ServerRequest serverRequest) {
		String id = serverRequest.pathVariable("id");
		
		return ServerResponse.ok()
				.body(this.fluxCarService.findById(id), Car.class)
				.doOnError(throwable -> new IllegalStateException("oh boy... not againnn =(("));
	}

	public Mono<ServerResponse> events(ServerRequest serverRequest) {
		String id = serverRequest.pathVariable("id");
		
		return ServerResponse.ok()
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(this.fluxCarService.streams(id), CarEvents.class)
				.doOnError(throwable -> new IllegalStateException("I give up!! "));
	}
	
	public Mono<ServerResponse> create(ServerRequest serverRequest) {
		
		final Mono<Car> car = serverRequest.bodyToMono(Car.class);
		
	    final UUID id = UUID.randomUUID();
	    
	    return created(UriComponentsBuilder.fromPath("cars/" + id).build().toUri())
	        .contentType(APPLICATION_JSON)
	        .body(
	            fromPublisher(
	                car.map(p -> new Car(id, p)).flatMap(this.fluxCarService::create), Car.class));

	}
}