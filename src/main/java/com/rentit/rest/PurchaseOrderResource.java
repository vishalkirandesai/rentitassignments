package com.rentit.rest;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.roo.addon.javabean.RooJavaBean;

import com.rentit.main.util.ResourceSupport;
import com.rentit.main.util.POStatus;


@RooJavaBean
@XmlRootElement(name="purchaseorder")
public class PurchaseOrderResource extends ResourceSupport{

    private Long poId;

    private int siteId;

    private String companyName;

    private Long startDate;
    private Long endDate;

    private float price;

    private Long plantId;
    
    private POStatus status;
    
    private String email;
}
