package com.example.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventory_table")
public class InventoryItem {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer purchase_id;
	private String Model_no;
	private Integer Company_id;
	private Integer Category_id;
	private LocalDate purchase_date;
	private Integer price;
	private Integer warranty;
	private String image;
	private Integer seller_id;
	private Integer is_deleted = 0;
	public Integer getSeller_id() {
		return seller_id;
	}
	public Integer getIs_deleted() {
		return is_deleted;
	}
	public void setIs_deleted(Integer is_deleted) {
		this.is_deleted = is_deleted;
	}
	public void setSeller_id(Integer seller_id) {
		this.seller_id = seller_id;
	}
	public Integer getPurchase_id() {
		return purchase_id;
	}
	public void setPurchase_id(Integer purchase_id) {
		this.purchase_id = purchase_id;
	}
	public String getModel_no() {
		return Model_no;
	}
	public void setModel_no(String model_no) {
		Model_no = model_no;
	}
	public Integer getCompany_id() {
		return Company_id;
	}
	public void setCompany_id(Integer company_id) {
		Company_id = company_id;
	}
	public Integer getCategory_id() {
		return Category_id;
	}
	public void setCategory_id(Integer category_id) {
		Category_id = category_id;
	}
	public LocalDate getPurchase_date() {
		return purchase_date;
	}
	public void setPurchase_date(LocalDate purchase_date) {
		this.purchase_date = purchase_date;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getWarranty() {
		return warranty;
	}
	public void setWarranty(Integer warranty) {
		this.warranty = warranty;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
}
