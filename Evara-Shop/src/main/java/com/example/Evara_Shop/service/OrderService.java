package com.example.Evara_Shop.service;

import com.example.Evara_Shop.DTO.order.OrderCreateDTO;
import com.example.Evara_Shop.DTO.order.OrderDTO;
import com.example.Evara_Shop.DTO.order.OrderUpdateDTO;
import com.example.Evara_Shop.builder.OrderBuilder;
import com.example.Evara_Shop.command.UpdateOrderCommand;
import com.example.Evara_Shop.model.Order;
import com.example.Evara_Shop.model.User;
import com.example.Evara_Shop.repository.OrderRepo;
import com.example.Evara_Shop.repository.UserRepo;
import com.example.Evara_Shop.validation.ValidatorStrategy;
import com.example.Evara_Shop.validation.order.OrderCreateValidator;
import com.example.Evara_Shop.validation.order.OrderUpdateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired private OrderRepo orderRepo;
    @Autowired private UserRepo userRepo;

    @Autowired
    @Qualifier("orderCreateValidator")
    private OrderCreateValidator validator;

    @Autowired
    @Qualifier("orderUpdateValidator")
    private OrderUpdateValidator updateValidator;

    public List<OrderDTO> getAll() {
        return SimpleServiceHelper.mapToDTOList(orderRepo.findAll(), OrderDTO::new);
    }

    public OrderDTO create(OrderCreateDTO dto) {
        validator.validate(dto);
        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));

        Order order = OrderBuilder.fromDTO(dto, user);
        return SimpleServiceHelper.saveAndReturnDTO(order, orderRepo, OrderDTO::new);
    }

    public OrderDTO advanceState(Long id) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng"));
        order.initState();
        order.nextState();
        return new OrderDTO(orderRepo.save(order));
    }

    public OrderDTO cancel(Long id) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng"));
        order.initState();
        order.cancel();
        return new OrderDTO(orderRepo.save(order));
    }

    public void delete(Long id) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng"));
        orderRepo.delete(order);
    }

    public OrderDTO update(Long id, OrderUpdateDTO dto) {
        UpdateOrderCommand cmd = new UpdateOrderCommand(id, dto, orderRepo, userRepo, updateValidator);
        return cmd.execute();
    }
}

