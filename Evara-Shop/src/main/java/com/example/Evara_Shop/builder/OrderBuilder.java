package com.example.Evara_Shop.builder;

import com.example.Evara_Shop.DTO.order.OrderCreateDTO;
import com.example.Evara_Shop.DTO.order.OrderUpdateDTO;
import com.example.Evara_Shop.model.Order;
import com.example.Evara_Shop.model.User;
import com.example.Evara_Shop.state.CreatedState;
import com.example.Evara_Shop.enums.OrderStatus;

import java.time.LocalDateTime;

public class OrderBuilder {
    public static Order fromDTO(OrderCreateDTO dto, User user) {
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        order.setState(new CreatedState());
        return order;
    }

    public static void updateFromDTO(Order order, OrderUpdateDTO dto, User user) {
        if (dto.getTotalAmount() != null) {
            order.setTotalAmount(dto.getTotalAmount());
        }
        if (user != null) {
            order.setUser(user);
        }
    }
}

