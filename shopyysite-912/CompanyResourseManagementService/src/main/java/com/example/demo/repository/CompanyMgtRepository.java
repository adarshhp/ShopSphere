package com.example.demo.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.ProductDetails;

@Repository
public interface CompanyMgtRepository extends JpaRepository<ProductDetails, Integer> {
   
	@Query("Select u from ProductDetails u where u.Company_id = :company_id")
	public List<ProductDetails> getProducts(@Param("company_id") Integer company_id);
}
