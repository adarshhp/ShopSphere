package com.example.customer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class CustomerServiceController {
	@Autowired
	private ICustomerService service;
	
	 private ResponseEntity<?> handleValidationErrors(BindingResult bindingResult) {
	        Map<String, String> errors = new HashMap<>();
	        for (FieldError error : bindingResult.getFieldErrors()) {
	            errors.put(error.getField(), error.getDefaultMessage());
	        }
	        return ResponseEntity.badRequest().body(errors);
	    }

	    @PostMapping("/register-warranty")
	    public ResponseEntity<?> registercustomer(@Valid @RequestBody CustomerRegPayload customerRegPayload,
	                                              BindingResult bindingResult) {
	        if (bindingResult.hasErrors()) {
	            return handleValidationErrors(bindingResult);
	        }
	        PostResponse response = service.registercustomer(customerRegPayload);
	        return ResponseEntity.ok(response);
	    }
	    
	    @PostMapping("/raise-warranty-request")
	    public ResponseEntity<?> raiseWarrantyRequest(@Valid @RequestBody RaiseWarrantyPayload view,
	                                                  BindingResult bindingResult) {
	        if (bindingResult.hasErrors()) {
	            return handleValidationErrors(bindingResult);
	        }
	        PostResponse response = service.raiseWarrantyRequest(view);
	        return ResponseEntity.ok(response);
	    }
	
	@GetMapping("/warranty-requests-customer")
	public List<CustomerDetails> getWarrantyRequests(@RequestParam Integer customerId) {
	    return service.getWarrantyRequests(customerId);
	}
	
//	@PostMapping("/raise-warranty-request")
//	public PostResponse raiseWarrantyRequest(@Valid @RequestBody RaiseWarrantyPayload view) {
//	    return service.raiseWarrantyRequest(view);
//	}
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
