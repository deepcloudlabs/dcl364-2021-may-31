package com.example.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crm.entity.CustomerEntity;

public interface CustomerEntityRepository extends JpaRepository<CustomerEntity, String> {

}
