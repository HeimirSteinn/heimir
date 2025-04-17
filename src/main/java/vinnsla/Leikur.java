package vinnsla;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.List;

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
    private String sigurvegariString;


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
        sigurvegariString = "vantar";
    }

    /**
     * kastar tening, athugar hver á að gera, athugar hvort nýji reiturinn sé slanga eða stigi
     * og færir leikmann
     */
    public void leikaleik() {
        int eiturOrSpil = 0;
        try {
            Thread.sleep(1400); // LAGA ÞETTA!!!!!!!!!!!!!!
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        int kast = teningur.getTala();
        if (p1.getNafn() != null && naestur.get().equals(p1.getNafn())) {
            int nyrReitur = p1.getReitur() + kast;
            if (slongurStigar.isSlangaStigi(nyrReitur)) {
                eiturOrSpil = slongurStigar.setSlonguSkilabod(nyrReitur);
                if (slongurStigar.isSlangaOrStigi(nyrReitur)){
                    nyrReitur = slongurStigar.lending(nyrReitur);
                }
            }
            p1.faera(nyrReitur, reitir);
            if (p1.getReitur() == reitir) {
                sigurvegari.set(p1.getNafn());
                gameOver.set(true);
                sigurvegariString = p1.getNafn();
                slongurStigar.setSlonguSkilabod(reitir);
            }
            if (eiturOrSpil > 0){
                slongurStigar.addToEiturCounter();
            }
            if (eiturOrSpil < 0){
                slongurStigar.addToSpilCounter();
            }
            naestur.set(p2.getNafn());
        }

        else {
            int nyrReitur = p2.getReitur() + kast;
            if (slongurStigar.isSlangaStigi(nyrReitur)) {
                eiturOrSpil = slongurStigar.setSlonguSkilabod(nyrReitur);
                if (slongurStigar.isSlangaOrStigi(nyrReitur)){
                    nyrReitur = slongurStigar.lending(nyrReitur);
                }
            }
            p2.faera(nyrReitur, reitir);
            if (p2.getReitur() == reitir) {
                sigurvegari.set(p2.getNafn());
                gameOver.set(true);
                sigurvegariString = p2.getNafn();
                slongurStigar.setSlonguSkilabod(reitir);
            }if (eiturOrSpil > 0){
                slongurStigar.addToEiturCounter();
                //þetta virkar ekki því ég þarf að vita hort eitur reitur eða spilareitur
            }
            if (eiturOrSpil < 0){
                slongurStigar.addToSpilCounter();
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
    public Leikmadur getPlayer(){
        if (naestur.isEqualTo(p1.getNafn()).get()){
            return p1;
        } else {
            return p2;
        }
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

    /**
     * notað til að láta viðmót vita að reitur sé eiturreitur
     * @return SimpleIntegerProperty
     */
    public SimpleIntegerProperty eiturReiturProperty(){
        return slongurStigar.eiturCounterProperty();
    }

    /**
     * notað til að láta viðmót vita að reitur sé spilareitur
     * @return SimpleIntegerProperty
     */
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

    /**
     * tekur inn tvo strengi og breytir nöfnum leikmanna í þá strengi
     * @param nafnOne nafn fyrir p1
     * @param nafnTwo nafn fyrir p2
     */
    public void setNames(String nafnOne, String nafnTwo){
        p1.setNafn(nafnOne);
        p2.setNafn(nafnTwo);
        naestur.set(p1.getNafn());
    }

    /**
     * hjálparaðferð sem kastar teningnum
     */
    public void kastaTening(){
        teningur.kasta();
    }

    /**
     * skilar lista af eiturreitum
     * @return listi yfir eiturreiti
     */
    public List<Integer> getEiturReitir(){
        return slongurStigar.getEiturReitir();
    }

    /**
     * skilar lista af spilareitum
     * @return listi yfir spilareiti
     */
    public List<Integer> getSpilaReitir(){
        return slongurStigar.getSpilaReitir();
    }

    /**
     * getter fyrir nafn p1
     * @return Streng af nafni p1
     */
    public String getP1Name(){
        return p1.getNafn();
    }

    /**
     * getter fyrir nafn p2
     * @return Streng af nafni p2
     */
    public String getP2Name(){
        return p2.getNafn();
    }

}
