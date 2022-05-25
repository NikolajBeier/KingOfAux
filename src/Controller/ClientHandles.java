package Controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

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
}
