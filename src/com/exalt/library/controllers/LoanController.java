package com.exalt.library.controllers;

import com.exalt.library.models.Loan;
import com.exalt.library.models.SingletonLibrary;
import com.exalt.library.services.BorrowerServices;
import com.exalt.library.services.LibraryItemServices;
import com.exalt.library.services.LoanServices;
import com.exalt.library.services.ReservationServices;
import com.exalt.library.services.borrowtype.InHandBorrowStrategyService;
import com.exalt.library.services.borrowtype.OnlineBorrowStrategyService;
import com.exalt.library.services.factory.BorrowStrategyFactory;
import com.exalt.library.util.Json;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * a loan controller that gets a request from the client, does a specific job then returns the response
 * @author Mohammad Rimawi
 */
public class LoanController implements HttpHandler {
    private LoanServices loanServices = initializeLoanServices(); // defines the loan services
    Gson gson = new Gson(); // Google JSON Library for converting between JSON and Java objects

    /**
     * a method for initializing the loan services, where we can use the logic it delivers
     * @return the loan services
     */
    private LoanServices initializeLoanServices() {
        LoanServices loanServices = new LoanServices();

        BorrowStrategyFactory factory = new BorrowStrategyFactory();
        factory.setInHandStrategy(new InHandBorrowStrategyService());
        factory.setOnlineStrategy(new OnlineBorrowStrategyService());

        loanServices.setLibraryItemOperations(new LibraryItemServices());
        loanServices.setBorrowerOperations(new BorrowerServices());
        loanServices.setBorrowStrategyFactory(factory);
        loanServices.setReservationServices(new ReservationServices());

        return loanServices;
    }

    /**
     * CONSTANTS FOR DEFINING API ENDPOINTS
     */
    private final String GET_ALL_API_LOANS = "/api/loans";
    private final String GET_API_LOANS_ID = "/api/loans/";
    private final String POST_API_LOANS = "/api/loans";

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
            if("GET".equalsIgnoreCase(method) && path.equals(GET_ALL_API_LOANS)) {
                handleGetAll(exchange);
            } else if("GET".equalsIgnoreCase(method) && path.startsWith(GET_API_LOANS_ID)) {
                handleGetOne(exchange, path);
            } else if("POST".equalsIgnoreCase(method) && path.equals(POST_API_LOANS)) {
                handleCreate(exchange);
            } else {
                Json.sendJSON(exchange, 404, gson.toJson(Map.of("error", "no such route")));
            }
        } catch (Exception e) {
            Json.sendJSON(exchange, 500, gson.toJson(Map.of("error", "something broke: " + e.getMessage())));
        }
    }

    /**
     * a method for fetching all the loans
     * @param exchange
     * @throws IOException
     */
    private void handleGetAll(HttpExchange exchange) throws IOException{
        List<Loan> loans = SingletonLibrary.getInstance().getLoans();
        String json = gson.toJson(loans);
        Json.sendJSON(exchange, 200, json);
    }

    /**
     * a method for fetching a specific loan
     * @param exchange
     * @param path
     * @throws IOException
     */
    private void handleGetOne(HttpExchange exchange, String path) throws IOException {
        int id = Integer.parseInt(path.substring(path.lastIndexOf('/') + 1));
        Loan loan = loanServices.findLoanById(SingletonLibrary.getInstance().getLoans(), id);
        Json.sendJSON(exchange, 200, gson.toJson(loan));
    }

    /**
     * a method for creating a loan
     * @param exchange
     * @throws IOException
     */
    private void handleCreate(HttpExchange exchange) throws IOException {
        String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
        Map<String, Double> request = gson.fromJson(body, Map.class);

        int borrowerId = request.get("borrowerId").intValue();
        int itemId = request.get("itemId").intValue();

        Loan loan = loanServices.borrowLibraryItem(SingletonLibrary.getInstance().getLoans(),
                SingletonLibrary.getInstance().getLibraryItems(),
                SingletonLibrary.getInstance().getBorrowers(),
                borrowerId,
                itemId
        );

        Json.sendJSON(exchange, 201, gson.toJson(loan));
    }
}