package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.entities.User;
import com.devsuperior.dscommerce.services.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;

    public void validateSelfOrAdmin(long userId){
        User userLogged = userService.getUserAuthenticated();

        if (!userLogged.hasHole("ROLE_ADMIN") && (!userLogged.getId().equals(userId))) {
            throw new ForbiddenException("Access denied");
        }
    }
}
