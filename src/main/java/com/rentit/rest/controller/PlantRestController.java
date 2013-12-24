package com.rentit.rest.controller;

import java.net.URI;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rentit.main.Plant;
import com.rentit.main.exception.InvalidHirePeriodException;
import com.rentit.main.util.PlantResourceAssembler;
import com.rentit.repository.PlantRepository;
import com.rentit.soap.PlantResource;
import com.rentit.soap.PlantResourceList;

@Controller
@RequestMapping("/rest/plants")
public class PlantRestController {

	@Autowired PlantRepository plantRepository;
	
	//ps1
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<PlantResourceList> getAllPlants(){
		List<Plant> plants = plantRepository.findAll();
		PlantResourceList plantResourceList = PlantResourceAssembler.getPlantResourceList(plants);
		ResponseEntity<PlantResourceList> responseEntity = new ResponseEntity<>(plantResourceList,HttpStatus.OK);
		return responseEntity;
	}
	//ps3
	@RequestMapping(method=RequestMethod.GET, value="/available/{startDate}/{endDate}")
	public ResponseEntity<PlantResourceList> getAvailablePlants(@PathVariable Long startDate,@PathVariable Long endDate) throws InvalidHirePeriodException{
		if(startDate > endDate){
				throw new InvalidHirePeriodException("Requested StartDate -->"+(new Date(startDate)).toString()
													+"Requested EndDate -->"+(new Date(endDate)));
		}
		List<Plant> availablePlants = plantRepository.findBetweenDateRange(new Date(startDate),new Date(endDate));
		PlantResourceList plantResourceList = PlantResourceAssembler.getPlantResourceList(availablePlants);
		ResponseEntity<PlantResourceList> responseEntity = new ResponseEntity<PlantResourceList>(plantResourceList,HttpStatus.OK);
		return responseEntity;
	}
	//ps2
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<PlantResource> getPlantById(@PathVariable Long id){
		Plant plant = Plant.findPlant(id);
		ResponseEntity<PlantResource> responseEntity= new ResponseEntity<PlantResource>(PlantResourceAssembler.getPlantResource(plant),HttpStatus.OK);
		
		return responseEntity;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> createPlant(@RequestBody PlantResource plantResource){
		Plant plant = new Plant();
		plant.setId(plantResource.getPId());
		plant.setPrice(plantResource.getPrice());
		plant.setName(plantResource.getName());
		plant.setDescription(plantResource.getDescription());
		
		HttpHeaders headers = new HttpHeaders(); 
		URI location = 
		ServletUriComponentsBuilder.fromCurrentRequestUri(). 
		 pathSegment(Long.toString(plant.getId())).build().toUri(); 
		
		headers.setLocation(location);
		ResponseEntity<Void> responseEntity = new ResponseEntity<>(headers,HttpStatus.CREATED);
		return responseEntity;
	}
}
