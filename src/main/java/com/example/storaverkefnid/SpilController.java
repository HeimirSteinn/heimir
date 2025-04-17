package com.example.storaverkefnid;

import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
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
    private String spil;
    private String nafn;

    /**
     * frumstillir viðmótið
     */
    public void initialize(){
        fxHaldaAfram.disableProperty().set(true);
        fxComand.setText(nafn + " á að giska á spilið");
    }

    /**
     * constructor fyrir SpilControler
     * Opnar dialog glugga
     * @param n nafn liðs sem á að "leika" spilareitinn
     * @param spil tiltekið spil sem lið á að giska á
     */
    public SpilController(String n, String spil) {
        nafn = n;
        this.spil = spil;
        setDialogPane(spilDialog());
    }

    /**
     * aðfeð sem opnar DialogPane
     */
    @FXML
    public DialogPane spilDialog() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Spil-view.fxml"));
        try {
            fxmlLoader.setController(this);
            return fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * handler fyrir hvaða takka var ýtt á
     * @param e takkinn sem ýtt var á
     */
    @FXML
    public void onButtonClick(ActionEvent e){
        Button b = (Button) e.getSource();
        String s = b.getText();
        fxSpil.getStyleClass().remove("spilabak");
        fxSpil.getStyleClass().add(spil);
        checkSpil(s, spil);
        fxHaldaAfram.disableProperty().set(false);
    }

    /**
     * breytir text property á Lebelum eftir því hvort giskað var á rétt spil eða ekki
     * @param s strengur sem á að athuga hvort sé eins og spilið
     * @param spilid spilið sem verið er að giska á
     */
    private void checkSpil(String s, String spilid){
        if (Character.toLowerCase(spilid.charAt(0)) == Character.toLowerCase(s.charAt(0))){
            fxSvar.setText("Vel gert!");
            fxComand.setWrapText(true);
            fxComand.setText("Þú mátt gefa Öllum í hinu liðinu 4 sopa");
        }
        else {
            fxSvar.setText("Ekki Rétt!");
            fxComand.setText("Allir í þínu liði taka 2 sopa");
        }
    }
}
