// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.rentit.main;

import com.rentit.main.Invoice;
import com.rentit.main.PurchaseOrder;
import java.util.Date;

privileged aspect Invoice_Roo_JavaBean {
    
    public float Invoice.getAmountDue() {
        return this.amountDue;
    }
    
    public void Invoice.setAmountDue(float amountDue) {
        this.amountDue = amountDue;
    }
    
    public Date Invoice.getDueDate() {
        return this.dueDate;
    }
    
    public void Invoice.setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    
    public PurchaseOrder Invoice.getPurchaseOrderId() {
        return this.purchaseOrderId;
    }
    
    public void Invoice.setPurchaseOrderId(PurchaseOrder purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }
    
}
