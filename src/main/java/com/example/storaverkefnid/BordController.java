package com.example.storaverkefnid;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import vinnsla.Leikur;
import vinnsla.Spilastokkur;

import java.util.ArrayList;
import java.util.List;

public class BordController {
    @FXML
    private Button fxTeningur;
    @FXML
    private Label fxSkilabodTop;
    @FXML
    private Label fxSkilabodBot;
    @FXML
    private GridPane fxBord;
    private List<Node> reitir = new ArrayList<>();
    private final Leikur leikur = new Leikur(80);
    private static final String[] myndir = {"one", "two", "three", "four", "five", "six"};
    private Spilastokkur stokkur;
    private List<Integer> eiturReitir;
    private List<Integer> spilaReitir;


    /**
     * Frumstillir Viðmót við vinnsluklasa
     */
    public void initialize() {
        eiturReitir = leikur.getEiturReitir();
        spilaReitir = leikur.getSpilaReitir();
        stokkur = new Spilastokkur();
        reitir = fxBord.getChildren();
        fxTeningur.disableProperty().bind(leikur.getGameOver());
        leikur.talaProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if(newValue.intValue() != 0) {
                        Timeline timeline = new Timeline();
                        for (int i = 1; i <= 6; i++) {
                            int j = i;
                            KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.2 * j), e -> {
                                fxTeningur.getStyleClass().removeIf(s -> s.startsWith("mh"));
                                fxTeningur.getStyleClass().add("mh" + j);
                            });
                            timeline.getKeyFrames().add(keyFrame);
                        }
                        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1.3), e -> {
                            fxTeningur.getStyleClass().removeIf(s -> s.startsWith("mh"));
                            fxTeningur.getStyleClass().add(myndir[newValue.intValue() - 1]);

                        }));
                        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1.35), e -> {
                            leikur.leikaleik();
                        }));
                        timeline.play();
                    }
                    else {
                        fxTeningur.getStyleClass().remove(myndir[oldValue.intValue() - 1]);
                    }
                }
        );
        leikur.getGameOver().addListener(
                (observable, oldValue, newValue) ->{
                    if (newValue){
                        nyrLeikur();
                    }
                }
        );
        fxSkilabodBot.textProperty().bind(Bindings.when(leikur.getGameOver()).then("ekkert").otherwise(leikur.naesturProperty()));
        fxSkilabodTop.textProperty().bind(leikur.slonguSkilabodProperty());
        leikur.nyrLeikur();
        leikur.p1Stada().addListener(
                (observable, oldValue, newValue) -> {
                    int gamliReitur = (int) oldValue - 1;
                    int nyjiReitur = (int) newValue - 1;
                    p1move(gamliReitur, nyjiReitur);
                }
        );
        leikur.p2Stada().addListener(
                (observable, oldValue, newValue) -> {
                    int gamliReitur = (int) oldValue - 1;
                    int nyjiReitur = (int) newValue - 1;
                    p2move(gamliReitur, nyjiReitur);
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
        leikur.leikaleik();
        leikur.nyrLeikur();
    }

    /**
     * handler fyrir fxTeningur viðmótshlut
     *
     */
    @FXML
    public void teningurHandler() {
        leikur.kastaTening();
    }

    /**
     * handler fyrir fxNyrLeikur viðmótshlut
     *
     */
    @FXML
    public void nyrLeikur() {
        Platform.runLater(() -> {
            System.out.println(leikur.sigurvegariProperty().get());
            LokidController lokidController = new LokidController(leikur.sigurvegariProperty().get());
            boolean b = lokidController.getBoolean();
            if (b) {
                leikur.nyrLeikur();
                stokkur = new Spilastokkur();
            } else {
                System.exit(0);
            }
        });
    }

    /**
     * keyrir spilareitur viðmótið
     */
    public void spilaReitur() {

        Platform.runLater(() -> {
            String s;
            if (leikur.naesturProperty().get().equals(leikur.getP1Name())){
                s = leikur.getP2Name();
            }
            else {
                s = leikur.getP1Name();
            }
            SpilController spil = new SpilController(s, stokkur.getSpil());
            spil.showAndWait();
        });
    }
/**
 * þegar lent er á eiturreit
 */
    public void eiturReitur() {
        Platform.runLater(() -> {
            EiturController eiturController = new EiturController();
            eiturController.showAndWait();
        });

    }

    /**
     *
     * @param p1Nafn nafnið sem sett er fyrir player 1
     * @param p2Nafn nafnið sem sett er fyrir player 2
     */
    public void setNames(String p1Nafn, String p2Nafn) {
        leikur.setNames(p1Nafn, p2Nafn);
    }

    /**
     * færir player 1 í viðmótinu
     * @param gamli gamli reiturinn sem p1 var á
     * @param nyji nýji reyturinn sem p1 mun færast á
     */
    public void p1move(int gamli, int nyji) {
        reitir.get(gamli).getStyleClass().remove("blue");
        reitir.get(gamli).getStyleClass().remove("bluegreeneitur");
        reitir.get(gamli).getStyleClass().remove("bluegreenspil");
        reitir.get(gamli).getStyleClass().remove("bluegreen");
        reitir.get(gamli).getStyleClass().remove("blueeitur");
        reitir.get(gamli).getStyleClass().remove("bluespil");
        reitir.get(gamli).getStyleClass().remove("blue");

        if (gamli == leikur.p2Stada().get() - 1) {
            if (eiturReitir.contains(gamli + 1)) {
                reitir.get(gamli).getStyleClass().add("greeneitur");
            } else if (spilaReitir.contains(gamli + 1)) {
                reitir.get(gamli).getStyleClass().add("greenspil");
            } else {
                reitir.get(gamli).getStyleClass().add("green");
            }

        }
        if (nyji == leikur.p2Stada().get() - 1) {
            if (eiturReitir.contains(nyji + 1)) {
                reitir.get(nyji).getStyleClass().add("bluegreeneitur");
            } else if (spilaReitir.contains(nyji + 1)) {
                reitir.get(nyji).getStyleClass().add("bluegreenspil");
            } else {
                reitir.get(nyji).getStyleClass().add("bluegreen");
            }
        } else {
            if (eiturReitir.contains(nyji + 1)) {
                reitir.get(nyji).getStyleClass().add("blueeitur");
            } else if (spilaReitir.contains(nyji + 1)) {
                reitir.get(nyji).getStyleClass().add("bluespil");
            } else {
                reitir.get(nyji).getStyleClass().add("blue");
            }
        }
    }

    /**
     * færir player 2 í viðmótinu
     * @param gamli gamli reiturinn sem p2 var á
     * @param nyji nýji reiturinn sem p2 mun færast á
     */
    public void p2move(int gamli, int nyji) {
        reitir.get(gamli).getStyleClass().remove("bluegreeneitur");
        reitir.get(gamli).getStyleClass().remove("bluegreenspil");
        reitir.get(gamli).getStyleClass().remove("bluegreen");
        reitir.get(gamli).getStyleClass().remove("greeneitur");
        reitir.get(gamli).getStyleClass().remove("greenspil");
        reitir.get(gamli).getStyleClass().remove("green");

        if (gamli == leikur.p1Stada().get() - 1) {
            if (eiturReitir.contains(gamli + 1)) {
                reitir.get(gamli).getStyleClass().add("blueeitur");
            } else if (spilaReitir.contains(gamli + 1)) {
                reitir.get(gamli).getStyleClass().add("bluespil");
            } else {
                reitir.get(gamli).getStyleClass().add("blue");
            }
        }
        if (nyji == leikur.p1Stada().get() - 1) {
            if (eiturReitir.contains(nyji + 1)) {
                reitir.get(nyji).getStyleClass().add("bluegreeneitur");
            } else if (spilaReitir.contains(nyji + 1)) {
                reitir.get(nyji).getStyleClass().add("bluegreenspil");
            } else {
                reitir.get(nyji).getStyleClass().add("bluegreen");
            }
        } else {
            if (eiturReitir.contains(nyji + 1)) {
                reitir.get(nyji).getStyleClass().add("greeneitur");
            } else if (spilaReitir.contains(nyji + 1)) {
                reitir.get(nyji).getStyleClass().add("greenspil");
            } else {
                reitir.get(nyji).getStyleClass().add("green");
            }
        }
    }
}