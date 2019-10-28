package br.com.juli0mendes.cars;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import br.com.juli0mendes.cars.handles.RouteHandles;

@SpringBootApplication
public class CarsMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarsMsApplication.class, args);
	}


	@Bean
	RouterFunction<?> routes(RouteHandles routeHandles) {
		return RouterFunctions
				.route(GET("/cars"), routeHandles::findAll)
				.andRoute(GET("/cars/{id}"), routeHandles::findById)
				.andRoute(GET("/cars/{id}/events"), routeHandles::events)
				.andRoute(POST("/cars").and(accept(APPLICATION_JSON).and(contentType(APPLICATION_JSON))), routeHandles::create);
	}
	
}