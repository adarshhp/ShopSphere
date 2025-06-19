package com.example.customer.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="customer_details_table")
public class CustomerDetails {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer purchase_Id;
	public boolean isIs_deleted() {
		return is_deleted;
	}
	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}
	private Integer customerId;
	private String model_no;
	private LocalDate purchase_date;
	private LocalDate warranty_date;
	private LocalDate expiry_date;
    private boolean is_deleted = false;
	
	public Integer getPurchase_Id() {
		return purchase_Id;
	}
	public void setPurchase_Id(Integer purchase_Id) {
		this.purchase_Id = purchase_Id;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getModel_no() {
		return model_no;
	}
	public void setModel_no(String model_no) {
		this.model_no = model_no;
	}
	public LocalDate getPurchase_date() {
		return purchase_date;
	}
	public void setPurchase_date(LocalDate purchase_date) {
		this.purchase_date = purchase_date;
	}
	public LocalDate getWarranty_date() {
		return warranty_date;
	}
	public void setWarranty_date(LocalDate warranty_date) {
		this.warranty_date = warranty_date;
	}
	public LocalDate getExpiry_date() {
		return expiry_date;
	}
	public void setExpiry_date(LocalDate expiry_date) {
		this.expiry_date = expiry_date;
	}

	
}
