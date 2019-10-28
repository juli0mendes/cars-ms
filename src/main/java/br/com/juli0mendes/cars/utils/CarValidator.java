package br.com.juli0mendes.cars.utils;

import static java.lang.Integer.valueOf;
import static org.springframework.util.StringUtils.isEmpty;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.juli0mendes.cars.model.Car;

public class CarValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Car.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "model", "field.required");
		
		Car car = (Car) target;
		
		if (isEmpty(car.getModel()) && car.getModel().length() > 40) {
			errors.rejectValue("model", 
					           "field.max.length", 
					           new Object[] { valueOf(40) },
					           "The template must be a maximum of [6] characters in length.");
		}
	}

}
