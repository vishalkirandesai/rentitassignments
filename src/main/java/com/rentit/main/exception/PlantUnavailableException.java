package com.rentit.main.exception;

public class PlantUnavailableException extends PurchaseOrderException {

	public PlantUnavailableException(String message){
		super("The plant you are trying to find is unavailable.\n"+message);
	}
}
