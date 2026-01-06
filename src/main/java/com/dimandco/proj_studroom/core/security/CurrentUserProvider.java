package com.dimandco.proj_studroom.core.security;

import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Component for providing the Current user
 *
 * @see CurrentUser
 */
@Component
public final class CurrentUserProvider {

    public Optional<CurrentUser> getCurrentUser(){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null){
            return Optional.empty();
        }
        if (authentication.getPrincipal() instanceof ApplicationUserDetails userDetails){
            return Optional.of(new CurrentUser(userDetails.personId(), userDetails.getUsername(), userDetails.type()));
        }
        return Optional.empty();
    }
}
