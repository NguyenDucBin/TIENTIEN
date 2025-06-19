package com.example.Evara_Shop.factory;

import com.example.Evara_Shop.DTO.order.OrderUpdateDTO;
import com.example.Evara_Shop.DTO.product.ProductUpdateDTO;
import com.example.Evara_Shop.command.UpdateCommand;
import com.example.Evara_Shop.command.UpdateOrderCommand;
import com.example.Evara_Shop.command.UpdateProductCommand;
import com.example.Evara_Shop.repository.OrderRepo;
import com.example.Evara_Shop.repository.ProductRepo;
import com.example.Evara_Shop.repository.SupplierRepo;
import com.example.Evara_Shop.repository.UserRepo;
import com.example.Evara_Shop.validation.ValidatorStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateCommandFactory {

    @Autowired private ProductRepo productRepo;
    @Autowired private SupplierRepo supplierRepo;
    @Autowired private OrderRepo orderRepo;
    @Autowired private UserRepo userRepo;

    @Autowired private ValidatorStrategy<ProductUpdateDTO> productUpdateValidator;
    @Autowired private ValidatorStrategy<OrderUpdateDTO> orderUpdateValidator;

    public UpdateCommand<?> createCommand(String entity, Long id, Object dto) {
        return switch (entity.toLowerCase()) {
            case "product" -> new UpdateProductCommand(
                    id,
                    (ProductUpdateDTO) dto,
                    productRepo,
                    supplierRepo,
                    productUpdateValidator
            );
            case "order" -> new UpdateOrderCommand(
                    id,
                    (OrderUpdateDTO) dto,
                    orderRepo,
                    userRepo,
                    orderUpdateValidator
            );
            default -> throw new IllegalArgumentException("Không hỗ trợ entity: " + entity);
        };
    }
}

