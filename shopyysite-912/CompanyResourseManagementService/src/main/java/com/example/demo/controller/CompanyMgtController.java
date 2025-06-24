package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ProductDetails;
import com.example.demo.response.PostResponse;
import com.example.demo.service.ICompanyMgtService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class CompanyMgtController {
	
	@Autowired
	private  ICompanyMgtService service;
	
	public CompanyMgtController(ICompanyMgtService service) {
		this.service=service;
	}
	
	@PostMapping("/postProduct")
	public PostResponse PostProduct(@Valid @RequestBody ProductDetails productDetails) {
		return service.postProduct(productDetails);
	}
	
	@GetMapping("/products") 
    public List<ProductDetails> getAllProducts() {
        return service.getAllProducts();
    }
	
	@GetMapping("/productsByCompanyID")
	public List<ProductDetails> getProducts(@RequestParam Integer company_id){
		return service.getProducts(company_id);
	}
	@GetMapping("/getProductDetailsByModelNo")
	public Optional<ProductDetails> getProductDetailsByModelNo(@RequestParam String Model_no) {
		return service.getProductDetailsByModelNo(Model_no);
	}
	
	@PostMapping("/products/by-models")
	public List<ProductDetails> getProductsByModelNos(@RequestBody List<String> modelNos) {
	   return  service.getProductsByModelNos(modelNos);
	}
	
	@PostMapping("/products/update/{id}") 
    public PostResponse updateProduct(@PathVariable("id") Integer id,@Valid @RequestBody ProductDetails productDetails,BindingResult bindingResult) {
        return service.updateProduct(id, productDetails);
    }

	
	@PostMapping("/products/delete/{id}")
    public PostResponse deleteProduct(@PathVariable("id") Integer id) {
        return service.deleteProduct(id);
    }
}
