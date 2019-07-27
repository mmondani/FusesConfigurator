package main.java;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;


public class MainWindowController implements Initializable {
    public ComboBox<String> pics_comboBox;
    public ListView<PicsFusesModel.Fuse> fusesList_listView;
    public TextArea result_textArea;
    public Button copy_button;

    private Map<String, Integer> comboBoxesSelectedValues;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pics_comboBox.getItems().addAll(PicsFusesModel.getInstance().getPics());
        pics_comboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            List<PicsFusesModel.Fuse> fusesList = PicsFusesModel.getInstance().getPicFuses(newValue.toString());

            fusesList_listView.setItems(null);
            fusesList_listView.setItems(FXCollections.observableArrayList(fusesList));

            comboBoxesSelectedValues = new HashMap<>(fusesList.size());
            for (int i = 0; i < fusesList.size(); i++) {
                comboBoxesSelectedValues.put(fusesList.get(i).getName(), 0);
            }

            updateOutputText();
        });
        pics_comboBox.getSelectionModel().select(0);


        fusesList_listView.setCellFactory(new Callback<ListView<PicsFusesModel.Fuse>, ListCell<PicsFusesModel.Fuse>>() {
            @Override
            public ListCell<PicsFusesModel.Fuse> call(ListView<PicsFusesModel.Fuse> param) {
                return new FuseCell();
            }
        });
    }

    private void updateOutputText () {
        for (int i = 0; i < fusesList_listView.getItems().size(); i++) {
            PicsFusesModel.Fuse fuse = (PicsFusesModel.Fuse)fusesList_listView.getItems().get(i);

            System.out.print(fuse.getName() + " : ");
            System.out.print(fuse.getValues().get(comboBoxesSelectedValues.get(fuse.getName())));
            System.out.println();
        }
    }

    private class FuseCell extends ListCell<PicsFusesModel.Fuse> {
        @Override
        protected void updateItem(PicsFusesModel.Fuse item, boolean empty) {
            super.updateItem(item, empty);

            Parent root = null;
            if (item != null) {
                try {
                    root = FXMLLoader.load(getClass().getResource("../resources/fuse_list_element.fxml"));

                    Label fuseName_label;
                    Label fuseDescription_label;
                    ComboBox<String> fuseValues_comboBox;

                    fuseName_label = (Label)root.lookup("#fuseName_label");
                    fuseDescription_label = (Label)root.lookup("#fuseDescription_label");
                    fuseValues_comboBox = (ComboBox<String>)root.lookup("#fuseValues_comboBox");

                    fuseName_label.setText(item.getName());
                    fuseDescription_label.setText(item.getDescription());
                    fuseValues_comboBox.getItems().addAll(item.getValues());
                    fuseValues_comboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
                        if (oldValue != null && !oldValue.equals(newValue)) {
                            comboBoxesSelectedValues.put(item.getName(), fuseValues_comboBox.getSelectionModel().getSelectedIndex());

                            updateOutputText();
                        }
                    });


                    fuseValues_comboBox.getSelectionModel().select(0);

                }
                catch (IOException ioe) {
                    ioe.printStackTrace();
                }

                setGraphic(root);
            }

        }
    }
}
