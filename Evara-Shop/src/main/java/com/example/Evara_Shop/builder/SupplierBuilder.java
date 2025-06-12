package com.example.Evara_Shop.builder;

import com.example.Evara_Shop.DTO.supplier.SupplierCreateDTO;
import com.example.Evara_Shop.model.Supplier;

public class SupplierBuilder {
    public static Supplier from(SupplierCreateDTO supplierCreateDTO) {
        Supplier supplier = new Supplier();
        supplier.setName(supplierCreateDTO.getName());
        supplier.setPhone(supplierCreateDTO.getPhone());
        supplier.setEmail(supplierCreateDTO.getEmail());
        supplier.setAddress(supplierCreateDTO.getAddress());
        return supplier;
    }
}
