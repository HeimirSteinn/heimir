package vinnsla;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.HashMap;

/**
 * vinnlus klasi fyrir leikur vinnsluklasann
 * notar hashmap til að kortleggja hvar slöngur eða
 * stigar eru á spilaborðinu
 */
public class SlongurStigar {
    private final HashMap<Integer, Integer> slongurStigar;
    private final SimpleStringProperty slonguSkilabod = new SimpleStringProperty();
    private final Eiturreitur eiturreitur;
    private final Spilareitir spilareitir;

    /**
     * no args constructor fyrir SlongurStigar hlut
     */
    public SlongurStigar() {
        slongurStigar = new HashMap<>();
        slongurStigar.put(3, 9);
        slongurStigar.put(8, 16);
        slongurStigar.put(10, 2);
        slongurStigar.put(11, 16);
        slongurStigar.put(14, 4);
        slongurStigar.put(18, 20);
        slongurStigar.put(19, 17);
        slongurStigar.put(23, 15);
        eiturreitur = new Eiturreitur();
        spilareitir = new Spilareitir();
    }

    /**
     * void aðferð sem sem setur slonguskilaboðbreytuna eftir því
     * hvort reitur sé með slöngu eða stiga
     * setur leik lokið ef reiturinn er nákvæmlega 24
     * @param reitur reiturinn sem leikmaður lendir á
     */
    public void setSlonguSkilabod(int reitur) {
        if (reitur == 80) {
            setSlonguSkilabod("Leik Lokið!");
        }
        if (isSlangaOrStigi(reitur)){
            int value = slongurStigar.get(reitur);
            if (reitur < value) {
                setSlonguSkilabod("Stigi! Frá " + reitur + " að reit " + value + "!");
            } else {
                setSlonguSkilabod("Slanga! Frá " + reitur + " á reit " + value + "!");
            }
        }
        if (eiturreitur.isEiturReitur(reitur)){
            setSlonguSkilabod("EITUR REITUR!");
            eiturreitur.addEiturCounter();
            System.out.println(eiturreitur.eiturCounterProperty().get() + " eitur");
        }
        if (spilareitir.isSpilareitur(reitur)){
            setSlonguSkilabod("Spila REITUR!");
            spilareitir.addSpilaCounter();
            System.out.println(spilareitir.spilaCounterProperty().get() + "spila");
        }
    }

    /**
     * tekur inn reitinn sem slanga eða stigi er á og skilar nýjum reit
     * @param reitur reiturinn sem leikmaður lenti á eftir kast
     * @return reiturinn sem leikmaður lendir á eftir stiga eða slöngu
     */
    public int lending(int reitur) {
        return slongurStigar.get(reitur);
    }

    /**
     * aðferð sem athugar hvort að ákveðinn reitur sé slanga eða stigi.
     * ef reiturinn er ekki stigi eða slanga kallar aðferðin á aðra aðferð
     * sem hreinsar slonguskilaboð
     * @param reitur reiturinn sem á að athuga
     * @return true ef reiturinn er slanga eða stigi
     */
    public boolean isSlangaStigi(int reitur) {
        boolean isSlonguStigi = false;
        if (slongurStigar.containsKey(reitur)){
            isSlonguStigi = true;
        } else if (eiturreitur.isEiturReitur(reitur)) {
            isSlonguStigi = true;
        } else if (spilareitir.isSpilareitur(reitur)) {
            isSlonguStigi = true;
        } else {
            setSlonguSkilabod("");
        }
        return isSlonguStigi;
    }
    public boolean isSlangaOrStigi(int reitur){
        return slongurStigar.containsKey(reitur);
    }

    /**
     * getter aðferð fyrir SimpleStringProperty slonguSkilabod
     * @return slonguSkilabod
     */
    public SimpleStringProperty slonguSkilabodProperty() {
        return slonguSkilabod;
    }

    /**
     * setter fyrir slonguSkilabod
     * @param s Strengur sem er settur í breytuna slonguSkilabod
     */
    public void setSlonguSkilabod(String s) {
        slonguSkilabod.set(s);
    }
    public SimpleIntegerProperty eiturCounterProperty() {
        return eiturreitur.eiturCounterProperty();
    }
    public SimpleIntegerProperty spilaCounterProperty(){
        return spilareitir.spilaCounterProperty();
    }
}
