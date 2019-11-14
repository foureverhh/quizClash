package client_server.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class Channel implements Runnable {
    private BufferedReader reader;
    private PrintWriter writer;
    private Socket client;
    private String msg;
    private boolean clientIsRunning;

    public Channel(Socket client) {
        this.client = client;
        try {
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            writer = new PrintWriter(client.getOutputStream(),true);
        } catch (IOException e) {
            release();
        }
    }

    public String receive() {
        String input = null;
        try {
            input  = reader.readLine();
            if(input.equals("bye")) {
                release();
            }
        }catch (Exception e){
            System.out.println("no info from client, client shut down");
            release();
        }
        return input;
    }

    public void send(String string){
        writer.println(string);
    }

    @Override
    public void run() {
        while (clientIsRunning){
            msg = receive();
            send(msg);
        }
        release();
    }

    public void release()  {
        try {
            clientIsRunning = false;
            reader.close();
            writer.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}