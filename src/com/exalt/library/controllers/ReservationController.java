package com.exalt.library.controllers;

import com.exalt.library.exceptions.ReservationNotFoundException;
import com.exalt.library.models.Borrower;
import com.exalt.library.models.SingletonLibrary;
import com.exalt.library.models.libraryitems.LibraryItem;
import com.exalt.library.models.reservation.Reservation;
import com.exalt.library.services.BorrowerServices;
import com.exalt.library.services.LibraryItemServices;
import com.exalt.library.services.ReservationServices;
import com.exalt.library.services.borrowtype.InHandBorrowStrategyService;
import com.exalt.library.services.borrowtype.OnlineBorrowStrategyService;
import com.exalt.library.services.factory.BorrowStrategyFactory;
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
 * a reservation controller that gets a request from the client, does a specific job then returns the response
 * @author Mohammad Rimawi
 */
public class ReservationController implements HttpHandler {
    ReservationServices reservationServices = initializeReservationServices();
    Gson gson = new Gson(); // Google JSON Library for converting between JSON and Java objects

    /**
     * a method for initializing the reservation services, where we can use the logic it delivers
     * @return the reservation services
     */
    private ReservationServices initializeReservationServices() {
        ReservationServices reservationServices = new ReservationServices();

        BorrowStrategyFactory factory = new BorrowStrategyFactory();
        factory.setInHandStrategy(new InHandBorrowStrategyService());
        factory.setOnlineStrategy(new OnlineBorrowStrategyService());

        reservationServices.setLibraryItemOperations(new LibraryItemServices());
        reservationServices.setBorrowerOperations(new BorrowerServices());
        reservationServices.setBorrowStrategyFactory(factory);

        return reservationServices;
    }

    /**
     * CONSTANTS FOR DEFINING API ENDPOINTS
     */
//    ==== GET ====
    private final String GET_ALL_API_RESERVATIONS = "/api/reservations";
    private final String GET_API_RESERVATIONS_ID = "/api/reservations/";

//    === POST ====
    private final String POST_API_RESERVATIONS = "/api/reservations";

//    ==== SUFFIX ====
    private final String RETURN = "/return";
    private final String CLAIM = "/claim";

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
            if("GET".equalsIgnoreCase(method) && path.equals(GET_ALL_API_RESERVATIONS)) {
                handleGetAll(exchange);
            } else if("GET".equalsIgnoreCase(method) && path.startsWith(GET_API_RESERVATIONS_ID)
                    && !path.endsWith(RETURN) && !path.endsWith(CLAIM)) {
                handleGetOne(exchange, path);
            } else if("POST".equalsIgnoreCase(method) && path.equals(POST_API_RESERVATIONS)) {
                handleCreate(exchange);
            } else if("POST".equalsIgnoreCase(method) && path.startsWith(GET_API_RESERVATIONS_ID) && path.endsWith(RETURN)) {
                handleReturn(exchange, path);
            } else if("POST".equalsIgnoreCase(method) && path.startsWith(GET_API_RESERVATIONS_ID) && path.endsWith(CLAIM)) {
                handleClaim(exchange, path);
            } else if("DELETE".equalsIgnoreCase(method) && path.startsWith(GET_API_RESERVATIONS_ID)) {
                handleCancel(exchange, path);
            } else {
                Json.sendJSON(exchange, 404, gson.toJson(Map.of("error", "no such route")));
            }
        } catch (ReservationNotFoundException e) {
            Json.sendJSON(exchange, 404, gson.toJson(ApiResponse.error(404, "Not Found", e.getMessage())));
        } catch (NumberFormatException e) {
            Json.sendJSON(exchange, 400, gson.toJson(ApiResponse.error(400, "Bad Request", "Invalid reservation id")));
        } catch (IllegalArgumentException | IllegalStateException e) {
            Json.sendJSON(exchange, 400, gson.toJson(ApiResponse.error(400, "Bad Request", e.getMessage())));
        } catch (Exception e) {
            Json.sendJSON(exchange, 500, gson.toJson(Map.of("error", "something broke: " + e.getMessage())));
        }
    }

    /**
     * a method for fetching all the reservations
     * @param exchange
     * @throws IOException
     */
    private void handleGetAll(HttpExchange exchange) throws IOException{
        List<Reservation> reservations = SingletonLibrary.getInstance().getReservations();
        Json.sendJSON(exchange, 200, gson.toJson(ApiResponse.success(200, reservations)));
    }

    /**
     * a method for fetching a specific reservation
     * @param exchange
     * @param path
     * @throws IOException
     */
    private void handleGetOne(HttpExchange exchange, String path) throws IOException {
        int id = Integer.parseInt(path.substring(path.lastIndexOf('/') + 1));
        Reservation reservation = reservationServices.findReservationById(SingletonLibrary.getInstance().getReservations(), id);
        Json.sendJSON(exchange, 200, gson.toJson(ApiResponse.success(200, reservation)));
    }

    /**
     * a method for creating a reservation
     * @param exchange
     * @throws IOException
     */
    private void handleCreate(HttpExchange exchange) throws IOException {
        String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
        Map<String, Double> request = gson.fromJson(body, Map.class);

        int borrowerId = request.get("borrowerId").intValue();
        int itemId = request.get("itemId").intValue();

        Reservation reservation = reservationServices.reserve(SingletonLibrary.getInstance().getReservations(),
                SingletonLibrary.getInstance().getLibraryItems(),
                SingletonLibrary.getInstance().getBorrowers(),
                borrowerId,
                itemId);

        Json.sendJSON(exchange, 201, gson.toJson(ApiResponse.success(201, reservation)));
    }

    /**
     * a method for handling a borrower returning an item tied to an active reservation
     * @param exchange
     * @param path
     * @throws IOException
     */
    private void handleReturn(HttpExchange exchange, String path) throws IOException {
        String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
        Map<String, Double> request = gson.fromJson(body, Map.class);

        int borrowerId = request.get("borrowerId").intValue();
        int itemId = request.get("itemId").intValue();

        LibraryItem item = reservationServices.checkForLibraryItem(SingletonLibrary.getInstance().getLibraryItems(), itemId);
        Borrower borrower = reservationServices.checkForBorrower(SingletonLibrary.getInstance().getBorrowers(), borrowerId);

        boolean closed = reservationServices.returnItem(SingletonLibrary.getInstance().getReservations(), item, borrower);

        Json.sendJSON(exchange, 200, gson.toJson(ApiResponse.success(200, Map.of("returned", closed))));
    }

    /**
     * a method for handling a borrower claiming a READY reservation
     * @param exchange
     * @param path
     * @throws IOException
     */
    private void handleClaim(HttpExchange exchange, String path) throws IOException {
        int id = Integer.parseInt(path.substring(GET_API_RESERVATIONS_ID.length(), path.indexOf(CLAIM)));
        Reservation reservation = reservationServices.findReservationById(SingletonLibrary.getInstance().getReservations(), id);

        Reservation claimed = reservationServices.claimReservation(reservation);

        Json.sendJSON(exchange, 200, gson.toJson(ApiResponse.success(200, claimed)));
    }

    /**
     * a method for cancelling a reservation, e.g. DELETE /api/reservations/5
     * @param exchange
     * @param path
     * @throws IOException
     */
    private void handleCancel(HttpExchange exchange, String path) throws IOException {
        int id = Integer.parseInt(path.substring(path.lastIndexOf('/') + 1));
        List<Reservation> reservations = SingletonLibrary.getInstance().getReservations();

        Reservation reservation = reservationServices.findReservationById(reservations, id);
        boolean cancelled = reservationServices.cancelReservation(reservations, reservation);

        Json.sendJSON(exchange, 200, gson.toJson(ApiResponse.success(200, Map.of("cancelled", cancelled))));
    }
}