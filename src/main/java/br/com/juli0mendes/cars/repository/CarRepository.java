package br.com.juli0mendes.cars.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import br.com.juli0mendes.cars.model.Car;

public interface CarRepository extends ReactiveMongoRepository<Car, String> {
}