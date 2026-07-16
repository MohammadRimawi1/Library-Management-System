package com.exalt.library.repositories;

import com.exalt.library.models.libraryitems.LibraryItem;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository interface for managing LibraryItem documents in MongoDB.
 * Provides standard CRUD operations inherited from MongoRepository
 */
public interface LibraryItemRepository extends MongoRepository<LibraryItem, String> {
}
