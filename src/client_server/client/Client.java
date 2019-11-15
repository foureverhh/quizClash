package client_server.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private boolean isMatched = false;

    public static void main(String[] args) {
        Socket client = null;
        System.out.println("Input your user name:");
        Scanner scanner = new Scanner(System.in);
        String userName = scanner.nextLine();
        try {
            client = new Socket("localhost", 8888);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        new Thread(new ClientSend(client,userName)).start();
        new Thread(new ClientReceive(client)).start();
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }

    public boolean isMatched() {
        return isMatched;
    }
}
