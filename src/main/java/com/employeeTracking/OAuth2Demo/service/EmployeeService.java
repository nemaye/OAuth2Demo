package com.employeeTracking.OAuth2Demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.employeeTracking.OAuth2Demo.config.JWTConfig;
import com.employeeTracking.OAuth2Demo.entity.EmployeeEntity;
import com.employeeTracking.OAuth2Demo.model.Employee;
import com.employeeTracking.OAuth2Demo.repository.EmployeeRepository;

@Service
public class EmployeeService {
    
    @Autowired
    EmployeeRepository employeeRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JWTConfig jwtConfig;
    
    public void registerEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(employee.getId());
        employeeEntity.setName(employee.getName());
        employeeEntity.setRole(employee.getRole());
        employeeEntity.setPassword(passwordEncoder.encode(employee.getPassword()));
        employeeRepository.save(employeeEntity);
    }

    public String signIn(String name, String password) {
        EmployeeEntity employeeEntity = employeeRepository.findByName(name);

        if(employeeEntity == null){
            return "Employee not found";
            // throw new RuntimeException("Employee not found");
        }
        if(passwordEncoder.matches(password, employeeEntity.getPassword())){
        // if(!employeeEntity.getPassword().equals(passwordEncoder.encode("password"))){
            List<String> roles = List.of("EMPLOYEE");
            String token = jwtConfig.generateToken(name, roles);
            System.out.println("Employee Token: "+token);
            return token;
            // throw new RuntimeException("Invalid password");
        }
        return "Invalid password"; 
    }

    public EmployeeEntity getEmployeeDetails(int id) {
        EmployeeEntity employeeEntity = employeeRepository.findById((long) id).get();
        // System.out.println(employeeEntity);
        return employeeEntity;
    }
}
