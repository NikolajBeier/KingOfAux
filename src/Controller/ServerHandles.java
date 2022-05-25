package Controller;

import Model.Information;
import Model.Player;
import View.CreateLobby;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


public class ServerHandles {
    public static void handleNewPlayer(Socket s, String[] commands){
        String[] name = commands[1].split("->",2);
        Player player = new Player(name[1], s);
        Information.players.add(player);
        for(int i = 0; i < Information.players.size(); i++){
            try {
                if(Information.players.get(i).getSocket()!=null){
                    Socket socket = Information.players.get(i).getSocket();
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    for(int j = 0; j < Information.players.size(); j++){
                        out.write("PLAYERNAMES:"+Information.players.get(j).getName()+"\n");
                    }
                    out.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void playerReady(){
        CreateLobby.readyPlayers++;
        if(Information.players.size()==CreateLobby.readyPlayers){
            CreateLobby.getStart().setVisible(true);
        } else{
            CreateLobby.getStart().setVisible(false);
        }
    }
}
