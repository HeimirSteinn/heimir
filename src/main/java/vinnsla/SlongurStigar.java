package vinnsla;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.HashMap;
import java.util.List;

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
        slongurStigar.put(7, 14);
        slongurStigar.put(15, 6);
        slongurStigar.put(22, 17);
        slongurStigar.put(26, 32);
        slongurStigar.put(35, 28);
        slongurStigar.put(39, 59);
        slongurStigar.put(43, 58);
        slongurStigar.put(49, 31);
        slongurStigar.put(55, 46);
        slongurStigar.put(57, 64);
        slongurStigar.put(68, 73);
        slongurStigar.put(70, 52);
        slongurStigar.put(75, 66);

        eiturreitur = new Eiturreitur();
        spilareitir = new Spilareitir();
    }

    /**
     * void aðferð sem sem setur slonguskilaboðbreytuna eftir því
     * hvort reitur sé með slöngu eða stiga
     * setur leik lokið ef reiturinn er nákvæmlega 24
     * @param reitur reiturinn sem leikmaður lendir á
     */
    public int setSlonguSkilabod(int reitur) {
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
            System.out.println(eiturreitur.eiturCounterProperty().get() + " eitur");
            return 1;
        }
        if (spilareitir.isSpilareitur(reitur)){
            setSlonguSkilabod("Spila REITUR!");
            System.out.println(spilareitir.spilaCounterProperty().get() + "spila");
            return -1;
        }
        return 0;
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

    /**
     * lætur vita ef breyting verður á eiturcounter
     * @return SimpleIntegerProperty
     */
    public SimpleIntegerProperty eiturCounterProperty() {
        return eiturreitur.eiturCounterProperty();
    }

    /**
     * lætur vita ef breyting verður á spilacounter
     * @return SimpleIntegerProperty
     */
    public SimpleIntegerProperty spilaCounterProperty(){
        return spilareitir.spilaCounterProperty();
    }

    /**
     * kallar á aðferð sem bætir við spilacounter
     */
    public void addToSpilCounter(){
        spilareitir.addSpilaCounter();
    }

    /**
     * kallar á aðferð sem bætir við eiturcounter
     */
    public void addToEiturCounter(){
        eiturreitur.addEiturCounter();
    }

    /**
     * skilar lista af eiturreitum
     * @return Lista af eiturreitum
     */
    public List<Integer> getEiturReitir(){
        return eiturreitur.getEiturReitir();
    }

    /**
     * skilar lista af spilareitum
     * @return Lista af spilareitum
     */
    public List<Integer> getSpilaReitir(){
        return spilareitir.getSpilareitir();
    }
}
