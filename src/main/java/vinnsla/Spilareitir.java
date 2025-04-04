package vinnsla;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;

public class Spilareitir {
    private final SimpleIntegerProperty spilaCounter = new SimpleIntegerProperty(1);
    private final List<Integer> reitir;

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
    public boolean isSpilareitur(int a){
        return reitir.contains(a);
    }
    public SimpleIntegerProperty spilaCounterProperty(){
        return spilaCounter;
    }
    public void addSpilaCounter(){
        int count = spilaCounter.get() + 1;
        spilaCounter.set(count);
    }
}
