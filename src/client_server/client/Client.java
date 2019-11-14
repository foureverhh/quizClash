package client_server.client;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Socket client = null;
        try {
            client = new Socket("localhost", 8888);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        new Thread(new ClientSend(client)).start();
        new Thread(new ClientReceive(client)).start();
    }

}
