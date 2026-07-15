package com.exalt.library.services.factory;

import com.exalt.library.models.libraryitems.LibraryItem;
import com.exalt.library.models.libraryitems.onlineitems.OnlineItem;
import com.exalt.library.services.borrowtype.InHandBorrowStrategyService;
import com.exalt.library.services.borrowtype.OnlineBorrowStrategyService;
import com.exalt.library.services.strategies.BorrowStrategy;
import org.springframework.stereotype.Component;

/**
 * a class representing the borrow strategy factory which uses the factory design pattern
 * @author Mohammad Rimawi
 */
@Component
public class BorrowStrategyFactory {
    private final BorrowStrategy inHandStrategy; // defines the in hand strategy
    private final BorrowStrategy onlineStrategy; // defines the online strategy

    /**
     * constructor injection
     * @param inHandStrategy
     * @param onlineStrategy
     */
    public BorrowStrategyFactory(InHandBorrowStrategyService inHandStrategy, OnlineBorrowStrategyService onlineStrategy) {
        this.inHandStrategy = inHandStrategy;
        this.onlineStrategy = onlineStrategy;
    }

    /**
     * a method for choosing between the strategies
     * @param libraryItem
     * @return
     */
    public BorrowStrategy resolve(LibraryItem libraryItem) {
        return (libraryItem instanceof OnlineItem) ? onlineStrategy : inHandStrategy;
    }
}
