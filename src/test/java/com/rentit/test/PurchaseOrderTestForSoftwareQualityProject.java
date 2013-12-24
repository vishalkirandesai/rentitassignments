package com.rentit.test;
/**
 * @author vishal,jake
 * 
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.Properties;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;

import com.rentit.rest.PurchaseOrderResource;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;


@ContextConfiguration(locations = { "/META-INF/spring/applicationContext.xml","/META-INF/spring/applicationContext-security.xml","/META-INF/spring/applicationContext-jpa.xml" })
public class PurchaseOrderTestForSoftwareQualityProject {

	PurchaseOrderResource purchaseOrderResource;
	String url;
	Properties properties;
	URI uri;

	public PurchaseOrderTestForSoftwareQualityProject() throws FileNotFoundException, IOException {
			properties = new Properties();
			properties.load(new FileInputStream(new File("system.properties")));
			url = properties.getProperty("app.url");
	}

	@Test
	public void createPOPlantAvailableStartDateNull() {
		purchaseOrderResource = new PurchaseOrderResource();
		purchaseOrderResource.setCompanyName("BuildIt");
		purchaseOrderResource.setPlantId(15L);
		purchaseOrderResource.setSiteId(15);
		purchaseOrderResource.setPrice(50.0f);
		// start date null
		//purchaseOrderResource.setStartDate(1385683200000L);
		purchaseOrderResource.setEndDate(1386028800000L);
		purchaseOrderResource.setEmail("builtit.finally@gmail.com");
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("customer", "customer"));
		WebResource webResource = client
				.resource(url+"rest/purchaseorders");

		ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML)
				.post(ClientResponse.class, purchaseOrderResource);
		
		uri = response.getLocation();
		System.out.println(uri);
		Assert.assertEquals(Response.Status.NOT_ACCEPTABLE.getStatusCode(),response.getStatus());

	}
	
	@Test
	public void createPOPlantAvailableEndDateNull() {
		purchaseOrderResource = new PurchaseOrderResource();
		purchaseOrderResource.setCompanyName("BuildIt");
		purchaseOrderResource.setPlantId(15L);
		purchaseOrderResource.setSiteId(15);
		purchaseOrderResource.setPrice(50.0f);
		purchaseOrderResource.setStartDate(1385683200000L);
		// end date null
		//purchaseOrderResource.setEndDate(1386028800000L);
		purchaseOrderResource.setEmail("builtit.finally@gmail.com");
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("customer", "customer"));
		WebResource webResource = client
				.resource(url+"rest/purchaseorders");

		ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML)
				.post(ClientResponse.class, purchaseOrderResource);
		
		uri = response.getLocation();
		System.out.println(uri);
		Assert.assertEquals(Response.Status.NOT_ACCEPTABLE.getStatusCode(),response.getStatus());

	}
	
	@Test
	public void createPOPlantAvailablePlantIdNull() {
		purchaseOrderResource = new PurchaseOrderResource();
		purchaseOrderResource.setCompanyName("BuildIt");
		// plant id null
		//purchaseOrderResource.setPlantId(15L);
		purchaseOrderResource.setSiteId(15);
		purchaseOrderResource.setPrice(50.0f);
		purchaseOrderResource.setStartDate(1385683200000L);
		purchaseOrderResource.setEndDate(1386028800000L);
		purchaseOrderResource.setEmail("builtit.finally@gmail.com");
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("customer", "customer"));
		WebResource webResource = client
				.resource(url+"rest/purchaseorders");

		ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML)
				.post(ClientResponse.class, purchaseOrderResource);
		
		uri = response.getLocation();
		System.out.println(uri);
		Assert.assertEquals(Response.Status.NOT_ACCEPTABLE.getStatusCode(),response.getStatus());

	}
	
	@Test
	public void createPOPlantAvailableEmailNull() {
		purchaseOrderResource = new PurchaseOrderResource();
		purchaseOrderResource.setCompanyName("BuildIt");
		purchaseOrderResource.setPlantId(15L);
		purchaseOrderResource.setSiteId(15);
		purchaseOrderResource.setPrice(50.0f);
		purchaseOrderResource.setStartDate(1385683200000L);
		purchaseOrderResource.setEndDate(1386028800000L);
		// email null
		//purchaseOrderResource.setEmail("builtit.finally@gmail.com");
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("customer", "customer"));
		WebResource webResource = client
				.resource(url+"rest/purchaseorders");

		ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML)
				.post(ClientResponse.class, purchaseOrderResource);
		
		uri = response.getLocation();
		System.out.println(uri);
		Assert.assertEquals(Response.Status.NOT_ACCEPTABLE.getStatusCode(),response.getStatus());

	}
	
	@Test
	public void createPOPlantNotAvailable() {
		purchaseOrderResource = new PurchaseOrderResource();
		purchaseOrderResource.setCompanyName("BuildIt");
		// made sure that no plant with id 25 exists
		purchaseOrderResource.setPlantId(25L);
		purchaseOrderResource.setSiteId(15);
		purchaseOrderResource.setPrice(50.0f);
		purchaseOrderResource.setStartDate(1385683200000L);
		purchaseOrderResource.setEndDate(1386028800000L);
		purchaseOrderResource.setEmail("builtit.finally@gmail.com");
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("customer", "customer"));
		WebResource webResource = client
				.resource(url+"rest/purchaseorders");

		ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML)
				.post(ClientResponse.class, purchaseOrderResource);
		
		uri = response.getLocation();
		System.out.println(uri);
		Assert.assertEquals(Response.Status.NOT_ACCEPTABLE.getStatusCode(),response.getStatus());

	}
	
	@Test
	public void createPOPlantAvailableAllesGut() {
		purchaseOrderResource = new PurchaseOrderResource();
		purchaseOrderResource.setCompanyName("BuildIt");
		purchaseOrderResource.setPlantId(79L);
		purchaseOrderResource.setSiteId(15);
		purchaseOrderResource.setPrice(50.0f);
		purchaseOrderResource.setStartDate(1385683200000L);
		purchaseOrderResource.setEndDate(1386028800000L);
		purchaseOrderResource.setEmail("builtit.finally@gmail.com");
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("customer", "customer"));
		WebResource webResource = client
				.resource(url+"rest/purchaseorders");

		ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML)
				.post(ClientResponse.class, purchaseOrderResource);
		
		uri = response.getLocation();
		System.out.println(uri);
		Assert.assertEquals(Response.Status.CREATED.getStatusCode(),response.getStatus());

	}
	
	@Test
	public void modifyPurchaseOrderAllesGut() {
		purchaseOrderResource = new PurchaseOrderResource();
		purchaseOrderResource.setCompanyName("BuildIt");
		purchaseOrderResource.setPlantId(80L);
		purchaseOrderResource.setSiteId(20);
		purchaseOrderResource.setPrice(60.0f);
		purchaseOrderResource.setStartDate(1389781031000L);
		purchaseOrderResource.setEndDate(1390213031000L);
		purchaseOrderResource.setEmail("builtit.finally@gmail.com");
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("customer", "customer"));
		WebResource webResource = client
				.resource(url+"rest/purchaseorders");
		ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML)
				.post(ClientResponse.class, purchaseOrderResource);
		uri = response.getLocation();
		purchaseOrderResource.setStartDate(1389781031000L);
		purchaseOrderResource.setEndDate(1390385831000L);
		webResource = client
				.resource(uri);
		response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML)
				.put(ClientResponse.class, purchaseOrderResource);
		
		Assert.assertEquals(Response.Status.OK.getStatusCode(),response.getStatus());

	}
	
	@Test
	public void modifyPurchaseOrderStartDateEqualsPrevious() {
		purchaseOrderResource = new PurchaseOrderResource();
		purchaseOrderResource.setCompanyName("BuildIt");
		purchaseOrderResource.setPlantId(16L);
		purchaseOrderResource.setSiteId(20);
		purchaseOrderResource.setPrice(60.0f);
		purchaseOrderResource.setStartDate(1389781031000L);
		purchaseOrderResource.setEndDate(1390213031000L);
		purchaseOrderResource.setEmail("builtit.finally@gmail.com");
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("customer", "customer"));
		WebResource webResource = client
				.resource(url+"rest/purchaseorders");
		ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML)
				.post(ClientResponse.class, purchaseOrderResource);
		uri = response.getLocation();
		purchaseOrderResource.setStartDate(1389781031000L);
		purchaseOrderResource.setEndDate(1390385831000L);
		webResource = client
				.resource(uri);
		response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML)
				.put(ClientResponse.class, purchaseOrderResource);
		
		Assert.assertEquals(Response.Status.OK.getStatusCode(),response.getStatus());

	}
	
	@Test
	public void modifyPurchaseOrderEndDateEqualsPrevious() {
		purchaseOrderResource = new PurchaseOrderResource();
		purchaseOrderResource.setCompanyName("BuildIt");
		purchaseOrderResource.setPlantId(16L);
		purchaseOrderResource.setSiteId(20);
		purchaseOrderResource.setPrice(60.0f);
		purchaseOrderResource.setStartDate(1389781031000L);
		purchaseOrderResource.setEndDate(1390213031000L);
		purchaseOrderResource.setEmail("builtit.finally@gmail.com");
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("customer", "customer"));
		WebResource webResource = client
				.resource(url+"rest/purchaseorders");
		ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML)
				.post(ClientResponse.class, purchaseOrderResource);
		uri = response.getLocation();
		purchaseOrderResource.setStartDate(1389992679000L);
		purchaseOrderResource.setEndDate(1390213031000L);
		webResource = client
				.resource(uri);
		response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML)
				.put(ClientResponse.class, purchaseOrderResource);
		
		Assert.assertEquals(Response.Status.OK.getStatusCode(),response.getStatus());

	}
	
	@Test
	public void modifyPurchaseOrderDatesAreSame() {
		purchaseOrderResource = new PurchaseOrderResource();
		purchaseOrderResource.setCompanyName("BuildIt");
		purchaseOrderResource.setPlantId(16L);
		purchaseOrderResource.setSiteId(20);
		purchaseOrderResource.setPrice(60.0f);
		purchaseOrderResource.setStartDate(1389781031000L);
		purchaseOrderResource.setEndDate(1390213031000L);
		purchaseOrderResource.setEmail("builtit.finally@gmail.com");
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("customer", "customer"));
		WebResource webResource = client
				.resource(url+"rest/purchaseorders");
		ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML)
				.post(ClientResponse.class, purchaseOrderResource);
		uri = response.getLocation();
		purchaseOrderResource.setStartDate(1389781031000L);
		purchaseOrderResource.setEndDate(1390213031000L);
		webResource = client
				.resource(uri);
		response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML)
				.put(ClientResponse.class, purchaseOrderResource);
		
		Assert.assertEquals(Response.Status.OK.getStatusCode(),response.getStatus());

	}
	
	@Test
	public void modifyPurchaseOrderStartDateIsGreaterThanEndDate() {
		purchaseOrderResource = new PurchaseOrderResource();
		purchaseOrderResource.setCompanyName("BuildIt");
		purchaseOrderResource.setPlantId(16L);
		purchaseOrderResource.setSiteId(20);
		purchaseOrderResource.setPrice(60.0f);
		purchaseOrderResource.setStartDate(1389781031000L);
		purchaseOrderResource.setEndDate(1390213031000L);
		purchaseOrderResource.setEmail("builtit.finally@gmail.com");
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("customer", "customer"));
		WebResource webResource = client
				.resource(url+"rest/purchaseorders");
		ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML)
				.post(ClientResponse.class, purchaseOrderResource);
		uri = response.getLocation();
		purchaseOrderResource.setStartDate(1390213031000L);
		purchaseOrderResource.setEndDate(1389781031000L);
		webResource = client
				.resource(uri);
		response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML)
				.put(ClientResponse.class, purchaseOrderResource);
		
		Assert.assertEquals(Response.Status.NOT_ACCEPTABLE.getStatusCode(),response.getStatus());

	}
	
	@Test
	public void modifyPurchaseOrderDatesAreCorrectButPlantIsUnavailable() {
		purchaseOrderResource = new PurchaseOrderResource();
		purchaseOrderResource.setCompanyName("BuildIt");
		// made sure no plant with id of 25 exists
		purchaseOrderResource.setPlantId(25L);
		purchaseOrderResource.setSiteId(20);
		purchaseOrderResource.setPrice(60.0f);
		purchaseOrderResource.setStartDate(1389781031000L);
		purchaseOrderResource.setEndDate(1390213031000L);
		purchaseOrderResource.setEmail("builtit.finally@gmail.com");
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("customer", "customer"));
		WebResource webResource = client
				.resource(url+"rest/purchaseorders");
		ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML)
				.post(ClientResponse.class, purchaseOrderResource);
		uri = response.getLocation();
		purchaseOrderResource.setStartDate(1389781031000L);
		purchaseOrderResource.setEndDate(1390213031000L);
		webResource = client
				.resource(uri);
		response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML)
				.put(ClientResponse.class, purchaseOrderResource);
		
		Assert.assertEquals(Response.Status.NOT_ACCEPTABLE.getStatusCode(),response.getStatus());

	}
	
	@Test
	public void getPurchaseOrders(){
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("customer", "customer"));
		WebResource webResource = client
				.resource(url+"rest/purchaseorders");
		ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML)
				.get(ClientResponse.class);
		
		Assert.assertEquals(Response.Status.OK.getStatusCode(),response.getStatus());
	}
	
	@Test
	public void getPurchaseOrderAvailable(){
		purchaseOrderResource = new PurchaseOrderResource();
		purchaseOrderResource.setCompanyName("BuildIt");
		purchaseOrderResource.setPlantId(16L);
		purchaseOrderResource.setSiteId(20);
		purchaseOrderResource.setPrice(60.0f);
		purchaseOrderResource.setStartDate(1389781031000L);
		purchaseOrderResource.setEndDate(1390213031000L);
		purchaseOrderResource.setEmail("builtit.finally@gmail.com");
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("customer", "customer"));
		WebResource webResource = client
				.resource(url+"rest/purchaseorders");
		ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML)
				.post(ClientResponse.class, purchaseOrderResource);
		uri = response.getLocation();
		webResource = client
				.resource(uri);
		response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML)
				.get(ClientResponse.class);
		
		Assert.assertEquals(Response.Status.OK.getStatusCode(),response.getStatus());
	}
	
	@Test
	public void getPurchaseOrderNotAvailable(){
		purchaseOrderResource = new PurchaseOrderResource();
		purchaseOrderResource.setCompanyName("BuildIt");
		purchaseOrderResource.setPlantId(16L);
		purchaseOrderResource.setSiteId(20);
		purchaseOrderResource.setPrice(60.0f);
		purchaseOrderResource.setStartDate(1389781031000L);
		purchaseOrderResource.setEndDate(1390213031000L);
		purchaseOrderResource.setEmail("builtit.finally@gmail.com");
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("customer", "customer"));
		WebResource webResource = client
				.resource(url+"rest/purchaseorders");
		ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML)
				.post(ClientResponse.class, purchaseOrderResource);
		uri = response.getLocation();
		webResource = client
				.resource(url+"rest/purchaseorders/2555");
		response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML)
				.get(ClientResponse.class);
		
		Assert.assertEquals(Response.Status.NOT_FOUND.getStatusCode(),response.getStatus());
	}
	
	@Test
	public void cancelPOAllesGut() throws IOException {
		purchaseOrderResource = new PurchaseOrderResource();
		purchaseOrderResource.setCompanyName("BuildIt");
		purchaseOrderResource.setPlantId(78L);
		purchaseOrderResource.setSiteId(20);
		purchaseOrderResource.setPrice(20.0f);
		purchaseOrderResource.setStartDate(1389781031000L);
		purchaseOrderResource.setEndDate(1390213031000L);
		purchaseOrderResource.setEmail("builtit.finally@gmail.com");
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("customer", "customer"));
		WebResource webResource = client
				.resource(url+"rest/purchaseorders");
		ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML)
				.post(ClientResponse.class, purchaseOrderResource);
		uri = response.getLocation();
		webResource = client
				.resource(uri);
		response = webResource.type(MediaType.APPLICATION_XML)
						.accept(MediaType.APPLICATION_XML)
						.delete(ClientResponse.class);
		Assert.assertEquals(Response.Status.OK.getStatusCode(),response.getStatus());

	}
}
