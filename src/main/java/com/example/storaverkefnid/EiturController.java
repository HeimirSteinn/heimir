package com.example.storaverkefnid;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

import java.io.IOException;

public class EiturController  extends Dialog<String> {
    /**
     * constructor fyrir EiturController
     */
    public EiturController(){
        setDialogPane(eiturDialog());
        setResultConverter(b -> {
            if (b.getButtonData() == ButtonBar.ButtonData.OK_DONE){
                return "null";
            }
            return null;
        });
    }

    /**
     * Skilar viðeigandi DialogPane
     * @return DialogPane úr Eitur-view.fxml
     */
    public DialogPane eiturDialog() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Eitur-view.fxml"));
        try {
            fxmlLoader.setController(this);
            return fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
