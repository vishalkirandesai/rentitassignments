package com.rentit.rest.controller;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.Date;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rentit.main.Plant;
import com.rentit.main.PurchaseOrder;
import com.rentit.main.exception.PurchaseOrderException;
import com.rentit.main.util.ExtendedLink;
import com.rentit.main.util.POStatus;
import com.rentit.main.util.PlantResourceAssembler;
import com.rentit.main.util.PurchaseOrderResourceAssembler;
import com.rentit.repository.PlantRepository;
import com.rentit.repository.PurchaseOrderRepository;
import com.rentit.rest.PurchaseOrderResource;
import com.rentit.rest.PurchaseOrderResourceList;
import com.rentit.soap.PlantResourceList;
import com.rentit.web.PurchaseOrderController;

@Controller
@RequestMapping("/rest/purchaseorders")
public class PurchaseOrderRestController {

	@Autowired
	PurchaseOrderRepository purchaseOrderRepository;
	
	@Autowired 
	static PlantRepository plantRepository;

	Logger logger = LoggerFactory.getLogger(PurchaseOrderController.class);

	//ps6
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<PurchaseOrderResourceList> getAllPurchaseOrders(){
		List<PurchaseOrder> list = PurchaseOrder.findAllPurchaseOrders();
		PurchaseOrderResourceList purchaseOrderResourceList = PurchaseOrderResourceAssembler.getPurchaseOrderResourceList(list);
		ResponseEntity<PurchaseOrderResourceList> responseEntity = new ResponseEntity<>(purchaseOrderResourceList,HttpStatus.OK);
		return responseEntity;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<PurchaseOrderResource> getPurchaseOrder(@PathVariable Long id) throws NoSuchMethodException, SecurityException, PurchaseOrderException{
		PurchaseOrder purchaseOrder = PurchaseOrder.findPurchaseOrder(id);
		logger.debug("Entered getPurchaseOrder() PurchaseOrder id -->"+id);
		if(purchaseOrder == null){
			logger.debug("PurchaseOrder is null");
			throw new PurchaseOrderException("Purchase order id requested -->"+id);
		}
		logger.debug("PurchaseOrder retrieved -->"+purchaseOrder.toString());
		PurchaseOrderResource purchaseOrderResource = PurchaseOrderResourceAssembler.getPurchaseOrderResource(purchaseOrder);
		switch (purchaseOrder.getStatus()) { 
		 case ACCEPTED: 
		 case REJECTED:
		 Method _cancelPO = PurchaseOrderRestController.class.getMethod("cancelPurchaseOrder",Long.class);
		 String cancelLink = linkTo(_cancelPO, purchaseOrder.getId()).toUri().toString(); 
		 purchaseOrderResource.add(new ExtendedLink(cancelLink, "cancelPurchaseOrder", "DELETE")); 
		 
		 break; 
		 default: 
		 break; 
		 }
		ResponseEntity<PurchaseOrderResource> responseEntity = new ResponseEntity<PurchaseOrderResource>(purchaseOrderResource,HttpStatus.OK);
		return responseEntity;
	}
	
	//ps8
	@RequestMapping(method=RequestMethod.DELETE,value="/{id}")
	public ResponseEntity<Void> cancelPurchaseOrder(@PathVariable Long id){
		PurchaseOrder purchaseOrder = PurchaseOrder.findPurchaseOrder(id);
		ResponseEntity<Void> responseEntity;
		if(purchaseOrder.getStatus() != POStatus.DISPATCHED &&
				purchaseOrder.getStatus() != POStatus.DELIVERED){
			purchaseOrder.setStatus(POStatus.CANCELLED);
			purchaseOrder.persist();
			responseEntity = new ResponseEntity<>(HttpStatus.OK);
			return responseEntity;
		}
		responseEntity = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		return responseEntity;
	}
	
	//ps5
	@RequestMapping(method=RequestMethod.GET, value="/plants/{date}")
	public ResponseEntity<PlantResourceList> getDuePlants(@PathVariable Long date){
		List<Plant> plants = purchaseOrderRepository.findPlantsToDeliver(new Date(date));
		PlantResourceList list = PlantResourceAssembler.getPlantResourceList(plants);
		ResponseEntity<PlantResourceList> responseEntity = new ResponseEntity<>(list,HttpStatus.OK);
		return responseEntity;
	}

	//ps4
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<PurchaseOrderResource> createPurchaseOrder(
			@RequestBody PurchaseOrderResource purchaseOrderResource) {
		logger.debug("Entered createPurchaseOrder() path : /purchaseorder");
		ResponseEntity<PurchaseOrderResource> response;
		if(!isPlantAvailable(Plant.findPlant(purchaseOrderResource.getPlantId()),
				purchaseOrderResource.getStartDate(), 
				purchaseOrderResource.getEndDate())){
			response = new ResponseEntity<>(purchaseOrderResource,HttpStatus.NOT_ACCEPTABLE);
			return response;
		}
		if(purchaseOrderResource.getStartDate()==null||
				purchaseOrderResource.getEndDate()==null||
				purchaseOrderResource.getPlantId()==null||
				purchaseOrderResource.getEmail()==null){
			response = new ResponseEntity<>(purchaseOrderResource,HttpStatus.NOT_ACCEPTABLE);
			return response;
		}
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setId(purchaseOrderResource.getPoId());
		purchaseOrder.setSiteId(purchaseOrderResource.getSiteId());
		purchaseOrder.setPlant(Plant.findPlant(purchaseOrderResource.getPlantId()));
		purchaseOrder.setPrice(purchaseOrderResource.getPrice());
		purchaseOrder.setCompanyName(purchaseOrderResource.getCompanyName());
		purchaseOrder.setStartDate(new Date(purchaseOrderResource
				.getStartDate()));
		purchaseOrder.setEndDate(new Date(purchaseOrderResource.getEndDate()));
		purchaseOrder.setStatus(POStatus.ACCEPTED);
		purchaseOrder.persist();
		HttpHeaders headers = new HttpHeaders();
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
				.pathSegment(Long.toString(purchaseOrder.getId())).build()
				.toUri();

		headers.setLocation(location);

		response = new ResponseEntity<PurchaseOrderResource>(PurchaseOrderResourceAssembler.getPurchaseOrderResource(purchaseOrder),headers,
				HttpStatus.CREATED);
		return response;

	}
	
	private static boolean isPlantAvailable(Plant plant,Long startDate,Long endDate){
//		List<Plant> list = plantRepository.findBetweenDateRange(new Date(startDate), new Date (endDate));
		List<PurchaseOrder> poList = PurchaseOrder.findAllPurchaseOrders();
		if(poList.isEmpty())
			return true;
		for(PurchaseOrder purchaseOrder:poList){
			if(purchaseOrder.getPlant().equals(plant))
				if((startDate>=purchaseOrder.getStartDate().getTime()&&startDate<=purchaseOrder.getEndDate().getTime()) ||
					(endDate>=purchaseOrder.getStartDate().getTime()&&startDate<=purchaseOrder.getEndDate().getTime()) ||
					(startDate - endDate < -86400000L)){
						return false;
					}
		}
		return true;
	}

	//ps7
	@RequestMapping(method = RequestMethod.PUT, value="/{id}")
	public ResponseEntity<Void> modifyPurchaseOrder(@PathVariable Long id,
			@RequestBody PurchaseOrderResource purchaseOrderResource) {
		logger.debug("Entered modifyPurchaseOrder() path : /purchaseorder");
		PurchaseOrder purchaseOrder = PurchaseOrder.findPurchaseOrder(id);
		HttpHeaders headers = new HttpHeaders();
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
				.build()
				.toUri();
		headers.setLocation(location);
		ResponseEntity<Void> response;
		if((purchaseOrder.getStartDate().getTime() - System.currentTimeMillis() > 86400000L) &&
				(purchaseOrderResource.getStartDate() - System.currentTimeMillis() > 86400000L)){
			boolean startCondition;
			boolean endCondition;
			if(purchaseOrderResource.getStartDate() == purchaseOrder.getStartDate().getTime())
				startCondition = true;
			else
				startCondition = isPlantAvailable(purchaseOrder.getPlant(), purchaseOrderResource.getStartDate(), purchaseOrder.getStartDate().getTime()-86400000L);
			if(purchaseOrderResource.getEndDate() == purchaseOrder.getEndDate().getTime())
				endCondition = true;
			else
				endCondition = isPlantAvailable(purchaseOrder.getPlant(), purchaseOrder.getEndDate().getTime()+86400000L, purchaseOrderResource.getEndDate());
			if( startCondition && endCondition ){
				purchaseOrder.setSiteId(purchaseOrderResource.getSiteId());
				purchaseOrder.setStartDate(new Date(purchaseOrderResource
						.getStartDate()));
				purchaseOrder.setEndDate(new Date(purchaseOrderResource.getEndDate()));
				purchaseOrder.persist();

				response = new ResponseEntity<>(headers,
					HttpStatus.OK);
			}
			else{
				response = new ResponseEntity<>(headers,
						HttpStatus.NOT_ACCEPTABLE);
			}
		}
		else{
			response = new ResponseEntity<>(headers,
					HttpStatus.NOT_ACCEPTABLE);
		}
		return response;
	}
}
