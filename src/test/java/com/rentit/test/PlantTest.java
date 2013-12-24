package com.rentit.test;
/**
 * @author vishal,jake
 *
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.ws.rs.core.MediaType;

import org.junit.Assert;
import org.junit.Test;

import com.rentit.main.Plant;
import com.rentit.main.util.PlantResourceAssembler;
import com.rentit.rest.PlantResource;
import com.rentit.rest.PlantResourceList;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class PlantTest {
	
	Plant plant;
	Properties properties;
	String url;
	
	public PlantTest() throws FileNotFoundException, IOException{
		properties = new Properties();
		properties.load(new FileReader(new File("system.properties")));
		url = properties.getProperty("app.url");
		plant = new Plant();
		plant.setName("Yamaha");
		plant.setPrice(60);
		plant.setDescription("Something");
		
		Client client = new Client();
		WebResource webResource = client.resource(url+"rest/plants");
		
		webResource.type(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML).post(PlantResourceAssembler.getPlantResource(plant));
	}
	
	@Test
	public void testAvailability(){
		Client client = new Client();
		WebResource resource = client.resource(url+"rest/plants");
		ClientResponse response = resource.type(MediaType.APPLICATION_XML) 
				 .accept(MediaType.APPLICATION_XML).get(ClientResponse.class);
		
		Assert.assertFalse(response.getEntity(PlantResourceList.class).getPlantResources().isEmpty());
	}
	
	@Test
	public void testPriceCheck() throws IOException{
		int id = 1;
		Client client = new Client();
		WebResource webResource = client.resource(url+"rest/plants/"+id);
		ClientResponse clientResponse = webResource.type(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML).get(ClientResponse.class);
		
		Assert.assertTrue(50.0 == clientResponse.getEntity(PlantResource.class).getPrice());
	}
}
