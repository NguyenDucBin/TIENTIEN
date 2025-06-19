package com.example.Evara_Shop.state;

import com.example.Evara_Shop.model.Order;

public interface OrderState {
    void next(Order order);
    void cancel(Order order);
}

