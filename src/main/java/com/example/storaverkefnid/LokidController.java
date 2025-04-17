package com.example.storaverkefnid;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Optional;

public class LokidController  extends Dialog<Boolean> {
    @FXML
    private Label fxVannLeik;

    /**
     * constructor fyrir DialogPane sem kemur upp þegar leik er lokið
     * @param sigurvegari sigurvegari leiksins
     */
    public LokidController(String sigurvegari){
        setDialogPane(lokidDialog());
        fxVannLeik.textProperty().set(sigurvegari + " vann leikinn!");
        setResultConverter(b -> b.getButtonData() == ButtonBar.ButtonData.OK_DONE);
    }

    /**
     * nær í réttan DialogPane
     * @return DialogPane í Lokid-View.fxml
     */
    public DialogPane lokidDialog() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Lokid-view.fxml"));
        try {
            fxmlLoader.setController(this);
            return fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * optional boolean
     * @return útkoma. annars false. true ef ýtt er á Nýr leikur.
     */
    public boolean getBoolean(){
        Optional<Boolean> utkoma = showAndWait();
        return utkoma.orElse(false);
    }
}
