package main.java;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;


public class MainWindowController implements Initializable {
    public ComboBox<String> pics_comboBox;
    public ListView fusesList_listView;
    public TextArea result_textArea;
    public Button copy_button;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PicsFusesModel.getInstance();
    }
}
