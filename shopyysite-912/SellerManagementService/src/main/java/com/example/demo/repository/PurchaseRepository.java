package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.PurchaseTable;

public interface PurchaseRepository extends JpaRepository<PurchaseTable,Integer> {

}
