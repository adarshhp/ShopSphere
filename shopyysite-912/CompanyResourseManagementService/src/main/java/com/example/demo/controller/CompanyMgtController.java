package com.example.demo.controller;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
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
	public List<ProductDetails> getProducts(@RequestParam Integer company_id){
		return service.getProducts(company_id);
	}
	@GetMapping("/getProductDetailsByModelNo")
	public ProductDetails getProductDetailsByModelNo(@RequestParam String Model_no) {
		return service.getProductDetailsByModelNo(Model_no);
	}
	
	@PostMapping("/products/by-models")
	public List<ProductDetails> getProductsByModelNos(@RequestBody List<String> modelNos) {
	   return  service.getProductsByModelNos(modelNos);
	}
}
 
 