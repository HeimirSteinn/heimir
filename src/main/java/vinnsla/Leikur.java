package vinnsla;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * vinnsluklasi fyrir Slönguspil
 */
public class Leikur {
    private final Teningur teningur;
    private final Leikmadur p1;
    private final Leikmadur p2;
    private final SlongurStigar slongurStigar;
    private final SimpleBooleanProperty gameOver;
    private final SimpleStringProperty sigurvegari;
    private final SimpleStringProperty naestur;
    private final int reitir;


    /**
     * constructor fyrir Leikur klasann
     * @param reitir heildartala reitum
     */
    public Leikur(int reitir) {
        this.reitir = reitir;
        teningur = new Teningur();
        p1 = new Leikmadur();
        p2 = new Leikmadur();
        slongurStigar = new SlongurStigar();
        gameOver = new SimpleBooleanProperty(false);
        sigurvegari = new SimpleStringProperty();
        naestur = new SimpleStringProperty();
    }

    /**
     * kastar tening, athugar hver á að gera, athugar hvort nýji reiturinn sé slanga eða stigi
     * og færir leikmann
     */
    public void leikaleik() {
        teningur.kasta();
        int kast = teningur.getTala();
        if (p1.getNafn() != null && naestur.get().equals(p1.getNafn())) {
            int nyrReitur = p1.getReitur() + kast;
            if (slongurStigar.isSlangaStigi(nyrReitur)) {
                slongurStigar.setSlonguSkilabod(nyrReitur);
                if (slongurStigar.isSlangaOrStigi(nyrReitur)){
                    nyrReitur = slongurStigar.lending(nyrReitur);
                }
            }
            p1.faera(nyrReitur, reitir);
            if (p1.getReitur() == reitir) {
                gameOver.set(true);
                sigurvegari.set(p1.getNafn() + " VANN LEIKINN!");
                slongurStigar.setSlonguSkilabod(reitir);
            }
            naestur.set(p2.getNafn());
        }
        else {
            int nyrReitur = p2.getReitur() + kast;
            if (slongurStigar.isSlangaStigi(nyrReitur)) {
                slongurStigar.setSlonguSkilabod(nyrReitur);
                if (slongurStigar.isSlangaOrStigi(nyrReitur)){
                    nyrReitur = slongurStigar.lending(nyrReitur);
                }

            }
            p2.faera(nyrReitur, reitir);
            if (p2.getReitur() == reitir) {
                gameOver.set(true);
                sigurvegari.set(p2.getNafn() + " VANN LEIKINN!");
                slongurStigar.setSlonguSkilabod(reitir);
            }
            naestur.set(p1.getNafn());
        }
    }

    /**
     * getter fyrir SimpleStringProperty breytuna Sigurvegari
     * @return SimpleStringProperty breytan sigurvegari
     */
    public SimpleStringProperty sigurvegariProperty(){
        return sigurvegari;
    }

    /**
     * getter fyrir SimpleBooleanProperty breytuna gameOver
     * @return SimpleBooleanProperty breytan gameOver
     */
    public SimpleBooleanProperty getGameOver() {
        return gameOver;
    }

    /**
     * getter fyrir SimpleIntegerProperty fyrir tala breytuna í Teningur klasanum
     * @return SimpleIntegerProperty fyrir tala breytuna í Teningur klasanum
     */
    public SimpleIntegerProperty talaProperty() {
        return teningur.talaProperty();
    }

    /**
     * getter fyrir SimpleStringProperty breytuna slonguSkilabod í Slongurstigar klasanum
     * @return SimpleStringProperty breytan slonguSkilabod í Slongurstigar klasanum
     */
    public SimpleStringProperty slonguSkilabodProperty(){
        return slongurStigar.slonguSkilabodProperty();
    }

    /**
     * getter fyrir SimpleStringProperty breytan naestur
     * @return SimpleStringProperty breytan naestur
     */
    public SimpleStringProperty naesturProperty(){
        return naestur;
    }

    /**
     * getter fyrir SimpleIntegerProperty breytuna fyrir stöðuleikmanns 1
     * @return SimpleIntegerProperty breytan fyrir stöðuleikmanns 1
     */
    public SimpleIntegerProperty p1Stada(){
        return p1.reiturProperty();
    }

    /**
     * getter fyrir SimpleIntegerProperty breytuna fyrir stöðuleikmanns 1
     * @return SimpleIntegerProperty breytan fyrir stöðuleikmanns 1
     */
    public SimpleIntegerProperty p2Stada(){
        return p2.reiturProperty();
    }
    public SimpleIntegerProperty eiturReiturProperty(){
        return slongurStigar.eiturCounterProperty();
    }
    public SimpleIntegerProperty spilaReitirProperty(){
        return slongurStigar.spilaCounterProperty();
    }

    /**
     * endurstillir leikur hlutinn í upphafsstöðu
     */
    public void nyrLeikur() {
        gameOver.set(false);
        p1.faera(1, reitir);
        p2.faera(1, reitir);
        slongurStigar.setSlonguSkilabod("");
        naestur.set(p1.getNafn());

    }
    public void setNames(String nafnOne, String nafnTwo){
        p1.setNafn(nafnOne);
        p2.setNafn(nafnTwo);
        naestur.set(p1.getNafn());
    }

}
