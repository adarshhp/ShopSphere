package com.example.customer.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
}
