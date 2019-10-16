package br.com.juli0mendes.cars.data;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.juli0mendes.cars.model.Car;
import br.com.juli0mendes.cars.repository.CarRepository;
import reactor.core.publisher.Flux;

@Component
public class DummyData implements CommandLineRunner {

	private final CarRepository carRepository;
	
	public DummyData(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		
		this.carRepository.deleteAll()
	        .thenMany(
	              Flux.just("Corsa", "Pálio", "Fusca", "Chevette", "Del Rei", "Opala", "Brasília", "HRV", "Dog Ram", "H20s")
	              .map(model -> new Car(UUID.randomUUID().toString(), model))
	              .flatMap(this.carRepository::save))
	        .subscribe(System.out::println);
	}
}
