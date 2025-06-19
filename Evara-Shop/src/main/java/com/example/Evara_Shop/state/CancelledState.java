package com.example.Evara_Shop.state;

import com.example.Evara_Shop.model.Order;

public class CancelledState implements OrderState {

    @Override
    public void next(Order order) {
        throw new IllegalStateException("Đơn hàng đã huỷ không thể xử lý tiếp.");
    }

    @Override
    public void cancel(Order order) {
        // Không làm gì cả – đã huỷ
    }
}

