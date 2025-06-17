package com.example.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "purchase_table")
public class PurchaseTable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer sale_id;
private Integer customer_id;
private String Model_no;
private Integer Price;
private LocalDate purchase_date;
private Integer Warranty;
private String Name;
private String Email;
private Integer Phono;
public Integer getSale_id() {
	return sale_id;
}
public void setSale_id(Integer sale_id) {
	this.sale_id = sale_id;
}
public Integer getCustomer_id() {
	return customer_id;
}
public void setCustomer_id(Integer customer_id) {
	this.customer_id = customer_id;
}
public String getModel_no() {
	return Model_no;
}
public void setModel_no(String model_no) {
	Model_no = model_no;
}
public Integer getPrice() {
	return Price;
}
public void setPrice(Integer price) {
	Price = price;
}
public LocalDate getPurchase_date() {
	return purchase_date;
}
public void setPurchase_date(LocalDate purchase_date) {
	this.purchase_date = purchase_date;
}
public Integer getWarranty() {
	return Warranty;
}
public void setWarranty(Integer warranty) {
	Warranty = warranty;
}
public String getName() {
	return Name;
}
public void setName(String name) {
	Name = name;
}
public String getEmail() {
	return Email;
}
public void setEmail(String email) {
	Email = email;
}
public Integer getPhono() {
	return Phono;
}
public void setPhono(Integer phono) {
	Phono = phono;
}
}
