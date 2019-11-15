package client_server.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

class Channel implements Runnable {
    private CopyOnWriteArrayList<Channel> channels ;
    private BufferedReader reader;
    private PrintWriter writer;
    private Socket client;
    private String msg;
    private boolean clientIsRunning;
    private String clientName;


    public Channel(Socket client) {
        this.client = client;
        channels = Server.channels;
        clientIsRunning=true;
        try {
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            clientName = receive();
            writer = new PrintWriter(client.getOutputStream(),true);
        } catch (IOException e) {
            System.out.println("!");
            release();
        }
    }

    public String receive() {
        String input = null;
        try {
            input  = reader.readLine();
            if(input != null)
                System.out.println("Server receive: "+input);
            if(input.equals("bye")) {
                release();
                System.out.println("!!");
            }
        }catch (Exception e){
            System.out.println("no info from client, client shut down");
            release();
        }
        return input;
    }

    public void send(String msg){
        if(msg!=null)
            writer.println(msg);
    }

    public void sendToOthers(String msg){
        for(Channel channel:channels){
            if (channel != this){
                System.out.println("Sending to chanel: " + channel.clientName);
                 channel.send(msg);
                 System.out.println("sendToOthers: **** "+msg);
            }
        }
        System.out.println("Channels size in Channel: "+channels.size());
    }
    @Override
    public void run() {
        while (clientIsRunning){
            msg = receive();
            //send(msg);
            if(msg==null){
                msg = clientName+" has left";
            }
            sendToOthers(msg);
        }
        System.out.println("!!!");
        release();
    }

    public void release()  {
        try {
            clientIsRunning = false;
            //channels.remove(this);
            Server.channels.remove(this);
            System.out.println(Server.channels.size());
            reader.close();
            writer.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}