package main.java;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoadConfigWordsWindowController implements Initializable {

    public TextField word1_textField;
    public TextField word2_textField;
    public Button accept_button;
    public Button cancel_button;

    private MainWindowController mainWindowController = null;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cancel_button.setOnMouseClicked(event -> {
            Stage stage = (Stage)cancel_button.getScene().getWindow();
            stage.close();
        });


        accept_button.setOnMouseClicked(event -> {
            if (mainWindowController != null)
                mainWindowController.loadConfigWordsWindow_onAcceptClicked(word1_textField.getText(), word2_textField.getText());

            Stage stage = (Stage)cancel_button.getScene().getWindow();
            stage.close();
        });
    }

    public void setMainController (MainWindowController controller) {
        mainWindowController = controller;
    }
}
