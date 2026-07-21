package com.exalt.library.security;

import com.exalt.library.models.users.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * adapts our User entity to Spring Security's UserDetails contract
 * @author Mohammad Rimawi
 */
public class UserPrincipal implements UserDetails {
    private final User user; // Defines the user

    /**
     * constructor injection
     * @param user
     */
    public UserPrincipal(User user) {
        this.user = user;
    }

    /**
     * a method for getting the user
     * @return
     */
    public User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail(); // email is our login identifier
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
