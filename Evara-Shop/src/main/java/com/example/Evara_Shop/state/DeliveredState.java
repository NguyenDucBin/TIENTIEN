package com.example.Evara_Shop.state;

import com.example.Evara_Shop.model.Order;

public class DeliveredState implements OrderState {

    @Override
    public void next(Order order) {
        throw new IllegalStateException("Đơn hàng đã hoàn tất. Không thể chuyển tiếp.");
    }

    @Override
    public void cancel(Order order) {
        throw new IllegalStateException("Không thể huỷ đơn hàng đã giao.");
    }
}

