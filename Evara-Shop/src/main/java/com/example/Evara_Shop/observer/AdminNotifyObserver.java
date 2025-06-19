package com.example.Evara_Shop.observer;

import com.example.Evara_Shop.model.User;
import org.springframework.stereotype.Component;

@Component
public class AdminNotifyObserver implements UserRegistrationObserver {
    @Override
    public void update(User user) {
        System.out.println("📢 Thông báo cho admin: Người dùng mới đã đăng ký: " + user.getEmail());
    }
}

