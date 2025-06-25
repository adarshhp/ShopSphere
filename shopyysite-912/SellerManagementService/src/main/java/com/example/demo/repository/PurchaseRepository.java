package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.PurchaseTable;

public interface PurchaseRepository extends JpaRepository<PurchaseTable,Integer> {
	
//	@Query("Select u from PurchaseTable u where u.seller_id = :Seller_Id And u.is_deleted=0")
//	public List<PurchaseTable> GetPurchases(@RequestParam Integer Seller_Id);
	
	@Query("SELECT p FROM PurchaseTable p " +
		       "WHERE p.is_deleted = 0 " +
		       "AND (:sellerId IS NULL OR p.seller_id = :sellerId) " +
		       "AND (:modelNo IS NULL OR LOWER(p.modelNo) = LOWER(:modelNo))")
		List<PurchaseTable> findFilteredPurchases(
		    @Param("sellerId") Integer sellerId,
		    @Param("modelNo") String modelNo
		);

	
	@Modifying
	@Query("Update PurchaseTable a set a.is_deleted=1 where a.sale_id=:sale_id")
	int DeletePurchase(@Param("sale_id") Integer sale_id);

}
