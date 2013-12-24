package com.rentit.soap.service;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.rentit.main.Plant;
import com.rentit.main.util.PlantResourceAssembler;
import com.rentit.repository.PlantRepository;
import com.rentit.soap.PlantResourceList;

@WebService
public class PlantSoapService {

	@Autowired PlantRepository plantRepository;
	
	@WebMethod
	public PlantResourceList getAllPlants(){
		List<Plant> plants = Plant.findAllPlants();
		PlantResourceList plantResourceList = PlantResourceAssembler.getPlantResourceList(plants);
		return plantResourceList;
	}
	
	@WebMethod
	public PlantResourceList getAllAvailablePlants(Long startDate,Long endDate){
		List<Plant> plants = plantRepository.findBetweenDateRange(new Date(startDate),new Date(endDate));
		PlantResourceList plantResourceList = PlantResourceAssembler.getPlantResourceList(plants);
		return plantResourceList;
	}
	
}
