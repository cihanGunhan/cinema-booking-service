package com.cihangunhan.cinemabookingservice.config;

import com.cihangunhan.cinemabookingservice.entity.Kullanici;
import com.cihangunhan.cinemabookingservice.exception.UnauthorizedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserProvider {

    public Kullanici getCurrentUser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedException("Oturum açmanız gerekiyor");
        }

        return (Kullanici) authentication.getPrincipal();
    }

    public Long getCurrentUserId() {
        return getCurrentUser().getId();
    }
}