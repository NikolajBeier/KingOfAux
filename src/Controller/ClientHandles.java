package Controller;

import Model.Information;
import Model.Player;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.LinkedList;

public class ClientHandles {
    static Socket socket;
    static BufferedWriter writer;
    public ClientHandles(Socket socket) throws IOException {
        this.socket = socket;
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }
    public static void readyUp() throws IOException {
        writer.write("PLAYERREADY\n");
        writer.flush();
    }
    public static void handleNewPlayer(String[] commands) {
        Player player = new Player(commands[1], null);
        boolean contains = false;
        for(int i = 0; i < Information.players.size(); i++){
            if(Information.players.get(i).getName().equals(commands[1])){
                contains=true;
            }
        }
        if(!contains) {
            Information.players.add(player);
        }
    }
}
