package com.example.Evara_Shop.DTO.employee;

import com.example.Evara_Shop.model.Employee;

public class EmployeeDTO {
    private Long id;
    private int user_id;
    private String position;

    public EmployeeDTO(Employee employee) {
        this.id = employee.getId();
        this.user_id = employee.getUser_id();
        this.position = employee.getPosition();
    }

    public Long getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getPosition() {
        return position;
    }
}
