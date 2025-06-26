package com.example.demo.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.ProductDetails;
import com.example.demo.response.PostResponse;

@Repository
public interface CompanyMgtRepository extends JpaRepository<ProductDetails, Integer> {
   
	@Query("Select u from ProductDetails u where u.Company_id = :company_id")
	public List<ProductDetails> getProducts(@Param("company_id") Integer company_id);
	
	@Query("Select u from ProductDetails u where u.Model_no=:Model_no")
	ProductDetails getProductDetailsByModelNo(@RequestParam String Model_no);
	
	@Query("SELECT u FROM ProductDetails u WHERE u.Model_no IN :modelNos")
	List<ProductDetails> getProductsByModelNos(@Param("modelNos") List<String> modelNos);
	
	@Modifying
	@Query("Update ProductDetails p set p.HolderStatus=:status where p.Model_no=:Model_no")
	Integer ChangeholderStatus(@RequestParam String Model_no,@RequestParam Integer status);
	
	@Query("SELECT u FROM ProductDetails u WHERE u.Model_no = :Model_no AND u.HolderStatus = :checkvalue-1")
	List<ProductDetails> CheckEligibility(
	    @Param("Model_no") String modelNo,
	    @Param("checkvalue") int checkvalue);

}
