package com.exalt.library.util;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.Instant;

public class Json {
    /**
     * Sends a JSON response back to the client through the HTTP exchange.
     * The response body is encoded using UTF-8 and the Content-Type header
     * is set to application/json.
     * @param exchange
     * @param status
     * @param json
     * @throws IOException
     */
    public static void sendJSON(HttpExchange exchange, int status, String json) throws IOException {
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
        exchange.getResponseHeaders().set("Date", Instant.now().toString());
        exchange.getResponseHeaders().set("Server", "LibraryAPI/1.0");

        exchange.sendResponseHeaders(status, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }
}
