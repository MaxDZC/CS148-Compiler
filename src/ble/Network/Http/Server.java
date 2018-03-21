package ble.Network.Http;

import java.util.ArrayList;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import ble.Network.Memory.ServerMemory;
import ble.Network.Storage.FileDirectory;
import com.sun.net.httpserver.Headers;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Server {

    protected int port;

    protected Response response = null;

    protected Request request = null;

    protected ServerMemory memory = null;
    
    protected FileDirectory directory = null;

    protected List<Session> sesions = null;


    public Server(int port) {
            this.port = port;

            this.build();
    }

    public Server() {
            this.port = 80;

            this.build();
    }


    //

    public void build() {
            this.memory = new ServerMemory();
            this.sesions = new ArrayList<Session>();
            this.directory = new FileDirectory();
            
            this.directory.setPath("bledocs/" + this.port + "/");
    }
    
    public void start() throws Exception {
        
        HttpServer server = HttpServer.create(new InetSocketAddress(this.port), 0);
        // @TODO Tentative
        server.createContext("/", new ServerHandler(this));
        server.setExecutor(null);
        server.start();
    }
    
    public void end() {
        
    }
    
    
    // Components
    
    public FileDirectory directory() {
        return this.directory;
    }


    //

    protected void setPort(int port) {
            this.port = port;
    }

    public int getPort() {
            return this.port;
    }


    //

    public void setResponse(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return this.response;
    }


    //

    public void setRequest(Request request) {
        this.request = request;
    }

    public Request getRequest() {
        return this.request;
    }
    
    
    //

    public class ServerHandler implements HttpHandler {
        
        protected Server server = null;
        
        public ServerHandler(Server server) {
            this.server = server;
            
            
            
        }
        
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            ble.Http.Response response = new ble.Http.Response();
            
            String file = this.server.directory().getPath() + exchange.getRequestURI().toString();
            String exURI = exchange.getRequestURI().toString();
            String f2 = "";
            int i;
            
            String eu = "";
            for(i = 0; i < exURI.length() && exURI.charAt(i) != '?'; i++){
                eu += exURI.charAt(i);
            }
            exURI =eu;
            for(i = 0; i < file.length() && file.charAt(i) != '?'; i++){
                f2 += file.charAt(i);
            }
            file = f2;
            
            System.out.println(file);
            if ( ! (new File(file)).exists()) {
                file = this.server.directory().getPath() + exURI + ".ble";
            }
            
            if ( ! (new File(file)).exists()) {
                file = "bledocs/defaults/404.ble";
            }
                
                    
            String uri = exchange.getRequestURI().toString();
            for( i = 0; i < uri.length() && uri.charAt(i) != '?'; i++){}
            uri = uri.substring(i);
            uri = uri.replace("?", "");
            
            String[] result;
            result = uri.split("&");
            System.out.println(uri);
            Request.parseQueryString(uri);
            
                       
                   
            /*
            if (exchange.getRequestMethod().equalsIgnoreCase("GET")) {
                
                try {
                   Headers requestHeaders = exchange.getRequestHeaders();
                   Set<Map.Entry<String, List<String>>> entries = requestHeaders.entrySet();

                   int contentLength = Integer.parseInt(requestHeaders.getFirst("Content-length"));
                   System.out.println(""+requestHeaders.getFirst("Content-length"));

                   InputStream is = exchange.getRequestBody();

                   byte[] data = new byte[contentLength];
                   int length = is.read(data);

                   Headers responseHeaders = exchange.getResponseHeaders();

                   exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, contentLength);

                   OutputStream os = exchange.getResponseBody();

                   os.write(data);
                   exchange.close();

                }catch (NumberFormatException | IOException e) {
                }
            }
            */
            
            
            
            
            
            
            
            // @TENTATIVE
            String responseBody = new String(Files.readAllBytes(Paths.get(file)), StandardCharsets.UTF_8);

            //responseBody = responseBody.replace("{{ BLE.QUERY_PARAMETERS }}", uri);
            
            
            //for( i = 0; i < result[0].length() && result[0].charAt(i) != '='; i++){}
            //result[0] = result[0].substring(i);
            //for( i = 0; i < result[1].length() && result[1].charAt(i) != '='; i++){}
            //result[1] = result[1].substring(i);
            System.out.println(result.length);
            if(uri.length() > 0){
                
            // System.out.println(result[0]);
            responseBody = responseBody.replace("{{ BLE.ADD_1 }}", (result[0].split("="))[1]);
            responseBody = responseBody.replace("{{ BLE.ADD_2 }}", (result[1].split("="))[1]);
            }
            else {
                
            responseBody = responseBody.replace("{{ BLE.ADD_1 }}", "");
            responseBody = responseBody.replace("{{ BLE.ADD_2 }}", "");
            }
            
            response.setBody(responseBody);
            response.setStatus(200);

            exchange.sendResponseHeaders(response.getStatus(), response.getBody().length());

            OutputStream stream = exchange.getResponseBody();
            stream.write(response.getBody().getBytes());
            stream.close();
        }
    }
}