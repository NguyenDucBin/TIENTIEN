package com.example.Evara_Shop.validation.order;

import com.example.Evara_Shop.DTO.order.OrderUpdateDTO;
import com.example.Evara_Shop.validation.ValidatorStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("orderUpdateValidator")
public class OrderUpdateValidator implements ValidatorStrategy<OrderUpdateDTO> {

    @Override
    public void validate(OrderUpdateDTO dto) {
        if (dto.getTotalAmount() != null && dto.getTotalAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Tổng tiền phải lớn hơn 0.");
        }
    }
}

