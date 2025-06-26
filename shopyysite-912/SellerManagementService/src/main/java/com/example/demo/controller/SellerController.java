package com.example.demo.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
public class SellerController {

	@Autowired
	private ISellerService isellerservice;
	
	public SellerController(ISellerService isellerservice) {
		this.isellerservice=isellerservice;
	}
	
	@PostMapping("/purchase")
	public ResponseEntity<?> postPurchase(@Valid @RequestBody PurchaseTable purchaseItem, BindingResult bindingResult) {
 
	    if (bindingResult.hasErrors()) {
	        List<String> errors = bindingResult.getFieldErrors().stream()
	            .map(err -> err.getField() + ": " + err.getDefaultMessage())
	            .toList();
	        return ResponseEntity.badRequest().body(errors);
	    }
 
	    PostResponse response = isellerservice.PostPurchase(purchaseItem);
	    return ResponseEntity.ok(response);
	}
 

	@PostMapping("/inventory")
	public ResponseEntity<?> PostInventory(@Valid @RequestBody InventoryItem inventoryItem, BindingResult bindingResult) {
	    if (bindingResult.hasErrors()) {
	        Map<String, String> errors = new HashMap<>();
	        bindingResult.getFieldErrors().forEach(error ->
	            errors.put(error.getField(), error.getDefaultMessage())
	        );
	        return ResponseEntity.badRequest().body(errors);
	    }

	    PostResponse response = isellerservice.PostInventory(inventoryItem);
	    return ResponseEntity.ok(response);
	}

	
	@GetMapping("/GetPurchases")
	public List<PurchaseTable> GetPurchases(@RequestParam(required = false) Integer Seller_Id, 
	                                        @RequestParam(required = false) String modelNo) {
	    return isellerservice.GetPurchases(Seller_Id,modelNo);
	}

	
	@GetMapping("/allinventory")
	public List<InventoryItem> GetAllInventory(
	        @RequestParam Integer Seller_Id,
	        @RequestParam(required = false) Integer categoryId,
	        @RequestParam(required = false) String modelNo,
	        @RequestParam(required = false) Integer warranty,
	        @RequestParam(required = false)
	        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate purchaseDate) {

	    return isellerservice.GetAllInventory(Seller_Id, categoryId, modelNo, warranty, purchaseDate);
	}

	
	@PostMapping("/editinventory")
	public PostResponse editInventory(@RequestBody InventoryItem inventoryItem,@RequestParam Integer purchaseId) {
     return isellerservice.EditInventory(inventoryItem, purchaseId);
          }
	@PostMapping("/deleteinventory")
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