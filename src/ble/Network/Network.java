package ble.Network;

import ble.Network.Http.Server;
import java.util.HashMap;

public class Network {

    protected HashMap<Integer, Server> servers = null;
    
    public Network() {
        this.servers = new HashMap<Integer, Server>();
    }

    public Server createServer(int port, String[] args) {
            Server server = null;

            this.servers.put(port, server);

            return server;
    }
    
    public Server createServer(int port) {
        Server server = new Server(port);

        this.servers.put(port, server);
            
        return server;
    }

    public boolean destroyServer(int port) {
            boolean destroyed = false;

            return destroyed;  
    }

    public boolean hasServer(int port) {
        return this.servers.containsKey(port);
    }
}