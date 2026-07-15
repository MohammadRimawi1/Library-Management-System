package com.exalt.library.services.factory;

import com.exalt.library.models.libraryitems.LibraryItem;
import com.exalt.library.models.libraryitems.onlineitems.BookOnline;
import com.exalt.library.models.libraryitems.onlineitems.StoryOnline;
import com.exalt.library.models.libraryitems.physicalitems.BookPhysical;
import com.exalt.library.models.libraryitems.physicalitems.StoryPhysical;

/**
 * a factory for creating the correct LibraryItem subtype based on a type name
 * @author Mohammad Rimawi
 */
public class LibraryItemFactory {
    public static LibraryItem create(String type) {
        return switch (type) {
            case "BookPhysical" -> new BookPhysical();
            case "StoryPhysical" -> new StoryPhysical();
            case "BookOnline" -> new BookOnline();
            case "StoryOnline" -> new StoryOnline();
            default -> throw new IllegalArgumentException("Unknown item type: " + type);
        };
    }
}
