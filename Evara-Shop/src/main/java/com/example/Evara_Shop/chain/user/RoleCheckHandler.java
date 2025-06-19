package com.example.Evara_Shop.chain.user;

import com.example.Evara_Shop.chain.AbstractDeleteHandler;
import com.example.Evara_Shop.model.User;
import org.springframework.stereotype.Component;

@Component
public class RoleCheckHandler extends AbstractDeleteHandler<User> {
    @Override
    protected void check(User user) {
        if ("admin".equals(user.getRole())) {
            throw new RuntimeException("Không được phép xóa tài khoản admin.");
        }
    }
}

