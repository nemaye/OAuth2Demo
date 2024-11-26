package com.employeeTracking.OAuth2Demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.employeeTracking.OAuth2Demo.entity.*;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    EmployeeEntity findByName(String name);
}
