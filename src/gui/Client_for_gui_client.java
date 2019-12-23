package gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Client_for_gui_client{
    private String ip;
    private int port;
    private String username;
    //private Player player;


    public Client_for_gui_client(String username,String ip,int port) {
        this.username = username;
        this.ip = ip;
        this.port = port;
        //player = new Player(username);
     /*   System.out.println("Input your user name:");
        Scanner scanner = new Scanner(System.in);*/
        //new UserInterface();
       // String userName = scanner.nextLine();

        try {
            System.out.println("!!!!!!!!!");
            Socket client = new Socket(ip, port);
       /*     new Thread(new ClientSend(client,userName)).start();
            new Thread(new ClientReceive(client)).start();*/
        //keep on sending and receiving
            new SendInfo(client,username).start();
            new ReceiveInfo(client).start();

            System.out.println("Client side connection succeed");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    class ReceiveInfo extends Thread{
        private Socket client;
        private BufferedReader reader;
        private boolean clientIsRunning;

        public ReceiveInfo(Socket client) {
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
                String msg = null;
                if((msg =reader.readLine())!=null)
                    System.out.println("Client receive: "+msg);
                //System.out.println(reader.readLine());
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

    class SendInfo extends Thread{
        private Socket client;
        private PrintWriter writer;
        private boolean clientIsRunning;
        private Scanner scanner;
        private String msg;
        private String username;

        public SendInfo(Socket client,String username){
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

/*    public static void main(String[] args) {
        new Client_for_gui_client("Test","localhost",8888).start();
    }*/
}
