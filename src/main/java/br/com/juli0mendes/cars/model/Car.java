package br.com.juli0mendes.cars.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

	@Id
	private String id;
	
	private String model;

	public Car(UUID uuid, Car car) {
		super();
		this.id = uuid.toString();
		this.model = car.getModel();
	}
}