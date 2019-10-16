package br.com.juli0mendes.cars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RequestPredicates;
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
		return RouterFunctions.route(RequestPredicates.GET("/cars"), routeHandles::findAll)
				.andRoute(RequestPredicates.GET("/cars/{id}"), routeHandles::findById)
				.andRoute(RequestPredicates.GET("/cars/{id}/events"), routeHandles::events);
	}
}