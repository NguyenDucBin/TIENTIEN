package com.example.Evara_Shop.service;

import com.example.Evara_Shop.DTO.supplier.SupplierDTO;
import com.example.Evara_Shop.DTO.supplier.SupplierCreateDTO;
import com.example.Evara_Shop.builder.SupplierBuilder;
import com.example.Evara_Shop.model.Supplier;
import com.example.Evara_Shop.repository.SupplierRepo;
import com.example.Evara_Shop.validation.ValidatorStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    @Autowired
    SupplierRepo supplierRepo;

    @Autowired @Qualifier("supplierValidator")
    private ValidatorStrategy<SupplierCreateDTO> supplierValidator;

    public List<SupplierDTO> getAll() {
        return SimpleServiceHelper.mapToDTOList(supplierRepo.findAll(), SupplierDTO::new);
    }

    public SupplierDTO create(SupplierCreateDTO dto) {
        supplierValidator.validate(dto);

        Supplier supplier = SupplierBuilder.from(dto);

        return SimpleServiceHelper.saveAndReturnDTO(supplier, supplierRepo, SupplierDTO::new);
    }
}
