package Controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
    String name;
    public Client(String name){
        this.name = name;
    }
    public void start(String str, int port) throws IOException {
            Socket s = new Socket(str, port);
            joinGame(name, s);
            ClientHandles clientHandles = new ClientHandles(s);
    }
    private static void joinGame(String name, Socket s) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        writer.write("INITPLAYER:NAME->"+name+"\n");
        writer.flush();
    }
}
