package com.example.Evara_Shop.observer;

import com.example.Evara_Shop.model.User;
import org.springframework.stereotype.Component;

@Component
public class WelcomeEmailObserver implements UserRegistrationObserver {
    @Override
    public void update(User user) {
        System.out.println("📧 Gửi email chào mừng tới: " + user.getEmail());
    }
}

