package com.rentit.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.rentit.main.Plant;

@RooJpaRepository(domainType = Plant.class)
public interface PlantRepository {

	@Query("SELECT p FROM Plant AS p WHERE LOWER(p.name) LIKE LOWER(:plantName) AND p.price BETWEEN :minPrice AND :maxPrice")
	@Transactional(readOnly = true)
	public List<Plant> findByNameLikePriceRange(@Param("plantName") String name,
			@Param("minPrice") Float price1, @Param("maxPrice") Float price2);
	
	@Query("SELECT q.plant FROM PurchaseOrder q where ((q.startDate BETWEEN :startDate AND :endDate) AND (q.endDate BETWEEN :startDate AND :endDate))")
	@Transactional(readOnly = true)
	public List<Plant> findBetweenDateRange(@Param("startDate")Date startDate,@Param("endDate")Date endDate);

	@Query("SELECT p.price FROM Plant p WHERE p.id = :id")
	@Transactional(readOnly = true)
	public int findPriceById(@Param("id")int id);
	
}
