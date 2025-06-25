package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.InventoryItem;
import com.example.demo.model.PurchaseTable;
import com.example.demo.response.PostResponse;

public interface ISellerService {
	public PostResponse PostInventory(InventoryItem inventoryItem);
	public PostResponse PostPurchase(PurchaseTable purchaseItem);
	public List<InventoryItem> GetAllInventory(
	        Integer sellerId,
	        Integer categoryId,
	        String modelNo,
	        Integer warranty,
	        LocalDate purchaseDate
	    );
	public List<PurchaseTable> GetPurchases(Integer sellerId, String modelNo);
	public PostResponse EditInventory(@RequestBody InventoryItem newItem,@RequestParam Integer purchaseId);	
	public PostResponse DeleteInventory(@RequestParam Integer purchase_id);
	public PostResponse EditPurchase(@RequestBody PurchaseTable purchaseItem, @RequestParam Integer sale_id);
	public PostResponse DeletePurchase(@RequestParam Integer sale_id);

	}
