package com.example.Evara_Shop.service;

import com.example.Evara_Shop.DTO.employee.EmployeeDTO;
import com.example.Evara_Shop.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public List<EmployeeDTO> getAll(){
        return SimpleServiceHelper.mapToDTOList(employeeRepo.findAll(), EmployeeDTO::new);
    }
}
