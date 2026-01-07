package com.dimandco.proj_studroom.core.web.ui;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;

/**
 * Authentication utilities for controllers.
 */
public final class AuthUtils {

    private AuthUtils() {
        throw new UnsupportedOperationException();
    }

    public static boolean isAuthenticated(final Authentication auth) {
        if (auth == null) return false;
        if (auth instanceof AnonymousAuthenticationToken) return false;
        return auth.isAuthenticated();
    }

    public static boolean isAnonymous(final Authentication auth) {
        if (auth == null) return true;
        if (auth instanceof AnonymousAuthenticationToken) return true;
        return !auth.isAuthenticated();
    }
}