package Controller;

import Model.Information;
import View.CreateLobby;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends Thread{

    int port;
    public Server(int port){
        this.port = port;
    }

    public void run() {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(port);


        } catch (IOException e) {
            System.out.println("Could not start server!");
            this.interrupt();
        }
        Socket s = null;
        while(true){
            try{
                s = ss.accept();
                System.out.println(s.getInetAddress().getHostAddress() + " has entered the lobby!");
            } catch(IOException e){
                System.out.println("could not connect to client");
            }
            new ServerThread(s).start();
        }
    }

}
