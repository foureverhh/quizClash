package client_server.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientReceive implements Runnable{
    private Socket client;
    private BufferedReader reader;
    private boolean clientIsRunning;

    public ClientReceive(Socket client) {
        this.client = client;
        clientIsRunning = true;
        try {
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            System.out.println("ClientReceive reader set up wrong");
           release();
        }
    }

    @Override
    public void run() {
        while (clientIsRunning){
            receive();
        }
        release();
    }

    void receive(){
        try {
            if(reader.readLine()!=null)
                System.out.println("Client receive: "+reader.readLine());
        } catch (IOException e) {
            release();
        }
    }

    void release(){
        clientIsRunning = false;
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
