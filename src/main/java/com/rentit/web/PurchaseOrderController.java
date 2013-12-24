package com.rentit.web;
import com.rentit.main.PurchaseOrder;
import com.rentit.main.util.POStatus;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value="/purchaseorders")
@Controller
@RooWebScaffold(path = "purchaseorders", formBackingObject = PurchaseOrder.class)
public class PurchaseOrderController {

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("purchaseorder", purchaseOrderRepository.findOne(id));
        uiModel.addAttribute("itemId", id);
        return "purchaseorders/show";
    }
	
	@RequestMapping(value = "/{id}/delivered", produces = "text/html")
    public String setDelivered(@PathVariable("id") Long id, Model uiModel) {
		PurchaseOrder purchaseOrder = PurchaseOrder.findPurchaseOrder(id);
		if(purchaseOrder.getStatus()== POStatus.DISPATCHED)
			purchaseOrder.setStatus(POStatus.DELIVERED);
		purchaseOrder.persist();
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("purchaseorder", purchaseOrderRepository.findOne(id));
        uiModel.addAttribute("itemId", id);
        return "purchaseorders/show";
    }
	
	@RequestMapping(value = "/{id}/rejected", produces = "text/html")
    public String setRejected(@PathVariable("id") Long id, Model uiModel) {
		PurchaseOrder purchaseOrder = PurchaseOrder.findPurchaseOrder(id);
		if(purchaseOrder.getStatus()== POStatus.DISPATCHED)
			purchaseOrder.setStatus(POStatus.REJECTED);
		purchaseOrder.persist();
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("purchaseorder", purchaseOrderRepository.findOne(id));
        uiModel.addAttribute("itemId", id);
        return "purchaseorders/show";
    }
	
	@RequestMapping(value = "/{id}/dispatched", produces = "text/html")
    public String setDispatched(@PathVariable("id") Long id, Model uiModel) {
		PurchaseOrder purchaseOrder = PurchaseOrder.findPurchaseOrder(id);
		if(purchaseOrder.getStatus()== POStatus.ACCEPTED)
			purchaseOrder.setStatus(POStatus.DISPATCHED);
		purchaseOrder.persist();
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("purchaseorder", purchaseOrderRepository.findOne(id));
        uiModel.addAttribute("itemId", id);
        return "purchaseorders/show";
    }
	
	@RequestMapping(value = "/{id}/returned", produces = "text/html")
    public String setReturned(@PathVariable("id") Long id, Model uiModel) {
		PurchaseOrder purchaseOrder = PurchaseOrder.findPurchaseOrder(id);
		if(purchaseOrder.getStatus()== POStatus.DELIVERED)
			purchaseOrder.setStatus(POStatus.RETURNED);
		purchaseOrder.persist();
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("purchaseorder", purchaseOrderRepository.findOne(id));
        uiModel.addAttribute("itemId", id);
        return "purchaseorders/show";
    }
}
