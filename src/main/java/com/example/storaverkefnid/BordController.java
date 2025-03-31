package com.example.storaverkefnid;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import vinnsla.Leikur;

import java.util.ArrayList;
import java.util.List;

public class BordController {
    @FXML
    private Button fxTeningur;
    @FXML
    private Button fxNyrLeikur;
    @FXML
    private Label fxSkilabodTop;
    @FXML
    private Label fxSkilabodBot;
    @FXML
    private GridPane fxBord;
    @FXML
    private List<Node> reitir = new ArrayList<>();
    private final Leikur leikur = new Leikur(80);
    private static final String[] myndir = {"one", "two", "three", "four", "five", "six"};


    /**
     * Frumstillir Viðmót við vinnsluklasa
     *
     */
    public void initialize() {
        reitir = fxBord.getChildren();
        fxTeningur.disableProperty().bind(leikur.getGameOver());
        fxNyrLeikur.disableProperty().bind(fxTeningur.disabledProperty().not());
        leikur.talaProperty().addListener(
                (observable, oldValue, newValue) -> {
                    fxTeningur.getStyleClass().remove(myndir[oldValue.intValue() - 1]);
                    fxTeningur.getStyleClass().add(myndir[newValue.intValue() - 1]);
                }
        );
        fxSkilabodBot.textProperty().bind(Bindings.when(leikur.getGameOver()).then(leikur.sigurvegariProperty()).otherwise(leikur.naesturProperty()));
        fxSkilabodTop.textProperty().bind(leikur.slonguSkilabodProperty());
        leikur.nyrLeikur();
        leikur.p1Stada().addListener(
                (observable, oldValue, newValue) -> {

                    int gamliReitur = (int) oldValue - 1;
                    int nyjiReitur = (int) newValue - 1;
                    reitir.get(gamliReitur).getStyleClass().remove("blue");
                    if (gamliReitur == leikur.p2Stada().get() - 1) {
                        reitir.get(gamliReitur).getStyleClass().remove("bluegreen");
                        reitir.get(gamliReitur).getStyleClass().add("green");

                    }
                    if (nyjiReitur == leikur.p2Stada().get() - 1) {
                        reitir.get(nyjiReitur).getStyleClass().add("bluegreen");
                    } else {
                        reitir.get(nyjiReitur).getStyleClass().add("blue");

                    }
                }
        );
        leikur.eiturReiturProperty().addListener(
                (observable -> {
                    eiturReitur();
                    System.out.println("eiturreitur");
                })
        );
        leikur.spilaReitirProperty().addListener(
                (observable -> {
                    spilaReitur();
                    System.out.println("spilareitur");
                })
        );
        leikur.p2Stada().addListener(
                (observable, oldValue, newValue) -> {
                    int gamliReitur = (int) oldValue - 1;
                    int nyjiReitur = (int) newValue - 1;
                    reitir.get(gamliReitur).getStyleClass().remove("green");
                    reitir.get(gamliReitur).getStyleClass().remove("green");
                    if (gamliReitur == leikur.p1Stada().get() - 1) {
                        reitir.get(gamliReitur).getStyleClass().remove("bluegreen");
                        reitir.get(gamliReitur).getStyleClass().add("blue");
                    }
                    if (nyjiReitur == leikur.p1Stada().get() - 1) {
                        reitir.get(nyjiReitur).getStyleClass().add("bluegreen");

                    } else {
                        reitir.get(nyjiReitur).getStyleClass().add("green");
                    }


                }
        );
        leikur.leikaleik();
        leikur.nyrLeikur();
    }

    /**
     * handler fyrir fxTeningur viðmótshlut
     * @param event ónotað
     */
    @FXML
    public void teningurHandler(ActionEvent event) {
        leikur.leikaleik();
    }

    /**
     * handler fyrir fxNyrLeikur viðmótshlut
     * @param event ónotað
     */
    @FXML
    public void nyrLeikurHandler(ActionEvent event) {
        leikur.nyrLeikur();
    }
    public void spilaReitur(){
        SpilController s = new SpilController(leikur.naesturProperty().get());
        s.showAndWait();
    }
    public void eiturReitur(){
        EiturController eiturController = new EiturController();
        eiturController.showAndWait();
    }
    public void setNames(String p1Nafn, String p2Nafn){
        leikur.setNames(p1Nafn, p2Nafn);
    }
}