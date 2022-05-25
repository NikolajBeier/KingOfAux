package View;

import Controller.ClientHandles;
import Controller.Server;
import Model.Information;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Lobby {
    StackPane layout;
    static int currentPlayers = -1;
    public Lobby(StackPane layout){
        this.layout = layout;
        VBox vBox = new VBox();
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
                    if(currentPlayers!= Information.players.size()){
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

        Button ready = new Button("Ready Up");
        ready.setOnAction(values->{
            try {
                ClientHandles.readyUp();
            } catch (IOException e) {
                System.out.println("Couldnt ready up");
            }
            ready.setVisible(false);
        });
        hBox.getChildren().add(ready);
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        vBox.setAlignment(Pos.CENTER_LEFT);

        layout.getChildren().add(vBox);
        layout.getChildren().add(hBox);
    }
}
