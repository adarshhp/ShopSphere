package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.ProductDetails;
import com.example.demo.repository.CompanyMgtRepository;
import com.example.demo.response.PostResponse;

@Service
public class CompanyMgtService implements ICompanyMgtService {
	
	@Autowired
	private CompanyMgtRepository companyMgtRepository;
	public CompanyMgtService(CompanyMgtRepository companyMgtRepository) {
		this.companyMgtRepository=companyMgtRepository;
	}
	
	@Override
	public PostResponse postProduct(ProductDetails productDetails) {
	    ProductDetails savedProduct = companyMgtRepository.save(productDetails);

	    PostResponse response = new PostResponse();
	    if (savedProduct != null && savedProduct.getProd_id() != null) { 
	        response.setStatusCode(200);
	        response.setMessage("Done");
	    } else {
	        response.setStatusCode(400);
	        response.setMessage("Can't Do");
	    }

	    return response;
	}

@Override
public List<ProductDetails> getProducts(@RequestParam Integer company_id){
	return companyMgtRepository.getProducts(company_id);
}

@Override
public ProductDetails getProductDetailsByModelNo(@RequestParam String Model_no) {
	return companyMgtRepository.getProductDetailsByModelNo(Model_no);
}

@Override
public List<ProductDetails> getProductsByModelNos(@RequestParam List<String> modelNos){
	return companyMgtRepository.getProductsByModelNos(modelNos);
}

}
