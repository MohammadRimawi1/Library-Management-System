package com.exalt.library.services;

import com.exalt.library.exceptions.UserNotFoundException;
import com.exalt.library.models.users.User;
import com.exalt.library.repositories.UserRepository;
import com.exalt.library.services.operations.UserOperations;
import org.springframework.stereotype.Service;

/**
 * a class representing the services for the users
 * implements the interface UserOperations
 * @author Mohammad Rimawi
 */
@Service
public class UserServices implements UserOperations {
    private final UserRepository userRepository; // Defines the user repository

    /**
     * a constructor injection
     * @param userRepository
     */
    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * finds a user by their email
     * @param email
     * @return the user if found
     */
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    /**
     * checks whether a user with the given email already exists
     * @param email
     * @return true or false
     */
    @Override
    public boolean userExists(String email) {
        return userRepository.existsByEmail(email);
    }
}
