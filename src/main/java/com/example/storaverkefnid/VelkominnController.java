package com.example.storaverkefnid;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class VelkominnController {
    @FXML
    private Button fxHefjaLeik;
    @FXML
    private TextField fxBlueName;
    @FXML
    private TextField fxGreenName;

    public void onHefjaLeik(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Bord-view.fxml"));
        Parent root = loader.load();
        BordController controller = loader.getController();
        controller.setNames(fxBlueName.getText(), fxGreenName.getText());
        Stage stage = (Stage) fxHefjaLeik.getScene().getWindow();
        stage.setScene(new Scene(root, 1000, 1000));

    }
}
