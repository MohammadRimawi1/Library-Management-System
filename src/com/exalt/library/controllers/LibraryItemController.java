package com.exalt.library.controllers;

import com.exalt.library.exceptions.ItemNotFoundException;
import com.exalt.library.models.Author;
import com.exalt.library.models.SingletonLibrary;
import com.exalt.library.models.libraryitems.LibraryItem;
import com.exalt.library.models.libraryitems.physicalitems.PhysicalItem;
import com.exalt.library.services.LibraryItemServices;
import com.exalt.library.services.factory.LibraryItemFactory;
import com.exalt.library.util.ApiResponse;
import com.exalt.library.util.Json;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * a library item controller that gets a request from the client, does a specific job then returns the response
 * @author Mohammad Rimawi
 */
public class LibraryItemController implements HttpHandler {
    private LibraryItemServices libraryItemServices = new LibraryItemServices(); // defines the library item services
    Gson gson = new Gson(); // Google JSON Library for converting between JSON and Java objects

    /**
     * CONSTANTS FOR DEFINING API ENDPOINTS
     */
    private final String GET_ALL_API_LIBRARYITEMS = "/api/library-items";
    private final String GET_API_LIBRARYITEMS_ID = "/api/library-items/";
    private final String POST_API_LIBRARYITEMS = "/api/library-items";

    /**
     * a method for handling the type of request
     * @param exchange the exchange containing the request from the
     *                 client and used to send the response
     * @throws IOException
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        try {
            if("GET".equalsIgnoreCase(method) && path.equals(GET_ALL_API_LIBRARYITEMS)) {
                handleGetAll(exchange);
            } else if("GET".equalsIgnoreCase(method) && path.startsWith(GET_API_LIBRARYITEMS_ID)) {
                handleGetOne(exchange, path);
            } else if("POST".equalsIgnoreCase(method) && path.equals(POST_API_LIBRARYITEMS)) {
                handleCreate(exchange);
            } else {
                Json.sendJSON(exchange, 404, gson.toJson(Map.of("error", "no such route")));
            }
        } catch (ItemNotFoundException e) {
            Json.sendJSON(exchange, 404, gson.toJson(ApiResponse.error(404, "Not Found", e.getMessage())));
        } catch (IllegalArgumentException e) {
            Json.sendJSON(exchange, 400, gson.toJson(ApiResponse.error(400, "Bad Request", e.getMessage())));
        } catch (Exception e) {
            Json.sendJSON(exchange, 500, gson.toJson(Map.of("error", "something broke: " + e.getMessage())));
        }
    }

    /**
     * a method for fetching all the library items
     * @param exchange
     * @throws IOException
     */
    private void handleGetAll(HttpExchange exchange) throws IOException{
        List<LibraryItem> libraryItems = SingletonLibrary.getInstance().getLibraryItems();
        Json.sendJSON(exchange, 200, gson.toJson(ApiResponse.success(200, libraryItems)));
    }

    /**
     * a method for fetching a specific library item
     * @param exchange
     * @param path
     * @throws IOException
     */
    private void handleGetOne(HttpExchange exchange, String path) throws IOException {
        int id = Integer.parseInt(path.substring(path.lastIndexOf('/') + 1));
        LibraryItem libraryItem = libraryItemServices.findItemById(SingletonLibrary.getInstance().getLibraryItems(), id);
        Json.sendJSON(exchange, 200, gson.toJson(ApiResponse.success(200, libraryItem)));
    }

    /**
     * a method for creating a library item
     * @param exchange
     * @throws IOException
     */
    private void handleCreate(HttpExchange exchange) throws IOException {
        String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
        Map<String, Object> request = gson.fromJson(body, Map.class);

        String type = (String) request.get("type");
        if (type == null) {
            Json.sendJSON(exchange, 400, gson.toJson(ApiResponse.error(400, "Bad Request", "Missing required field: type")));
            return;
        }

        String title = (String) request.get("title");

        LibraryItem libraryItem = LibraryItemFactory.create(type);
        libraryItem.setTitle(title);

        if (libraryItem instanceof PhysicalItem physicalItem && request.get("numOfCopies") != null) {
            int numOfCopies = ((Double) request.get("numOfCopies")).intValue();
            physicalItem.setNumOfCopies(numOfCopies);
        }

        Map<String, Object> authorMap = (Map<String, Object>) request.get("author");
        Author author = new Author();

        author.setName((String) authorMap.get("name"));
        author.setNationality((String) authorMap.get("nationality"));

        libraryItem.setAuthor(author);

        libraryItemServices.addItem(SingletonLibrary.getInstance().getLibraryItems(), libraryItem);

        Json.sendJSON(exchange, 201, gson.toJson(ApiResponse.success(201, libraryItem)));
    }
}