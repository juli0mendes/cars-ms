package br.com.juli0mendes.cars.config;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import br.com.juli0mendes.cars.handlers.CarHandler;

@Configuration
public class CarRouter {

	private static final String ENDPOINT = "/cars";

	@Bean
	  public RouterFunction<ServerResponse> route(CarHandler carHandler) {
		return RouterFunctions
				.route(GET(ENDPOINT).and(accept(APPLICATION_JSON)), carHandler::findAll)
				.andRoute(GET(ENDPOINT.concat("/{id}")).and(accept(APPLICATION_JSON)), carHandler::findById)
				.andRoute(GET(ENDPOINT.concat("/{id}/events")), carHandler::events)
				.andRoute(POST(ENDPOINT).and(accept(APPLICATION_JSON).and(contentType(APPLICATION_JSON))), carHandler::create);
	}
}