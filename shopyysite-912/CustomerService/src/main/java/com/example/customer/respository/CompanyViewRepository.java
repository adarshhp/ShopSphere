package com.example.customer.respository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.customer.model.CompanyView;
import com.example.customer.model.CustomerDetails;

public interface CompanyViewRepository extends JpaRepository<CompanyView, Integer> {

	@Query("Select c from CompanyView c where c.customer_id=:userId")
	 List<CompanyView> getRaisedWarrantyRequestsForCustomer(@RequestParam Integer userId);
	
	@Modifying
	@Query("Update CompanyView c set c.warranty_status=:status where c.warranty_request_id=:purchase_id")
	Integer WarrantyAction(@RequestParam Integer purchase_id,@RequestParam Integer status);
	
	@Modifying
	@Query("UPDATE CompanyView c set c.isDeleted=1 where c.warranty_request_id=:raised_Id")
	Integer deleteRaisedWarranty(@RequestParam Integer raised_Id);
	
	@Query("SELECT c FROM CompanyView c WHERE " +
	           "(:status IS NULL OR c.warranty_status = :status) AND " +
	           "(:modelNo IS NULL OR c.model_no LIKE %:modelNo%) AND " +
	           "(:purchaseDate IS NULL OR c.purchase_date = :purchaseDate) AND " + 
	           "(:warrantyPeriod IS NULL OR c.warranty_period = :warrantyPeriod) AND " +
	           "(:customerId IS NULL OR c.customer_id = :customerId) AND " + 
	           "(:requestDateStart IS NULL OR c.request_date >= :requestDateStart) AND " +
	           "(:requestDateEnd IS NULL OR c.request_date <= :requestDateEnd)")
	List<CompanyView> findFilteredCompanyViews(
	        @Param("status") Integer status,
	        @Param("modelNo") String modelNo,
	        @Param("purchaseDate") LocalDate purchaseDate, 
	        @Param("warrantyPeriod") LocalDate warrantyPeriod, 
	        @Param("customerId") Integer customerId, 
	        @Param("requestDateStart") LocalDate requestDateStart,
	        @Param("requestDateEnd") LocalDate requestDateEnd
	    );
	@Query("SELECT c FROM CompanyView c WHERE c.customer_id = :userId AND " +
	           "(:status IS NULL OR c.warranty_status = :status) AND " +
	           "(:modelNo IS NULL OR c.model_no LIKE %:modelNo%)")
	    List<CompanyView> findRaisedWarrantyRequestsForCustomerFiltered(
	        @Param("userId") Integer userId,
	        @Param("status") Integer status,
	        @Param("modelNo") String modelNo
	    );
}
