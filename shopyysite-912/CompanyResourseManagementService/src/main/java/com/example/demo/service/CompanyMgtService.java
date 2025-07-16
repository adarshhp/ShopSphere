package com.example.demo.service;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.ProductDetails;
import com.example.demo.repository.CompanyMgtRepository;
import com.example.demo.response.PostResponse;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
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
	public PostResponse bulkUploadProducts(@Parameter(description = "File containing product data", 
            schema = @Schema(type = "string", format = "binary"))
  @RequestParam("file") MultipartFile postedFile)  {
		PostResponse response = new PostResponse();
		if (postedFile != null && !postedFile.isEmpty() && !Objects.requireNonNull(postedFile.getOriginalFilename()).isEmpty()) {
	        String fileName = postedFile.getOriginalFilename();
	        String fileContentType = postedFile.getContentType();
	        String fileExtension = fileName.substring(fileName.lastIndexOf("."));

	        int rowCount = 0;
	        if (fileExtension == ".xls" || fileExtension == ".xlsx")
	        {
	        	try {
					InputStream stream = postedFile.getInputStream();
					
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        
	        System.out.println("File Name: " + fileName);
	        System.out.println("Content Type: " + fileContentType);
	        System.out.println("Extension: " + fileExtension);

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