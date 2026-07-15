package com.exalt.library.repositories;

import com.exalt.library.models.libraryitems.LibraryItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LibraryItemRepository extends MongoRepository<LibraryItem, String> {
}
