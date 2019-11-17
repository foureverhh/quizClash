package client_server.server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server  {
    public static CopyOnWriteArrayList<Channel> channels = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        new Server();
    }

    public Server() {
        ServerSocket server = null;
        channels = new CopyOnWriteArrayList<>();
        try {
            server = new ServerSocket(8888);
        System.out.println("-----server started-----");

        boolean serverIsRunning = true;
        while (serverIsRunning) {
            Socket client = server.accept();
            Channel channel = new Channel(client);
            System.out.println("-----a client connected-----");
            new Thread(channel).start();

            channels.add(channel);

            System.out.println("channels size in Server: "+channels.size());

        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

