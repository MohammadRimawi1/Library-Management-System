package com.exalt.library.controllers.handler;

import com.exalt.library.exceptions.BorrowerNotFoundException;
import com.exalt.library.exceptions.ItemNotFoundException;
import com.exalt.library.exceptions.ItemUnavailableException;
import com.exalt.library.exceptions.ReservationNotFoundException;
import com.exalt.library.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/**
 * a class representing the exception handling for common possible errors
 * @author Mohammad Rimawi
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * catches "not found" cases across reservations, borrowers, and items
     * 404 status
     * @param e
     * @return
     */
    @ExceptionHandler({ReservationNotFoundException.class, BorrowerNotFoundException.class, ItemNotFoundException.class})
    public ResponseEntity<Map<String, Object>> handleNotFound(RuntimeException e) {
        return ResponseEntity.status(404).body(ApiResponse.error(404, "Not Found", e.getMessage()));
    }

    /**
     * catches bad input / invalid state cases (e.g. claiming a non-READY reservation, or an item with no copies left)
     * 400 status
     * @param e
     * @return
     */
    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class, ItemUnavailableException.class})
    public ResponseEntity<Map<String, Object>> handleBadRequest(RuntimeException e) {
        return ResponseEntity.status(400).body(ApiResponse.error(400, "Bad Request", e.getMessage()));
    }

    /**
     * catch-all for anything unexpected
     * 500 status
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception e) {
        return ResponseEntity.status(500).body(ApiResponse.error(500, "Internal Server Error", e.getMessage()));
    }
}