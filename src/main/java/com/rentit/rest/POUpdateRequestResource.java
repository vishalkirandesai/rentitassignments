package com.rentit.rest;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.roo.addon.javabean.RooJavaBean;

import com.rentit.soap.PlantResource;

@RooJavaBean
@XmlRootElement(name="poupdate")
public class POUpdateRequestResource {

    private int siteId;

    private Long startDate;
    private Long endDate;

    private int price;

    private PlantResource plantId;
}
