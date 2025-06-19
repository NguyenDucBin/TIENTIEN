package com.example.Evara_Shop.service;

import com.example.Evara_Shop.DTO.product.ProductByIdDTO;
import com.example.Evara_Shop.DTO.product.ProductCreateDTO;
import com.example.Evara_Shop.DTO.product.ProductDTO;
import com.example.Evara_Shop.DTO.product.ProductUpdateDTO;
import com.example.Evara_Shop.builder.ProductBuilder;
import com.example.Evara_Shop.command.UpdateCommand;
import com.example.Evara_Shop.command.UpdateProductCommand;
import com.example.Evara_Shop.factory.UpdateCommandFactory;
import com.example.Evara_Shop.model.Product;
import com.example.Evara_Shop.model.Supplier;
import com.example.Evara_Shop.repository.ProductRepo;
import com.example.Evara_Shop.repository.SupplierRepo;
import com.example.Evara_Shop.validation.product.ProductCreateValidator;
import com.example.Evara_Shop.validation.product.ProductUpdateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class ProductService {
    @Autowired private ProductRepo productRepo;
    @Autowired private SupplierRepo supplierRepo;

    @Autowired @Qualifier("productCreateValidator")
    ProductCreateValidator productCreateValidator;
    @Autowired @Qualifier("productUpdateValidator")
    ProductUpdateValidator productUpdateValidator;

    @Autowired
    UpdateCommandFactory updateCommandFactory;

    public List<ProductDTO> getAll() {
        return SimpleServiceHelper.mapToDTOList(productRepo.findAll(), ProductDTO::new);
    }

    public ProductByIdDTO getById(Long id) {
        Product product = productRepo.findById(id).get();
        return new ProductByIdDTO(product);
    }

    public ProductDTO create(ProductCreateDTO dto) {
        productCreateValidator.validate(dto);

        Supplier supplier = supplierRepo.findById(dto.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        Product product = ProductBuilder.from(dto, supplier);

        return SimpleServiceHelper.saveAndReturnDTO(product, productRepo, ProductDTO::new);
    }

    public ProductDTO update(Long id, ProductUpdateDTO dto) {
        UpdateCommand<?> command = updateCommandFactory.createCommand("product", id, dto);
        Object result = command.execute();

        // Vì factory trả về UpdateCommand<?>, cần ép kiểu nếu muốn type-safe
        if (!(result instanceof ProductDTO)) {
            throw new IllegalStateException("Command trả về kiểu không hợp lệ.");
        }

        return (ProductDTO) result;
    }

    public void delete(Long id) {
        productRepo.deleteById(id);
    }

}



