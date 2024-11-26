package com.employeeTracking.OAuth2Demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeeTracking.OAuth2Demo.entity.EmployeeEntity;
import com.employeeTracking.OAuth2Demo.model.Employee;
import com.employeeTracking.OAuth2Demo.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/employee")
public class EmployeeController {
    
    @Autowired
    EmployeeService employeeService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public String getMethodName() {
        return "hello this should be accessible by admin but not employee";
    }
    
    @PreAuthorize("hasAnyRole('EMPLOYEE', 'ADMIN')")
    @GetMapping("/{id}")
    public String getEmployeeDetails(@PathVariable int id) {
        EmployeeEntity employeeEntity = employeeService.getEmployeeDetails(id);
        return "hello: "+employeeEntity.getName() + " " + employeeEntity.getRole();
    }

    @GetMapping("/hello")
    @PreAuthorize("hasRole('ADMIN')")
    public String getMethodName2() {
        return "hello admin";
    }

    @PostMapping("/register")
    public String registerEmployee(@RequestBody Employee employee){
        System.out.println("EmployeeController.registerEmployee()"+employee);
        System.out.println("EmployeeController.registerEmployee()"+employee.getName());
        employeeService.registerEmployee(employee);
        return "Employee registered successfully";
    }

    @PostMapping("/signin")
    public String signIn(@RequestBody Employee employee){
        return employeeService.signIn(employee.getName(), employee.getPassword());
    }
}
