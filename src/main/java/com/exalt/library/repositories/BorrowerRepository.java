package com.exalt.library.repositories;

import com.exalt.library.models.Borrower;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BorrowerRepository extends MongoRepository<Borrower, String> {
}
