package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();

        /**
         * Para poder invocar de estar forma al resource, se debe establecer que la carpeta llamada
         * "resources" es de tipo resources.
         */
        loader.setLocation(getClass().getResource("/main_window.fxml"));
        Parent root = loader.load();

        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/chip_icon.png")));
        primaryStage.setTitle("Fuses Configurator");
        primaryStage.setScene(new Scene(root, 600, 740));
        primaryStage.setResizable(false);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
