package com.example.Evara_Shop.validation.order;

import com.example.Evara_Shop.DTO.order.OrderCreateDTO;
import com.example.Evara_Shop.validation.ValidatorStrategy;
import org.springframework.stereotype.Component;

@Component("orderCreateValidator")
public class OrderCreateValidator implements ValidatorStrategy<OrderCreateDTO> {

    @Override
    public void validate(OrderCreateDTO dto) {
        if (dto.getUserId() == null) {
            throw new IllegalArgumentException("User ID không được để trống.");
        }
    }
}

