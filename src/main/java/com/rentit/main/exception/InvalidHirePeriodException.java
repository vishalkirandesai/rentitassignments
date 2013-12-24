package com.rentit.main.exception;

public class InvalidHirePeriodException extends PlantException {

	public InvalidHirePeriodException(String message){
		super("Please check requested dates. \n"+message);
	}
}
