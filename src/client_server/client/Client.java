package client_server.client;

import front_end.UserInterface;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        new UserInterface();

        //private boolean isMatched = false;


        System.out.println("Input your user name:");
        Scanner scanner = new Scanner(System.in);
        String userName = scanner.nextLine();

        try {
            Socket client = new Socket("localhost", 8888);
            new Thread(new ClientSend(client,userName)).start();
            new Thread(new ClientReceive(client)).start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

/*    public void setMatched(boolean matched) {
        isMatched = matched;
    }

    public boolean isMatched() {
        return isMatched;
    }*/
}
