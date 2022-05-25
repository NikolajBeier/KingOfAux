package Controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
    public void start(String str, int port) throws IOException {
        try {
            Socket s = new Socket(str, port);
            joinGame("Walla", s);
        }catch(Exception e){
            System.out.println("Could not connect");
        }
    }
    private static void joinGame(String name, Socket s) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        writer.write("INITPLAYER:NAME->"+name+"\n");
        writer.flush();
    }
}
