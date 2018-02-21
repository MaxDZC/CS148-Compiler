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
    private static String response;
    private static String context;
    private static int socketNo;
    /**
     *
     * @param response
     */
    public void setResponse(String response){
        Server.response = response;
    }
    
    
    public void setContext(String context){
        if(context != null){
            int position = context.lastIndexOf(".");
            String result = context;
            
            if(position != -1){
                result = result.substring(0, position);
            }
            
            Server.context = "/"+result;
        }
    }
    
    public void setSocketNo(int socketNo){
        Server.socketNo = socketNo;
    }
    
    public String getResponse(){
        return response;
    }
    
    public String getContext(){
        return context;
    }
    
    public int getSocketNo(){
        return socketNo;
    }

    public static void server(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(Server.socketNo), 0);
        // @TODO Tentative
        server.createContext(Server.context, new ServerHandler());
        server.setExecutor(null);
        server.start();
    }

    static class ServerHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            Response response = new Response();

            response.setBody(Server.response);
            response.setStatus(200);

            exchange.sendResponseHeaders(response.getStatus(), response.getBody().length());

            OutputStream stream = exchange.getResponseBody();
            stream.write(response.getBody().getBytes());
            stream.close();
        }
    }
}
