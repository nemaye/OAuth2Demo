package com.employeeTracking.OAuth2Demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employeeTracking.OAuth2Demo.entity.AdminEntity;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    AdminEntity findByName(String name);
    
}
