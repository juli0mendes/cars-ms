package br.com.juli0mendes.cars.events;

import java.util.Date;

import br.com.juli0mendes.cars.model.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarEvents {

	private Car model;
	
	private Date when;
}
