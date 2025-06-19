package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.InventoryItem;
import com.example.demo.response.PostResponse;

@Repository
public interface SellerRepository extends JpaRepository<InventoryItem, Integer>  {

	@Query("Select u from InventoryItem u where u.seller_id = :Seller_Id AND u.is_deleted=0")
	public List<InventoryItem> GetAllInventory(@Param("Seller_Id") Integer Seller_Id);
	
	@Modifying
	@Query("Update InventoryItem u set u.is_deleted=1 where u.purchase_id=:purchase_id")
	int DeleteInventory(@Param("purchase_id") Integer purchase_id);
}
