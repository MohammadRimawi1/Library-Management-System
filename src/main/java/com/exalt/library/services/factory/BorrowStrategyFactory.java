package com.exalt.library.services.factory;

import com.exalt.library.models.libraryitems.LibraryItem;
import com.exalt.library.models.libraryitems.onlineitems.OnlineItem;
import com.exalt.library.services.strategies.BorrowStrategy;

/**
 * a class representing the borrow strategy factory which uses the factory design pattern
 * @author Mohammad Rimawi
 */
public class BorrowStrategyFactory {
    private BorrowStrategy inHandStrategy; // defines the in hand strategy
    private BorrowStrategy onlineStrategy; // defines the online strategy

    /**
     * a default constructor
     */
    public BorrowStrategyFactory() { }

//    ==== SETTERS ====
    /**
     * a method for setting the in hand strategy
     * @param inHandStrategy
     */
    public void setInHandStrategy(BorrowStrategy inHandStrategy) {
        this.inHandStrategy = inHandStrategy;
    }

    /**
     * a method for setting the online strategy
     * @param onlineStrategy
     */
    public void setOnlineStrategy(BorrowStrategy onlineStrategy) {
        this.onlineStrategy = onlineStrategy;
    }
//    ==== SETTERS ====

    /**
     * a method for choosing between the strategies
     * @param libraryItem
     * @return
     */
    public BorrowStrategy resolve(LibraryItem libraryItem) {
        return (libraryItem instanceof OnlineItem) ? onlineStrategy : inHandStrategy;
    }
}
