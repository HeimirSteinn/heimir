package vinnsla;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;

public class Spilareitir {
    private final SimpleIntegerProperty spilaCounter = new SimpleIntegerProperty(1);
    private final List<Integer> reitir;

    public Spilareitir(){
        reitir = new ArrayList<>();
        reitir.add(10);
        reitir.add(20);
        reitir.add(30);
        reitir.add(40);
        reitir.add(50);
        reitir.add(60);
        reitir.add(70);
        reitir.add(79);
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
