package com.example.Evara_Shop.state;

import com.example.Evara_Shop.enums.OrderStatus;
import com.example.Evara_Shop.model.Order;

public class PaidState implements OrderState {

    @Override
    public void next(Order order) {
        order.setStatus(OrderStatus.COMPLETED);
        order.setState(new DeliveredState());
    }

    @Override
    public void cancel(Order order) {
        order.setStatus(OrderStatus.CANCELLED);
        order.setState(new CancelledState());
    }
}

