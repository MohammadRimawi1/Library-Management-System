package com.exalt.library.services.operations;

import com.exalt.library.dto.LoginDTO;
import com.exalt.library.dto.RegisterDTO;
import com.exalt.library.models.users.User;

/**
 * an interface representing the operations for a user account
 * @author Mohammad Rimawi
 */
public interface UserOperations {

    /**
     * registers a new user
     * resolves the role first (based on the registration key),
     * then validates, then creates the User and, if BORROWER, a linked Borrower too
     * @param registerDTO
     * @return
     */
    public User register(RegisterDTO registerDTO);

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

    /**
     * verifies credentials and issues a JWT if they're correct
     * @param loginDTO
     * @return
     */
    public String login(LoginDTO loginDTO);
}