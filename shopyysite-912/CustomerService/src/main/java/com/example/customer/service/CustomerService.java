package com.example.customer.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.customer.model.CompanyView;
import com.example.customer.model.CustomerDetails;
import com.example.customer.respository.CompanyViewRepository;
import com.example.customer.respository.CustomerRepository;

@Service
public class CustomerService implements ICustomerService  {
	@Autowired
	private CustomerRepository repository;
	private CompanyViewRepository companyviewrepository;
	
	@Override
	public CustomerDetails registerCustomer(CustomerDetails customerDetails) {
	    customerDetails.setPurchase_Id(null); // force insert
	    LocalDate expiry = customerDetails.getPurchase_date().plusYears(1); // or based on your logic
	    customerDetails.setExpiry_date(expiry);
	    return repository.save(customerDetails);
	}

	@Override
	public List<CustomerDetails> getAllCustomers() {
	    return repository.findAllActiveCustomers();
	}
	
	@Override
	public CompanyView saveWarrantyRequest(CompanyView view) {
        return companyviewrepository.save(view);
    }

    @Override
    public List<CompanyView> getAllWarrantyRequests() {
        return companyviewrepository.findAll();
    }
    
    @Override
    public List<CustomerDetails> getCustomerDetailsByCustomerId(Integer customerId) {
        return repository.findByCustomerIdAndNotDeleted(customerId);
    }
    @Override
    public CustomerDetails updateCustomer(Integer purchase_Id, CustomerDetails updatedCustomer) {
        Optional<CustomerDetails> optional = repository.findById(purchase_Id);
        if (optional.isPresent()) {
            CustomerDetails existing = optional.get();
            existing.setCustomerId(updatedCustomer.getCustomerId());
            existing.setModel_no(updatedCustomer.getModel_no());
            existing.setPurchase_date(updatedCustomer.getPurchase_date());
            existing.setWarranty_date(updatedCustomer.getWarranty_date());
            existing.setExpiry_date(updatedCustomer.getExpiry_date());
            return repository.save(existing);
        } else {
            throw new RuntimeException("Customer not found with ID: " + purchase_Id);
        }
    }

    @Override
    public String deleteCustomer(Integer purchase_Id) {
        Optional<CustomerDetails> optional = repository.findById(purchase_Id);
        if (optional.isPresent()) {
            CustomerDetails customer = optional.get();
            customer.setIs_deleted(false);
            repository.save(customer);
            return "Customer marked as deleted with ID: " + purchase_Id;
        } else {
            return "Customer not found with ID: " + purchase_Id;
        }
    }
}
