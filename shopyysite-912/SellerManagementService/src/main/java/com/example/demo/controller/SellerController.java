package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.InventoryItem;
import com.example.demo.model.PurchaseTable;
import com.example.demo.response.PostResponse;
import com.example.demo.service.ISellerService;

@RestController
@RequestMapping("/")
public class SellerController {

	@Autowired
	private ISellerService isellerservice;
	
	public SellerController(ISellerService isellerservice) {
		this.isellerservice=isellerservice;
	}
	
	@PostMapping("/inventory")
	public PostResponse PostInventory(InventoryItem inventoryItem) {
		return isellerservice.PostInventory(inventoryItem);
	}
	
	@PostMapping("/purchase") 
	public PostResponse PostPurchase(PurchaseTable purchaseItem) {
		return isellerservice.PostPurchase(purchaseItem);
	}
	
	@GetMapping("/allinventory")
	public List<InventoryItem> GetAllInventory(@RequestParam Integer Seller_Id){
		return isellerservice.GetAllInventory(Seller_Id);
	}
	
	
}