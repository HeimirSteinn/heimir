package vinnsla;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;

public class Eiturreitur {
    private final SimpleIntegerProperty eiturCounter = new SimpleIntegerProperty(1);
    private final List<Integer> reitir;

    public Eiturreitur(){
        reitir = new ArrayList<>();
        reitir.add(2);
        reitir.add(9);
        reitir.add(18);
        reitir.add(29);
        reitir.add(40);
        reitir.add(47);
        reitir.add(51);
        reitir.add(60);
        reitir.add(65);
        reitir.add(74);
        reitir.add(77);
        reitir.add(79);
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