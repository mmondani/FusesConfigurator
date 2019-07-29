package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/main_window.fxml"));
        Parent root = loader.load();
        //Parent root = FXMLLoader.load(getClass().getResource("../resources/main_window.fxml"));
        primaryStage.setTitle("Fuses Configurator");
        primaryStage.setScene(new Scene(root, 600, 740));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
