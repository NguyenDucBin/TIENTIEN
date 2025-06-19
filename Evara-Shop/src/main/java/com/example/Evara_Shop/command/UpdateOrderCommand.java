package com.example.Evara_Shop.command;

import com.example.Evara_Shop.DTO.order.OrderDTO;
import com.example.Evara_Shop.DTO.order.OrderUpdateDTO;
import com.example.Evara_Shop.builder.OrderBuilder;
import com.example.Evara_Shop.model.Order;
import com.example.Evara_Shop.model.User;
import com.example.Evara_Shop.repository.OrderRepo;
import com.example.Evara_Shop.repository.UserRepo;
import com.example.Evara_Shop.validation.ValidatorStrategy;

public class UpdateOrderCommand implements UpdateCommand<OrderDTO> {

    private final Long id;
    private final OrderUpdateDTO dto;
    private final OrderRepo orderRepo;
    private final UserRepo userRepo;
    private final ValidatorStrategy<OrderUpdateDTO> validator;

    public UpdateOrderCommand(Long id, OrderUpdateDTO dto,OrderRepo orderRepo,
                              UserRepo userRepo,
                              ValidatorStrategy<OrderUpdateDTO> validator) {
        this.id = id;
        this.dto = dto;
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
        this.validator = validator;
    }

    @Override
    public OrderDTO execute() {
        validator.validate(dto);

        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng."));

        User user = null;
        if (dto.getUserId() != null) {
            user = userRepo.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng."));
        }

        OrderBuilder.updateFromDTO(order, dto, user);
        return new OrderDTO(orderRepo.save(order));
    }
}
