package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.InventoryItem;
import com.example.demo.model.PurchaseTable;
import com.example.demo.repository.PurchaseRepository;
import com.example.demo.repository.SellerRepository;
import com.example.demo.response.PostResponse;

@Service
public class SellerService implements ISellerService {
	
	private SellerRepository sellerRepository;
	private PurchaseRepository purchaseRepository;
	public SellerService(SellerRepository sellerRepository,PurchaseRepository purchaseRepository) {
		this.sellerRepository=sellerRepository;
		this.purchaseRepository=purchaseRepository;
	}
	
	public PostResponse PostInventory(InventoryItem inventoryItem) {
		InventoryItem item = sellerRepository.save(inventoryItem);
		PostResponse resp=new PostResponse();
		if(item!=null && item.getCompany_id()!=null) {
			resp.setStatusCode(200);
			resp.setMessage("Successfully Posted");
		}else {
			resp.setStatusCode(400);
			resp.setMessage("Couldnt save");
		}
		return resp;
	}
	public PostResponse PostPurchase(PurchaseTable purchaseItem) {
		PurchaseTable purchase=purchaseRepository.save(purchaseItem);
		PostResponse resp=new PostResponse();
		if(purchase!=null&& purchase.getSale_id()!=null) {
			resp.setStatusCode(200);
			resp.setMessage("Successfully Posted");
		}else {
			resp.setStatusCode(400);
			resp.setMessage("Couldnt save");
		}
		return resp;
	}
	
	public List<InventoryItem> GetAllInventory(@RequestParam Integer Seller_Id){
		return sellerRepository.GetAllInventory(Seller_Id);
	}

}
