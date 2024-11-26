package com.employeeTracking.OAuth2Demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeeTracking.OAuth2Demo.model.Admin;
import com.employeeTracking.OAuth2Demo.model.Employee;
import com.employeeTracking.OAuth2Demo.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/register")
    public String registerEmployee(@RequestBody Admin admin){
        System.out.println("EmployeeController.registerEmployee()"+admin);
        System.out.println("EmployeeController.registerEmployee()"+admin.getName());
        adminService.registerAdmin(admin);
        return "Admin registered successfully";
    }

    @PostMapping("/signin")
    public String signIn(@RequestBody Employee employee){
        return adminService.signIn(employee.getName(), employee.getPassword());
    }
}
