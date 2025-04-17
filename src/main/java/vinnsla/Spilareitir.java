package vinnsla;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;

public class Spilareitir {
    private final SimpleIntegerProperty spilaCounter = new SimpleIntegerProperty(1);
    private final List<Integer> reitir;

    /**
     * constroctor fyrir spilareiti í vinnslunni
     */
    public Spilareitir(){
        reitir = new ArrayList<>();
        reitir.add(5);
        reitir.add(13);
        reitir.add(24);
        reitir.add(33);
        reitir.add(37);
        reitir.add(45);
        reitir.add(53);
        reitir.add(63);
        reitir.add(67);
        reitir.add(72);
        reitir.add(76);
        reitir.add(78);
    }

    /**
     * athugar hvort a sé spilareitur
     * @param a reitur sem á að athuga
     * @return true ef a er spilareitur
     */
    public boolean isSpilareitur(int a){
        return reitir.contains(a);
    }

    /**
     * aðferð sem lætur vita þegar spilacounterproperty breytist
     * @return spilacounter
     */
    public SimpleIntegerProperty spilaCounterProperty(){
        return spilaCounter;
    }

    /**
     * aðferð sem breytir SpilaCounter, bætir einum við
     */
    public void addSpilaCounter(){
        int count = spilaCounter.get() + 1;
        spilaCounter.set(count);
    }

    /**
     * aðferð sem skilar leist af spilareitum
     * @return lista af spilareitum
     */
    public List<Integer> getSpilareitir(){
        return reitir;
    }
}
