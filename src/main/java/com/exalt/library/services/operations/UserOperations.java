package com.exalt.library.services.operations;

import com.exalt.library.dto.RegisterDTO;
import com.exalt.library.models.users.User;

/**
 * an interface representing the operations for a user account
 * @author Mohammad Rimawi
 */
public interface UserOperations {

    /**
     * finds a user by their email
     * implemented inside UserServices
     * @param email
     * @return the user if found
     */
    User findByEmail(String email);

    /**
     * checks whether a user with the given email already exists
     * implemented inside UserServices
     * @param email
     * @return true or false
     */
    boolean userExists(String email);
}