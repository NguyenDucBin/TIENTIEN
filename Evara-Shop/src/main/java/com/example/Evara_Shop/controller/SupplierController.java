package com.example.Evara_Shop.controller;

import com.example.Evara_Shop.DTO.supplier.SupplierByIdDTO;
import com.example.Evara_Shop.DTO.supplier.SupplierCreateDTO;
import com.example.Evara_Shop.DTO.supplier.SupplierDTO;
import com.example.Evara_Shop.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public ResponseEntity<List<SupplierDTO>> getAll() {
        return ResponseEntity.ok(supplierService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<SupplierByIdDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.getById(id));
    }

    @PostMapping
    public ResponseEntity<SupplierDTO> create(@RequestBody SupplierCreateDTO supplierDTO) {
        return ResponseEntity.ok(supplierService.create(supplierDTO));
    }
}
