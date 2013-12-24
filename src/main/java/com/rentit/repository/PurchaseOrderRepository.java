package com.rentit.repository;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.rentit.main.Plant;
import com.rentit.main.PurchaseOrder;

@RooJpaRepository(domainType = PurchaseOrder.class)
public interface PurchaseOrderRepository {
	
	@Query("Delete FROM PurchaseOrder p WHERE p.id = :id")
	@Transactional(readOnly = false)
	void deletePurchaseOrder(@Param("id") int id);
	
	@Query("Select p from PurchaseOrder p Where p.id = :id")
	@Transactional(readOnly = true)
	PurchaseOrder findPurchaseOrderById(@Param("id") int id);
	
	@Query("Select p.plant from PurchaseOrder p Where p.startDate=:startDate")
	@Transactional(readOnly = true)
	List<Plant> findPlantsToDeliver(@Param("startDate") Date startDate);
	
}
