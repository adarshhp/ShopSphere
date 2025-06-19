package com.example.customer.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.customer.model.CompanyView;

public interface CompanyViewRepository extends JpaRepository<CompanyView, Integer> {

}
