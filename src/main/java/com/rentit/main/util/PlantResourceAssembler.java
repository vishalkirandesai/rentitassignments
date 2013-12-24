package com.rentit.main.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.rentit.main.Plant;
import com.rentit.rest.controller.PlantRestController;
import com.rentit.soap.PlantResource;
import com.rentit.soap.PlantResourceList;

public class PlantResourceAssembler {
	
	public static PlantResource getPlantResource(Plant plant){
		PlantResource plantResource = new PlantResource();
		plantResource.setPId(plant.getId());
		plantResource.setName(plant.getName());
		plantResource.setPrice(plant.getPrice());
		plantResource.setDescription(plant.getDescription());
		
		return plantResource;
	}
	
	public static Plant getPlant(PlantResource plantResource){
		Plant plant = new Plant();
		plant.setId(plantResource.getPId());
		plant.setName(plantResource.getName());
		plant.setPrice(plantResource.getPrice());
		plant.setDescription(plantResource.getDescription());
		return plant;
	}
	
	public static PlantResourceList getPlantResourceList(List<Plant> plants){
		List<PlantResource> plantResources = new ArrayList<PlantResource>();
		for(Plant plant:plants){
			plantResources.add(getPlantResource(plant));
		}
		return new PlantResourceList(plantResources);
	}
}
