package com.rentit.main.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.rentit.main.PurchaseOrder;
import com.rentit.rest.PurchaseOrderResource;
import com.rentit.rest.PurchaseOrderResourceList;
import com.rentit.rest.controller.PurchaseOrderRestController;

public class PurchaseOrderResourceAssembler extends ResourceAssemblerSupport<PurchaseOrder, PurchaseOrderResource>{

	public PurchaseOrderResourceAssembler () { 
		 super(PurchaseOrderRestController.class,PurchaseOrderResource.class); 
		}
	
	@Override 
	public PurchaseOrderResource toResource(PurchaseOrder purchaseOrder) { 
		return getPurchaseOrderResource(purchaseOrder);
	}
	
	public static PurchaseOrderResource getPurchaseOrderResource(PurchaseOrder purchaseOrder){
		PurchaseOrderResource purchaseOrderResource = new PurchaseOrderResource();
		purchaseOrderResource.setPoId(purchaseOrder.getId());
		purchaseOrderResource.setPlantId(purchaseOrder.getPlant().getId());
		purchaseOrderResource.setCompanyName(purchaseOrder.getCompanyName());
		purchaseOrderResource.setSiteId(purchaseOrder.getSiteId());
		purchaseOrderResource.setPrice(purchaseOrder.getPrice());
		purchaseOrderResource.setStartDate(purchaseOrder.getStartDate().getTime());
		purchaseOrderResource.setEndDate(purchaseOrder.getEndDate().getTime());
		purchaseOrderResource.setStatus(purchaseOrder.getStatus());
		
		return purchaseOrderResource;
	}
	
	public static PurchaseOrderResourceList getPurchaseOrderResourceList(List<PurchaseOrder> list){
		List<PurchaseOrderResource> purchaseOrderResources = new ArrayList<>();
		for(PurchaseOrder purchaseOrder:list){
			purchaseOrderResources.add(getPurchaseOrderResource(purchaseOrder));
		}
		return new PurchaseOrderResourceList(purchaseOrderResources);
	}
}
