package client_server.client;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {

        try {
            Socket client = new Socket("localhost", 8888);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
