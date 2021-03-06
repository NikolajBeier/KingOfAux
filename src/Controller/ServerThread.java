package Controller;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    protected Socket socket;
    public ServerThread(Socket clientSocket){
        this.socket = clientSocket;
    }
    public void run(){
        InputStream inp = null;
        BufferedReader brinp = null;
        DataOutputStream out = null;
        PrintWriter printout = null;
        try {
            inp = socket.getInputStream();
            brinp = new BufferedReader(new InputStreamReader(inp));
            out = new DataOutputStream(socket.getOutputStream());
            printout = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            return;
        }
        String line;
        while (true) {
            try {
                line = brinp.readLine();
                if(!line.equals("null")){
                    String[] command = line.split(":",3);
                    System.out.println("Virker");
                    switch(command[0]){
                        case "INITPLAYER":
                            ServerHandles.handleNewPlayer(socket, command);
                            break;
                        case "PLAYERREADY":
                            ServerHandles.playerReady();
                            break;
                    }
                    //out.writeBytes(line + "\n\r");
                    //out.flush();
                }
            } catch (IOException e) {
                System.out.println("Lukker exception");
                e.printStackTrace();
                return;
            }
        }
    }
}
