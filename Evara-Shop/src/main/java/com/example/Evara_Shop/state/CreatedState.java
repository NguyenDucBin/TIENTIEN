package com.example.Evara_Shop.state;

import com.example.Evara_Shop.enums.OrderStatus;
import com.example.Evara_Shop.model.Order;

public class CreatedState implements OrderState {

    @Override
    public void next(Order order) {
        order.setStatus(OrderStatus.PROCESSING);
        order.setState(new PaidState());
    }

    @Override
    public void cancel(Order order) {
        order.setStatus(OrderStatus.CANCELLED);
        order.setState(new CancelledState());
    }
}

