package com.exalt.library.repositories;

import com.exalt.library.models.Borrower;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository interface for managing Borrower documents in MongoDB.
 * Provides standard CRUD operations inherited from MongoRepository
 */
public interface BorrowerRepository extends MongoRepository<Borrower, String> {
    /**
     * a method for checking if the email exists
     * @param email
     * @return
     */
    boolean existsByEmail(String email);
}
