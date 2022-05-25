import Model.Information;
import View.Startup;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

import java.awt.*;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("YoutubeHans");
        StackPane layout = new StackPane();
        Startup startup = new Startup(layout);


        Scene scene = new Scene(layout,960, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void initializeVideo(VBox vBox, String str){
        WebView webView = new WebView();
        webView.getEngine().load(str);
            initializeVideo(vBox, "https://www.youtube.com/embed/5YXB4MRfAow?&autoplay=1");
        vBox.getChildren().add(webView);
    }
}
