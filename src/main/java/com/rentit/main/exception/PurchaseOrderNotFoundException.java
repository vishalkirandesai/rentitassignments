package com.rentit.main.exception;

public class PurchaseOrderNotFoundException extends PurchaseOrderException{

	public PurchaseOrderNotFoundException(){
		super("The purchase order that you are looking for does not exist.");
	}
}
