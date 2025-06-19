package com.example.customer.service;

import java.util.List;

import com.example.customer.model.CompanyView;
import com.example.customer.model.CustomerDetails;

public interface ICustomerService {
    CustomerDetails registerCustomer(CustomerDetails customerDetails);
    List<CustomerDetails> getAllCustomers();
    
    CompanyView saveWarrantyRequest(CompanyView view);
    List<CompanyView> getAllWarrantyRequests();
    
    List<CustomerDetails> getCustomerDetailsByCustomerId(Integer customerId);
    
    CustomerDetails updateCustomer(Integer purchase_Id, CustomerDetails updatedCustomer);
    String deleteCustomer(Integer purchase_Id);
}