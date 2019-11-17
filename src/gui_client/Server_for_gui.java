package gui_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server_for_gui {
    private CopyOnWriteArrayList<AliveConnection> connections ;

    private boolean serverIsRunning = true;

    public static void main(String[] args) {
        new Server_for_gui();
    }

    public Server_for_gui() {
        ServerSocket server = null;
        connections = new CopyOnWriteArrayList<>();
        try {
            server = new ServerSocket(8888);
            System.out.println("-----server started-----");

            while (serverIsRunning) {

                Socket client = server.accept();

                System.out.println("-----a client connected-----");
                AliveConnection connection = new AliveConnection(client);
                connection.start();
                System.out.println("before channels size in Server: "+connections.size());
                connections.add(connection);
                System.out.println("after channels size in Server: "+connections.size());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class AliveConnection extends Thread{
        private BufferedReader reader;
        private PrintWriter writer;
        private Socket client;
        private String msg;
        private boolean clientIsRunning;
        private String clientName;


        public AliveConnection(Socket client) {
            this.client = client;
            clientIsRunning=true;
            try {
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            writer = new PrintWriter(client.getOutputStream(),true);
                clientName = receive();
            } catch (IOException e) {
                System.out.println("!");
                release();
            }
        }

        public String receive() {
            String input = "";
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
            for(AliveConnection connection:connections){
                if (connection != this){
                    System.out.println("Sending to chanel: " + connection.clientName);
                    connection.send(msg);
                    System.out.println("sendToOthers: " +
                            ""+msg);
                }
            }
            System.out.println("Connections size in AliveConnection: "+connections.size());
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
                connections.remove(this);
                System.out.println("Connections size in AliveConnection in release: "+connections.size());
                //System.out.println(Server.channels.size());
                if(reader!=null)
                    reader.close();
                if(writer!=null)
                    writer.close();
                if(client!=null)
                    client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}

