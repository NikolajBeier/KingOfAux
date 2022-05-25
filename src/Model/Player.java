package Model;

import java.net.Socket;

public class Player {
    String name;
    Socket s;
    public Player(String name, Socket s){
        this.name = name;
        this.s = s;
    }
    public Socket getSocket(){
        return s;
    }
    public String getName(){
        return name;
    }
}
