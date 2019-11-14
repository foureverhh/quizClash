package client_server.server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server  {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8888);
        System.out.println("-----server started-----");

        boolean serverIsRunning = true;
        while (serverIsRunning) {
            Socket client = server.accept();
            System.out.println("-----a client connected-----");
            new Thread(new Channel(client)).start();
        }
        server.close();
    }
}

