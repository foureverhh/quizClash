package client_server.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

class ClientSend implements Runnable{
    private Socket client;
    private PrintWriter writer;
    private boolean clientIsRunning;
    private Scanner scanner;
    private String msg;

    public ClientSend(Socket client){
        this.client = client;
        clientIsRunning = true;
        scanner = new Scanner(System.in);
        try {
            writer = new PrintWriter(client.getOutputStream(),true);
        } catch (IOException e) {
            System.out.println("Client send writer error");
            release();
        }
    }
    @Override
    public void run() {
        while (clientIsRunning){
            msg = scanner.nextLine();
            System.out.println("Client sends "+msg);
            if(msg!=null || !msg.isEmpty())
                send(msg);
        }
        release();

    }

    void send(String msg){
        if(msg.equals("bye")){
            System.out.println("user shut down client");
            release();
        }else {
            writer.println(msg);
        }
    }

    void release(){
        clientIsRunning = false;
        writer.close();
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
