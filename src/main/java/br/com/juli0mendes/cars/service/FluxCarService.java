package br.com.juli0mendes.cars.service;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import br.com.juli0mendes.cars.events.CarEvents;
import br.com.juli0mendes.cars.model.Car;
import br.com.juli0mendes.cars.repository.CarRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Service
public class FluxCarService {

	private final CarRepository carRepository;
	
	public FluxCarService(CarRepository carRepository) {
		this.carRepository = carRepository;
	}
	
	public Flux<Car> findAll() {
		return this.carRepository.findAll();
	}
	
	public Mono<Car> findById(String id) {
		return this.carRepository.findById(id);
	}
	
	/**
	 * Cria um stream de eventos de um determinado dado.
	 * <p> 
	 * Note que ele espera receber um ID e, então, no intervalo de um segundo irá disparar um evento <br>
	 * do conteúdo daquele dado com o respectivo ID. <br>
	 * <p>
	 * Em seguida o método ZIP da classe Flux se encarrega de aguardar até que os dados (os eventos) <br>
	 * estejam prontos e os envia em um response respeitando o intervalo definido.
	 * <p>
	 * @param id
	 * @return
	 */
	public Flux<CarEvents> streams(String id) {
		
		return this.findById(id).flatMapMany(car -> {
			
			Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
			
			Flux<CarEvents> events = Flux.fromStream(
					Stream.generate(() -> new CarEvents(car, new Date())));
			
			return Flux.zip(interval, events).map(Tuple2::getT2);
		});
	}
}
