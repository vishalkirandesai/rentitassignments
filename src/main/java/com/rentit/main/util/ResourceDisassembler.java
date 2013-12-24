package com.rentit.main.util;

import java.util.Date;

import com.rentit.main.Plant;
import com.rentit.main.PurchaseOrder;
import com.rentit.rest.PurchaseOrderResource;

public class ResourceDisassembler {
	
	public static boolean createPurchaseOrder(PurchaseOrderResource purchaseOrderResource){
		if(purchaseOrderResource.getStartDate()==null||
			purchaseOrderResource.getEndDate()==null||
			purchaseOrderResource.getPlantId()==null||
			purchaseOrderResource.getEmail()==null)
			return false;
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setPlant(Plant.findPlant(purchaseOrderResource.getPlantId()));
		purchaseOrder.setSiteId(purchaseOrderResource.getSiteId());
		purchaseOrder.setStartDate(new Date(purchaseOrderResource.getStartDate()));
		purchaseOrder.setEndDate(new Date(purchaseOrderResource.getEndDate()));
		float price = ((purchaseOrderResource.getEndDate() - purchaseOrderResource.getEndDate())/86400000L )* purchaseOrder.getPlant().getPrice();
		purchaseOrder.setPrice(price);
		purchaseOrder.setCompanyName(purchaseOrderResource.getCompanyName());
		purchaseOrder.setCustomerEmail(purchaseOrderResource.getEmail());
		purchaseOrder.persist();
		
		return true;
	}
	
	public static boolean modifyPurchaseOrder(PurchaseOrderResource purchaseOrderResource){
		if(purchaseOrderResource.getStartDate()==null||
			purchaseOrderResource.getEndDate()==null||
			purchaseOrderResource.getPoId()==null)
			return false;
		PurchaseOrder purchaseOrder = PurchaseOrder.findPurchaseOrder(purchaseOrderResource.getPoId());
		purchaseOrder.setStartDate(new Date(purchaseOrderResource.getStartDate()));
		purchaseOrder.setEndDate(new Date(purchaseOrderResource.getEndDate()));
		float price = ((purchaseOrderResource.getEndDate() - purchaseOrderResource.getEndDate())/86400000L )* purchaseOrder.getPlant().getPrice();
		purchaseOrder.setPrice(price);
		purchaseOrder.persist();
		return true;
	}

}
