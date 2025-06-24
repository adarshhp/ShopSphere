package com.example.customer.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="copmany_view_table")
public class CompanyView {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer warranty_request_id;
	private Integer customer_id;
	private LocalDate request_date;
	private String customer_name;
	private String customer_email;
	private Integer phone_number;
	private String model_no;
	private LocalDate purchase_date;
	private LocalDate warranty_period;
	private String image;
	private Integer warranty_status=1;
	private Integer company_id;
    private Integer isDeleted=0;
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Integer getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}
	public Integer getWarranty_request_id() {
		return warranty_request_id;
	}
	public void setWarranty_request_id(Integer warranty_request_id) {
		this.warranty_request_id = warranty_request_id;
	}
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	public LocalDate getRequest_date() {
		return request_date;
	}
	public void setRequest_date(LocalDate request_date) {
		this.request_date = request_date;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getCustomer_email() {
		return customer_email;
	}
	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}
	public Integer getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(Integer phone_number) {
		this.phone_number = phone_number;
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
	public LocalDate getWarranty_period() {
		return warranty_period;
	}
	public void setWarranty_period(LocalDate warranty_period) {
		this.warranty_period = warranty_period;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getWarranty_status() {
		return warranty_status;
	}
	public void setWarranty_status(Integer warranty_status) {
		this.warranty_status = warranty_status;
	}
	
	
}