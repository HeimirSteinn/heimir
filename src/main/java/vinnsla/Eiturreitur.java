package vinnsla;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;

public class Eiturreitur {
    private final SimpleIntegerProperty eiturCounter = new SimpleIntegerProperty(1);
    private final List<Integer> reitir;

    public Eiturreitur(){
        reitir = new ArrayList<>();
        reitir.add(5);
        reitir.add(15);
        reitir.add(25);
        reitir.add(35);
        reitir.add(45);
        reitir.add(55);
        reitir.add(65);
        reitir.add(75);
    }
    public boolean isEiturReitur(int a){
        return reitir.contains(a);
    }
    public SimpleIntegerProperty eiturCounterProperty (){
        return eiturCounter;
    }
    public void addEiturCounter(){
        int count = eiturCounter.get() + 1;
        eiturCounter.set(count);
    }
}