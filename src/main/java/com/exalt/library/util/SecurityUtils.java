package com.exalt.library.util;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * a small helper for reading the currently authenticated user's identity
 * @author Mohammad Rimawi
 */
public class SecurityUtils {
    private SecurityUtils() {}

    /**
     * returns the email of the currently authenticated user (set as the principal's "username")
     */
    public static String getCurrentUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
