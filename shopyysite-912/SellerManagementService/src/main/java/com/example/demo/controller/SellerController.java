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

import com.example.demo.model.InventoryItem;
import com.example.demo.model.PurchaseTable;
import com.example.demo.response.PostResponse;
import com.example.demo.service.ISellerService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class SellerController {

	@Autowired
	private ISellerService isellerservice;
	
	public SellerController(ISellerService isellerservice) {
		this.isellerservice=isellerservice;
	}
	
	@PostMapping("/inventory")
	public PostResponse PostInventory(@RequestBody InventoryItem inventoryItem) {
		return isellerservice.PostInventory(inventoryItem);
	}
	
	@PostMapping("/purchase") 
	public PostResponse PostPurchase(@RequestBody PurchaseTable purchaseItem) {
		return isellerservice.PostPurchase(purchaseItem);
	}
	
	@GetMapping("/GetPurchases")
	public List<PurchaseTable> GetPurchases(@RequestParam Integer Seller_Id){
		return isellerservice.GetPurchases(Seller_Id);
	}
	
	@GetMapping("/allinventory")
	public List<InventoryItem> GetAllInventory(@RequestParam Integer Seller_Id){
		return isellerservice.GetAllInventory(Seller_Id);
	}
	
	@PostMapping("/editinventory")
	public PostResponse editInventory(@RequestBody InventoryItem inventoryItem,@RequestParam Integer purchaseId) {
     return isellerservice.EditInventory(inventoryItem, purchaseId);
          }
	@GetMapping("/deleteinventory")
	public PostResponse DeleteInventory(@RequestParam Integer purchase_id) {
		return isellerservice.DeleteInventory(purchase_id);
	}
	
	@PostMapping("/editpurchase")
	public PostResponse EditPurchase(@RequestBody PurchaseTable purchaseItem,@RequestParam Integer sale_id) {
		return isellerservice.EditPurchase(purchaseItem,sale_id);
	}
	
	@GetMapping("/deletepurchase")
	public PostResponse DeletePurchase(@RequestParam Integer sale_id) {
		return isellerservice.DeletePurchase(sale_id);
	}
	
}