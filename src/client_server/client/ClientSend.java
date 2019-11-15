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
    private String username;

    public ClientSend(Socket client,String username){
        this.client = client;
        clientIsRunning = true;
        this.username = username;
        scanner = new Scanner(System.in);
        try {
            writer = new PrintWriter(client.getOutputStream(),true);
            send(username);
        } catch (IOException e) {
            System.out.println("Client send writer error");
            release();
        }
    }
    @Override
    public void run() {
        while (clientIsRunning){
            msg = scanner.nextLine();
            //System.out.println(username+" sends: "+msg);

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
        send(username+" has shutdown");
        clientIsRunning = false;
        writer.close();
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
