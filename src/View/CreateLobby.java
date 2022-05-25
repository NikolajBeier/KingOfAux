package View;

import Controller.Server;
import Model.Information;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.Socket;

public class CreateLobby {
    static int currentPlayers = -1;
    public static int readyPlayers = 0;
    static Button start = new Button("Start Game!");
    Button ready = new Button("Ready Up");
    public CreateLobby(StackPane layout){
        VBox vBox = new VBox();
        Server server = new Server(4999);
        server.start();
        layout.getChildren().clear();
        HBox hBox = new HBox();

        Runnable task = new Runnable() {
            @Override public void run(){
                while(true){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(currentPlayers!=Information.players.size()){
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if (currentPlayers!=Information.players.size()) {

                                    vBox.getChildren().clear();
                                    for (int i = 0; i < Information.players.size(); i++) {
                                        System.out.println(Information.players.get(i));
                                        Label name = new Label(Information.players.get(i).getName());
                                        vBox.getChildren().add(name);

                                    }
                                    currentPlayers = Information.players.size();
                                }
                            }
                        });
                    }
                }
            }
        };

        new Thread(task).start();

        start.setVisible(false);
        ready.setOnAction(values->{
            readyPlayers++;

            vBox.getChildren().remove(ready);
            ready.setVisible(false);
        });
        start.setOnAction(values->{
        });
        hBox.getChildren().add(start);
        hBox.getChildren().add(ready);
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        vBox.setAlignment(Pos.CENTER_LEFT);

        layout.getChildren().add(vBox);
        layout.getChildren().add(hBox);

    }
    public static Button getStart(){
        return start;
    }
}
