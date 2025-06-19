package com.example.Evara_Shop.observer;

import com.example.Evara_Shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRegistrationPublisher {

    private final List<UserRegistrationObserver> observers = new ArrayList<>();

    // Inject qua constructor hoặc setter
    @Autowired
    public UserRegistrationPublisher(List<UserRegistrationObserver> observers) {
        this.observers.addAll(observers);
    }

    public void notifyAll(User user) {
        for (UserRegistrationObserver o : observers) {
            o.update(user);
        }
    }
}

