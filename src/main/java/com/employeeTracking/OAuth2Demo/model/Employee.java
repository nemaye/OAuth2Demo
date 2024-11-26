package com.employeeTracking.OAuth2Demo.model;

public class Employee {
    private Long id;
    private String name;
    private String password;
    private String role = "EMPLOYEE";

    public Employee() {
    }

    public Employee(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public Employee(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }
}
