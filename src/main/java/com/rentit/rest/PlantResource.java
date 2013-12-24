package com.rentit.rest;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.roo.addon.javabean.RooJavaBean;

import com.rentit.main.util.ResourceSupport;

@RooJavaBean
@XmlRootElement(name="plant")
public class PlantResource extends ResourceSupport{
	
	private Long pId;
	private float price;
	private String name;
	private String description;
}
