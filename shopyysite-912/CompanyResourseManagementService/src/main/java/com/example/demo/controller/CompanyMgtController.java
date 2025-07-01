package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ProductDetails;
import com.example.demo.response.PostResponse;
import com.example.demo.service.ICompanyMgtService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class CompanyMgtController {
	
	@Autowired
	private  ICompanyMgtService service;
	
	public CompanyMgtController(ICompanyMgtService service) {
		this.service=service;
	}
	
	@PostMapping("/postproduct")
	public PostResponse PostProduct(@RequestBody ProductDetails productDetails) {
		return service.postProduct(productDetails);
	}
	
	
	@GetMapping("/getProducts")
	public Page<ProductDetails> getProducts(
	        @RequestParam Integer company_id,
	        @RequestParam(required = false) Integer holderStatus,
	        @RequestParam(required = false) String productCategory,
	        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate manDate,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int size) {

	    String sanitizedCategory = (productCategory == null || productCategory.trim().isEmpty()) ? null : productCategory.trim();

	    Pageable pageable = PageRequest.of(page, size);
	    return service.getProducts(company_id, holderStatus, sanitizedCategory, manDate, pageable);
	}
	
	@GetMapping("/getProductDetailsByModelNo")
	public ProductDetails getProductDetailsByModelNo(@RequestParam String Model_no) {
		return service.getProductDetailsByModelNo(Model_no);
	}
	
	@PostMapping("/products/by-models")
	public List<ProductDetails> getProductsByModelNos(@RequestBody List<String> modelNos) {
	   return  service.getProductsByModelNos(modelNos);
	}
	
	@PostMapping("/changeholderstatus")
	public PostResponse ChangeholderStatus(@RequestParam String Model_no,@RequestParam Integer status) {
		return service.ChangeholderStatus(Model_no,status);
	}
	@GetMapping("/checkeligibility")
	public Boolean CheckEligibility(@RequestParam String Model_no,@RequestParam Integer checkvalue) {
		return service.CheckEligibility(Model_no,checkvalue);
	}
}