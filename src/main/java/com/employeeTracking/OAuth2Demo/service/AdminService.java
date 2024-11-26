package com.employeeTracking.OAuth2Demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.employeeTracking.OAuth2Demo.config.JWTConfig;
import com.employeeTracking.OAuth2Demo.entity.AdminEntity;
import com.employeeTracking.OAuth2Demo.entity.EmployeeEntity;
import com.employeeTracking.OAuth2Demo.model.Admin;
import com.employeeTracking.OAuth2Demo.repository.AdminRepository;

@Service
public class AdminService {
    
    @Autowired
    AdminRepository adminRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JWTConfig jwtConfig;

    public void registerAdmin(Admin admin) {
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setId(admin.getId());
        adminEntity.setName(admin.getName());
        adminEntity.setRole(admin.getRole());
        adminEntity.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminRepository.save(adminEntity);
    }

    public String signIn(String name, String password) {
        AdminEntity adminEntity = adminRepository.findByName(name);

        if(adminEntity == null){
            return "Admin not found";
            // throw new RuntimeException("Employee not found");
        }
        if(passwordEncoder.matches(password, adminEntity.getPassword())){
        // if(!employeeEntity.getPassword().equals(passwordEncoder.encode("password"))){
            List<String> roles = List.of("ADMIN");
            String token = jwtConfig.generateToken(name, roles);
            System.out.println("Admin Token: "+token);
            return token;
            // throw new RuntimeException("Invalid password");
        }
        return "Invalid password"; 
    }

    public AdminEntity getEmployeeDetails(int id) {
        AdminEntity adminEntity = adminRepository.findById((long) id).get();
        // System.out.println(employeeEntity);
        return adminEntity;
    }
}
