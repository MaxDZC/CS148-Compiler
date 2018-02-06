/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ble.Http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/**
 *
 * @author Max
 */
public class Server {

    public static void server(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(80), 0);

        // @TODO Tentative
        server.createContext("/test", new ServerHandler());
        server.setExecutor(null);
        server.start();
    }

    static class ServerHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            Response response = new Response();

            response.setBody("Sample test response");
            response.setStatus(200);

            exchange.sendResponseHeaders(response.getStatus(), response.getBody().length());

            OutputStream stream = exchange.getResponseBody();
            stream.write(response.getBody().getBytes());
            stream.close();
        }
    }
}
