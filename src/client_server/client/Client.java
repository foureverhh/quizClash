package client_server.client;

import front_end.UserInterface;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        new UserInterface();

        try {
            Socket client = new Socket("localhost", 8888);
            new Thread(new ClientSend(client)).start();
            new Thread(new ClientReceive(client)).start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
