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
public class PurchaseOrderTest {

	PurchaseOrderResource purchaseOrderResource;
	String url;
	Properties properties;
	URI uri;

	public PurchaseOrderTest() throws FileNotFoundException, IOException {
			properties = new Properties();
			properties.load(new FileInputStream(new File("system.properties")));
			url = properties.getProperty("app.url");
	}

	@Test
	public void test1() {
		purchaseOrderResource = new PurchaseOrderResource();
		purchaseOrderResource.setCompanyName("BuildIt");
		purchaseOrderResource.setPlantId(15L);
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
	public void test2() {
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
	public void test3() throws IOException {
		purchaseOrderResource = new PurchaseOrderResource();
		purchaseOrderResource.setCompanyName("BuildIt");
		purchaseOrderResource.setPlantId(1L);
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
