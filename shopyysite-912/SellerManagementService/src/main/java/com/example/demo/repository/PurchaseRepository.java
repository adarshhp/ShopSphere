package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.PurchaseTable;

public interface PurchaseRepository extends JpaRepository<PurchaseTable,Integer> {
	
	@Query("Select u from PurchaseTable u where u.seller_id = :Seller_Id And u.is_deleted=0")
	public List<PurchaseTable> GetPurchases(@RequestParam Integer Seller_Id);
	
	@Modifying
	@Query("Update PurchaseTable p set p.is_deleted=1 where p.sale_id=:sale_id")
	int DeletePurchase(@Param("sale_id") Integer sale_id);
	
	@Query("SELECT COUNT(u) FROM PurchaseTable u WHERE u.modelNo = :ModelNo AND u.Phono = :PhoneNo")
	int WarrrantyReqValid(@Param("ModelNo") String ModelNo, @Param("PhoneNo") String PhoneNo);
}
