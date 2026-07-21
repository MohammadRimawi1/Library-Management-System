package com.exalt.library.security;

import com.exalt.library.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Spring Security's entry point for loading a user by their login identifier (email)
 * @author Mohammad Rimawi
 */
@Service
public class AppUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository; // Defines the user repository

    /**
     * constructor injection
     * @param userRepository
     */
    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .map(UserPrincipal::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
