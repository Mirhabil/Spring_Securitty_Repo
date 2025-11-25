package com.example.custom_jwt_authentication.security.permissions;

import com.example.custom_jwt_authentication.security.model.Order;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;


import java.io.Serializable;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication,
                                 Object targetDomainObject,
                                 Object permission) {

        if (!(permission instanceof String)) {
            return false;
        }

        String requiredPermission = (String) permission;

        if (targetDomainObject instanceof Order order) {
            return checkOrderPermission(authentication, order, requiredPermission);
        }

        return false;
    }

    private boolean checkOrderPermission(Authentication auth, Order order, String permission) {

        String username = auth.getName();

        if (permission.equals("ORDER_VIEW") && order.getOwner().equals(username)) {
            return true;
        }

        if (permission.equals("ORDER_EDIT") && auth.getAuthorities()
                .stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return true;
        }

        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication,
                                 Serializable targetId,
                                 String targetType,
                                 Object permission) {
        return false;
    }
}

