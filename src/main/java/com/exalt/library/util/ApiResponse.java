package com.exalt.library.util;

import java.time.Instant;
import java.util.Map;

/**
 * a class representing the api responses structure
 * @author Mohammad Rimawi
 */
public class ApiResponse {
    /**
     * a method for creating a success message
     * @param status
     * @param data
     * @return
     */
    public static Map<String, Object> success(int status, Object data) {
        return Map.of("status", status, "timestamp", Instant.now().toString(), "data", data);
    }

    /**
     * a method for creating an error message
     * @param status
     * @param error
     * @param message
     * @return
     */
    public static Map<String, Object> error(int status, String error, String message) {
        return Map.of("status", status, "timestamp", Instant.now().toString(), "error", error, "message", message);
    }
}
