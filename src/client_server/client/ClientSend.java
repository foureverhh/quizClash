package client_server.client;

import java.net.Socket;

class ClientSend implements Runnable{
    private Socket client;

    public ClientSend(Socket client){
        this.client = client;
    }
    @Override
    public void run() {

    }
}
