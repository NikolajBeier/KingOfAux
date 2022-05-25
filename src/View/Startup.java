package View;

import Controller.Client;
import Controller.Server;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.io.IOException;

public class Startup {
    public Startup(StackPane layout){
        Label title = new Label("KING OF AUX");
        HBox clickable = new HBox();
        VBox titlebox = new VBox();
        Button startServer = new Button("Start Game");
        Button joinServer = new Button("Join Game");
        TextField serverInfo = new TextField("Type in server info!");
        TextField name = new TextField("Type in name!");
        startServer.setOnAction(value->{
            CreateLobby createLobby = new CreateLobby(layout);
        });
        joinServer.setOnAction(value->{
            Client client = new Client();
            String str = serverInfo.getText();
            try {
                client.start(str, 4999);
            } catch (IOException e) {
                System.out.println("Could not join server!");
            }
        });
        title.setFont(new Font(30));
        clickable.getChildren().add(startServer);
        clickable.getChildren().add(joinServer);
        clickable.getChildren().add(serverInfo);
        clickable.setAlignment(Pos.BOTTOM_CENTER);
        clickable.setSpacing(10);
        titlebox.getChildren().add(title);
        titlebox.setAlignment(Pos.TOP_CENTER);

        layout.getChildren().add(titlebox);
        layout.getChildren().add(clickable);

    }
}
