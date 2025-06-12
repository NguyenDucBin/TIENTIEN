package com.example.Evara_Shop.DTO.supplier;

import com.example.Evara_Shop.model.Supplier;

public class SupplierDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;

    public SupplierDTO(Supplier supplier) {
        this.id = supplier.getId();
        this.name = supplier.getName();
        this.email = supplier.getEmail();
        this.phone = supplier.getPhone();
        this.address = supplier.getAddress();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}
