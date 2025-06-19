package com.example.Evara_Shop.command;

import com.example.Evara_Shop.DTO.product.ProductDTO;
import com.example.Evara_Shop.DTO.product.ProductUpdateDTO;
import com.example.Evara_Shop.builder.ProductBuilder;
import com.example.Evara_Shop.model.Product;
import com.example.Evara_Shop.model.Supplier;
import com.example.Evara_Shop.repository.ProductRepo;
import com.example.Evara_Shop.repository.SupplierRepo;
import com.example.Evara_Shop.validation.ValidatorStrategy;

import java.math.BigDecimal;

public class UpdateProductCommand implements UpdateCommand<ProductDTO> {

    private final Long id;
    private final ProductUpdateDTO dto;
    private final ProductRepo productRepo;
    private final SupplierRepo supplierRepo;
    private final ValidatorStrategy<ProductUpdateDTO> validator;

    public UpdateProductCommand(Long id, ProductUpdateDTO dto,
                                ProductRepo productRepo,
                                SupplierRepo supplierRepo,
                                ValidatorStrategy<ProductUpdateDTO> validator) {
        this.id = id;
        this.dto = dto;
        this.productRepo = productRepo;
        this.supplierRepo = supplierRepo;
        this.validator = validator;
    }

    @Override
    public ProductDTO execute() {
        validator.validate(dto);

        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product không tồn tại"));

        // ✅ Kiểm tra nếu oldPrice trong DB khác null → price phải nhỏ hơn
        if (dto.getPrice() != null && product.getOldPrice() != null) {
            if (dto.getPrice().compareTo(product.getOldPrice()) >= 0) {
                throw new IllegalArgumentException("Giá mới phải nhỏ hơn giá cũ đã tồn tại.");
            }
        }

        Supplier supplier = null;
        if (dto.getSupplierId() != null) {
            supplier = supplierRepo.findById(dto.getSupplierId())
                    .orElseThrow(() -> new RuntimeException("Supplier không tồn tại"));
        }

        ProductBuilder.updateFromDTO(product, dto, supplier);
        return new ProductDTO(productRepo.save(product));
    }
}

