package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.InventoryItem;

@Repository
public interface SellerRepository extends JpaRepository<InventoryItem, Integer>  {

	@Query("Select u from InventoryItem u where u.seller_id = :Seller_Id")
	public List<InventoryItem> GetAllInventory(@Param("Seller_Id") Integer Seller_Id);
}
