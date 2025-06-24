package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.ProductDetails;
import com.example.demo.response.PostResponse;

public interface ICompanyMgtService {
	public PostResponse postProduct(ProductDetails productDetails);
	public List<ProductDetails> getProducts(@RequestParam Integer company_id);
	public Optional<ProductDetails> getProductDetailsByModelNo(@RequestParam String Model_no);
	public List<ProductDetails> getProductsByModelNos(@RequestParam List<String> modelNos);
	public List<ProductDetails> getAllProducts();
	public PostResponse updateProduct(Integer prod_id, ProductDetails updatedProductDetails); 
	public PostResponse deleteProduct(Integer prod_id);
}
