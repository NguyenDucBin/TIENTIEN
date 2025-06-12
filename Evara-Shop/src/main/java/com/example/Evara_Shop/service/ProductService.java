package com.example.Evara_Shop.service;

import com.example.Evara_Shop.DTO.product.ProductCreateDTO;
import com.example.Evara_Shop.DTO.product.ProductDTO;
import com.example.Evara_Shop.builder.ProductBuilder;
import com.example.Evara_Shop.model.Product;
import com.example.Evara_Shop.model.Supplier;
import com.example.Evara_Shop.repository.ProductRepo;
import com.example.Evara_Shop.repository.SupplierRepo;
import com.example.Evara_Shop.validation.ValidatorStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {
    @Autowired private ProductRepo productRepo;
    @Autowired private SupplierRepo supplierRepo;

    @Autowired @Qualifier("productValidator")
    private ValidatorStrategy<ProductCreateDTO> validator;

    public List<ProductDTO> getAll() {
        return SimpleServiceHelper.mapToDTOList(productRepo.findAll(), ProductDTO::new);
    }

    public ProductDTO create(ProductCreateDTO dto) {
        validator.validate(dto);

        Supplier supplier = supplierRepo.findById(dto.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        Product product = ProductBuilder.from(dto, supplier);

        return SimpleServiceHelper.saveAndReturnDTO(product, productRepo, ProductDTO::new);
    }
}



