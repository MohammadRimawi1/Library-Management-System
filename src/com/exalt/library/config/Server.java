package com.exalt.library.config;

import com.exalt.library.controllers.BorrowerController;
import com.exalt.library.controllers.LibraryItemController;
import com.exalt.library.controllers.ReservationController;
import com.exalt.library.models.SingletonLibrary;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;
import java.util.ArrayList;

/**
 * a class for creating the server itself
 * @author Mohammad Rimawi
 */
public class Server {
    public static void start(int port) throws Exception {
        SingletonLibrary lib = SingletonLibrary.getInstance();
        lib.setLibraryItems(new ArrayList<>());
        lib.setBorrowers(new ArrayList<>());
        lib.setReservations(new ArrayList<>());

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/api/borrowers", new BorrowerController());
        server.createContext("/api/library-items", new LibraryItemController());
        server.createContext("/api/reservations", new ReservationController());

        server.setExecutor(null);
        server.start();
        System.out.println("Server running on http://localhost:" + port);
    }
}
