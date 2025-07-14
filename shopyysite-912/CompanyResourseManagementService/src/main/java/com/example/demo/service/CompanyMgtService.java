package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.ProductDetails;
import com.example.demo.repository.CompanyMgtRepository;
import com.example.demo.response.PostResponse;

import jakarta.transaction.Transactional;

@Service
public class CompanyMgtService implements ICompanyMgtService {
	
	@Autowired
	private CompanyMgtRepository companyMgtRepository;
	public CompanyMgtService(CompanyMgtRepository companyMgtRepository) {
		this.companyMgtRepository=companyMgtRepository;
	}
	
	@Override
	public PostResponse postProduct(ProductDetails productDetails) {
	    PostResponse response = new PostResponse();
	    
	    try {
	        // The productDetails object now contains a list of base64 images
	        ProductDetails savedProduct = companyMgtRepository.save(productDetails);
	        
	        response.setStatusCode(200);
	        response.setMessage("Product created successfully with " + 
	                          savedProduct.getProductImages().size() + " images");
	    } catch (Exception e) {
	        response.setStatusCode(500);
	        response.setMessage("Error creating product: " + e.getMessage());
	    }
	    
	    return response;
	}

	@Override
	public Page<ProductDetails> getProducts(Integer companyId, Integer holderStatus, String productCategory, String ModelNo, LocalDate manDate, Pageable pageable) {
		return companyMgtRepository.getProducts(companyId, holderStatus, productCategory,ModelNo, manDate, pageable);
	}



@Override
public ProductDetails getProductDetailsByModelNo(@RequestParam String Model_no) {
	return companyMgtRepository.getProductDetailsByModelNo(Model_no);
}

@Override
public List<ProductDetails> getProductsByModelNos(@RequestParam List<String> modelNos){
	return companyMgtRepository.getProductsByModelNos(modelNos);
}

@Override
public Boolean CheckEligibility(@RequestParam String Model_no, @RequestParam Integer checkvalue) {
    List<ProductDetails> mm = companyMgtRepository.CheckEligibility(Model_no, checkvalue);
    if (mm.size() > 0) {
        return true;
    } else {
        return false;
    }
}


@Transactional
@Override
public PostResponse ChangeholderStatus(@RequestParam String Model_no,@RequestParam Integer status) {
	Integer ff = companyMgtRepository.ChangeholderStatus(Model_no,status);
	PostResponse pr=new PostResponse();
	if(ff>0) {
		pr.setMessage("Updated");
		pr.setStatusCode(200);
	}else {
		pr.setMessage("Cant Update");
		pr.setStatusCode(404);
	}
	return pr;
}


}