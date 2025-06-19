package com.example.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.customer.model.CompanyView;
import com.example.customer.model.CustomerDetails;
import com.example.customer.service.ICustomerService;

@RestController
@RequestMapping("/")
public class CustomerServiceController {
	@Autowired
	private ICustomerService service;
	
	@PostMapping("/register")
	public CustomerDetails registercustomer(@RequestBody CustomerDetails customerdetails) {
		return service.registerCustomer(customerdetails);
	}
	@GetMapping("/all-registered-customers")
	    public List<CustomerDetails> getAllCustomers() {
	        return service.getAllCustomers();
	}
	@PostMapping("/warranty-request")
	public ResponseEntity<CompanyView> createWarrantyRequest(@RequestBody CompanyView view) {
	    return ResponseEntity.ok(service.saveWarrantyRequest(view));
	}
	@GetMapping("/warranty-requests")
	public ResponseEntity<List<CompanyView>> getWarrantyRequests() {
	    return ResponseEntity.ok(service.getAllWarrantyRequests());
	}
	@GetMapping("/getByCustomerId")
    public List<CustomerDetails> getCustomerDetailsByCustomerId(@RequestParam Integer customer_Id) {
        return service.getCustomerDetailsByCustomerId(customer_Id);
    }
	 @PostMapping("/edit")
	    public CustomerDetails editCustomer(@RequestParam Integer purchase_Id, @RequestBody CustomerDetails updatedCustomer) {
	        return service.updateCustomer(purchase_Id, updatedCustomer);
	}

	 @PostMapping("/delete")
	    public String deleteCustomer(@RequestParam Integer purchase_Id) {
	        return service.deleteCustomer(purchase_Id);
	 }
}
