package client_server.server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server  {
    public static CopyOnWriteArrayList<Channel> channels = new CopyOnWriteArrayList<>();
    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(8888);
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

        server.close();
    }


}

