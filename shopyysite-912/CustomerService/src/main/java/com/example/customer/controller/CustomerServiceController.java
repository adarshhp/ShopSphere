package com.example.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.customer.model.CompanyView;
import com.example.customer.model.CustomerDetails;
import com.example.customer.service.ICustomerService;
import com.example.demo.payload.CustomerRegPayload;
import com.example.demo.payload.PostResponse;
import com.example.demo.payload.RaiseWarrantyPayload;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class CustomerServiceController {
	@Autowired
	private ICustomerService service;
	
	@PostMapping("/register-warranty")
	public PostResponse registercustomer(@RequestBody CustomerRegPayload customerRegPayload) {
		return service.registercustomer(customerRegPayload);
	}
	
	@GetMapping("/warranty-requests-customer")
	public List<CustomerDetails> getWarrantyRequests(@RequestParam Integer customerId) {
	    return service.getWarrantyRequests(customerId);
	}
	
	@PostMapping("/raise-warranty-request")
	public PostResponse raiseWarrantyRequest(@RequestBody RaiseWarrantyPayload view) {
	    return service.raiseWarrantyRequest(view);
	}
	@GetMapping("/raised-warranty-requests-customer")
	public List<CompanyView> getRaisedWarrantyRequestsForCustomer(@RequestParam Integer userId){
		return service.getRaisedWarrantyRequestsForCustomer(userId);
	}
	
	@GetMapping("/getraised-warranty-requests")
    public List<CompanyView> getWarrayRequestsByCustomers(Integer company_id) {
        return service.getWarrayRequestsByCustomers(company_id);
    }
	
	
	 @PostMapping("/editregistered-warranty")
	    public CustomerDetails editCustomer(@RequestParam Integer purchase_Id, @RequestBody CustomerDetails updatedCustomer) {
	        return service.updateCustomer(purchase_Id, updatedCustomer);
	}

	 @PostMapping("/delete-registered-warranty")
	    public String deleteCustomer(@RequestParam Integer purchase_Id) {
	        return service.deleteCustomer(purchase_Id);
	 }
	 
	 @GetMapping("/warranty-action")
	 public PostResponse WarrantyAction(@RequestParam Integer purchase_id,@RequestParam Integer status) {
		 return service.WarrantyAction(purchase_id,status);
	 }
}
