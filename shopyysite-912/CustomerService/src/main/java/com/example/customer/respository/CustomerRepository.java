package com.example.customer.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.customer.model.CustomerDetails;

public interface CustomerRepository extends JpaRepository<CustomerDetails, Integer> {
	
	@Query("SELECT c FROM CustomerDetails c WHERE c.is_deleted = false")
	List<CustomerDetails> findAllActiveCustomers();

	@Query("SELECT c FROM CustomerDetails c WHERE c.customerId = :customerId AND c.is_deleted = false")
	List<CustomerDetails> findByCustomerIdAndNotDeleted(@Param("customerId") Integer customerId);

}
