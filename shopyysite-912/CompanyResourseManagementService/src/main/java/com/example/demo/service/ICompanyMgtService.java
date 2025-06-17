package com.example.demo.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.ProductDetails;
import com.example.demo.response.PostResponse;

public interface ICompanyMgtService {
	public PostResponse postProduct(ProductDetails productDetails);
	public List<ProductDetails> getProducts(@RequestParam Integer company_id);
}
