package Controller;

import java.io.*;
import java.net.Socket;

public class ClientThread extends Thread {
    protected Socket socket;
    public ClientThread(Socket clientSocket){ this.socket = clientSocket; }

    public void run(){
        InputStream inp = null;
        BufferedReader brinp = null;
        DataOutputStream out = null;
        PrintWriter printout = null;
        DataInputStream inFromServer = null;
        try {
            inp = socket.getInputStream();
            brinp = new BufferedReader(new InputStreamReader(inp));
            out = new DataOutputStream(socket.getOutputStream());
            printout = new PrintWriter(socket.getOutputStream(), true);
            inFromServer = new DataInputStream(socket.getInputStream());
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
                        case "PLAYERNAMES":
                            ClientHandles.handleNewPlayer(command);
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
