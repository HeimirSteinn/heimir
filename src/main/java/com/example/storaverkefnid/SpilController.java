package com.example.storaverkefnid;

import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import vinnsla.Spilastokkur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;

import javafx.scene.control.Dialog;

public class SpilController extends Dialog<String> {
    @FXML
    private Pane fxSpil;
    @FXML
    private Label fxSvar;
    @FXML
    private Label fxComand;
    @FXML
    private Button fxHjarta;
    @FXML
    private Button fxSpadi;
    @FXML
    private Button fxLauf;
    @FXML
    private Button fxTigull;
    @FXML
    private Button fxHaldaAfram;
    private Spilastokkur stokkur;
    private String nafn;

    public SpilController(String n) {
        nafn = n;
        stokkur = new Spilastokkur();
        setDialogPane(spilDialog());
    }
    @FXML
    public void initialize(){
        fxHaldaAfram.disableProperty().set(true);
        fxComand.setText(nafn + " á að giska á spilið");
    }

    public DialogPane spilDialog() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Spil-view.fxml"));
        try {
            fxmlLoader.setController(this);
            return fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void onHaldaAfram(ActionEvent ignore){
        getDialogPane().getScene().getWindow().hide();
    }
    @FXML
    public void onButtonClick(ActionEvent e){
        Button b = (Button) e.getSource();
        String s = b.getText();
        String stokkSort = stokkur.getSort();
        fxSpil.getStyleClass().remove("spilabak");
        fxSpil.getStyleClass().add(stokkSort);
        checkSpil(s, stokkSort);
        fxHaldaAfram.disableProperty().set(false);
    }
    private void checkSpil(String s, String stokkSort){
        if (Character.toLowerCase(stokkSort.charAt(0)) == Character.toLowerCase(s.charAt(0))){
            fxSvar.setText("Vel gert!");
            fxComand.setText("Þú mátt gefa Öllum í hinu liðinu 2 sopa");
        }
        else {
            fxSvar.setText("Ekki Rétt!");
            fxComand.setText("Allir í þínu liði taka 2 sopa");
        }
    }
}
