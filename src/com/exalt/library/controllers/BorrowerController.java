package com.exalt.library.controllers;

import com.exalt.library.models.Borrower;
import com.exalt.library.models.SingletonLibrary;
import com.exalt.library.services.BorrowerServices;
import com.exalt.library.util.Json;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * a borrower controller that gets a request from the client, does a specific job then returns the response
 * @author Mohammad Rimawi
 */
public class BorrowerController implements HttpHandler {
    private BorrowerServices borrowerServices = new BorrowerServices(); // Defines the services that will be used inside this controller
    Gson gson = new Gson(); // Google JSON Library for converting between JSON and Java objects

    /**
     * CONSTANTS FOR DEFINING API ENDPOINTS
     */
    private final String GET_ALL_API_BORROWERS = "/api/borrowers";
    private final String GET_API_BORROWERS_ID = "/api/borrowers/";
    private final String POST_API_BORROWERS = "/api/borrowers";

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
            if("GET".equalsIgnoreCase(method) && path.equals(GET_ALL_API_BORROWERS)) {
                handleGetAll(exchange);
            } else if("GET".equalsIgnoreCase(method) && path.startsWith(GET_API_BORROWERS_ID)) {
                handleGetOne(exchange, path);
            } else if("POST".equalsIgnoreCase(method) && path.equals(POST_API_BORROWERS)) {
                handleCreate(exchange);
            } else {
                Json.sendJSON(exchange, 404, gson.toJson(Map.of("error", "no such route")));
            }
        } catch (Exception e) {
            Json.sendJSON(exchange, 500, gson.toJson(Map.of("error", "something broke: " + e.getMessage())));
        }
    }

    /**
     * a method for fetching all the borrowers
     * @param exchange
     * @throws IOException
     */
    private void handleGetAll(HttpExchange exchange) throws IOException{
        List<Borrower> borrowers = SingletonLibrary.getInstance().getBorrowers();
        String json = gson.toJson(borrowers);
        Json.sendJSON(exchange, 200, json);
    }

    /**
     * a method for fetching a specific borrower
     * @param exchange
     * @param path
     * @throws IOException
     */
    private void handleGetOne(HttpExchange exchange, String path) throws IOException {
        int id = Integer.parseInt(path.substring(path.lastIndexOf('/') + 1));
        Borrower borrower = borrowerServices.findBorrowerById(SingletonLibrary.getInstance().getBorrowers(), id);
        Json.sendJSON(exchange, 200, gson.toJson(borrower));
    }

    /**
     * a method for creating a borrower
     * @param exchange
     * @throws IOException
     */
    private void handleCreate(HttpExchange exchange) throws IOException {
        String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
        Borrower req = gson.fromJson(body, Borrower.class);

        Borrower borrower = new Borrower();
        borrower.setName(req.getName());
        borrowerServices.assignBorrower(SingletonLibrary.getInstance().getBorrowers(), borrower);

        Json.sendJSON(exchange, 201, gson.toJson(borrower));
    }
}
