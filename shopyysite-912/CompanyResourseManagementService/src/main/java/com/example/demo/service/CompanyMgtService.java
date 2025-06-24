package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
public Optional<ProductDetails> getProductDetailsByModelNo(@RequestParam String Model_no) {
	return companyMgtRepository.getProductDetailsByModelNo(Model_no);
}

@Override
public List<ProductDetails> getProductsByModelNos(@RequestParam List<String> modelNos){
	return companyMgtRepository.getProductsByModelNos(modelNos);
}

@Override
public List<ProductDetails> getAllProducts() {
	return companyMgtRepository.findAll();
}

@Override
public PostResponse updateProduct(Integer prod_id, ProductDetails updatedProductDetails) {
	PostResponse response = new PostResponse();
	 Optional<ProductDetails> existingProductOptional = companyMgtRepository.findById(prod_id);
     if (existingProductOptional.isEmpty()) {
         response.setStatusCode(404); 
         response.setMessage("Product with ID " + prod_id + " not found.");
         return response;
     }
     
     ProductDetails existingProduct = existingProductOptional.get();
     
     if (!existingProduct.getModel_no().equals(updatedProductDetails.getModel_no())) {
         if (companyMgtRepository.getProductDetailsByModelNo(updatedProductDetails.getModel_no()).isPresent()) {
             response.setStatusCode(409); // Conflict
             response.setMessage("Another product with model number '" + updatedProductDetails.getModel_no() + "' already exists.");
             return response;
         }
     }
     
     existingProduct.setModel_no(updatedProductDetails.getModel_no());
     existingProduct.setProduct_name(updatedProductDetails.getProduct_name());
     existingProduct.setProduct_category(updatedProductDetails.getProduct_category());
     existingProduct.setProduct_price(updatedProductDetails.getProduct_price());
     existingProduct.setMan_date(updatedProductDetails.getMan_date());
     existingProduct.setWarrany_tenure(updatedProductDetails.getWarrany_tenure());
     existingProduct.setProduct_image(updatedProductDetails.getProduct_image());
     existingProduct.setCompany_id(updatedProductDetails.getCompany_id());
     
     if (existingProduct.getMan_date() != null && existingProduct.getMan_date().isAfter(LocalDate.now())) {
         response.setStatusCode(400); // Bad Request
         response.setMessage("Manufacture date cannot be in the future.");
         return response;
     }
     
	 return response;
}

@Override
public PostResponse deleteProduct(Integer prod_id) {
	PostResponse response = new PostResponse();
	
	if (!companyMgtRepository.existsById(prod_id)) {
        response.setStatusCode(404); 
        response.setMessage("Product with ID " + prod_id + " not found.");
        return response;
    }
	return response;
}

}
