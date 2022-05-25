package Controller;

import Model.Information;
import Model.Player;

import java.net.Socket;

public class ServerHandles {
    public static void handleNewPlayer(Socket s, String[] commands){
        String[] name = commands[1].split("->",2);
        Player player = new Player(name[1], s);
        Information.players.add(player);
    }
}
