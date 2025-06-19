package com.example.Evara_Shop.observer;

import com.example.Evara_Shop.model.User;
import org.springframework.stereotype.Component;

@Component
public class LogUserObserver implements UserRegistrationObserver {
    @Override
    public void update(User user) {
        System.out.println("📝 [LOG] Người dùng mới: " + user.getName() + " (" + user.getEmail() + ")");
    }
}
