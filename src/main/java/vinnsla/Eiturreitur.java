package vinnsla;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;

public class Eiturreitur {
    private final SimpleIntegerProperty eiturCounter = new SimpleIntegerProperty(1);
    private final List<Integer> reitir;

    /**
     * constructor sem býr til eiturreitina í vinnslunni
     */
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

    /**
     * athugar hvort einhver ákveðinn reitur sé eitur reitur
     * @param a reitur sem á að athuga
     * @return true ef a er eiturreitur
     */
    public boolean isEiturReitur(int a){
        return reitir.contains(a);
    }

    /**
     * aðferð sem skilar eiturcounter sem er notaður til að uppfæra viðmótið
     * þjónar engum öðrum tilgangi
     * @return eiturCounter
     */
    public SimpleIntegerProperty eiturCounterProperty (){
        return eiturCounter;
    }

    /**
     * breytir eiturCounter sem er notður til þess að uppfæra viðmótið
     */
    public void addEiturCounter(){
        int count = eiturCounter.get() + 1;
        eiturCounter.set(count);
    }

    /**
     * notað þar sem aðrir klasar þurfa að vita hvaða reitir eru eiturreitir
     * @return Lista af eiturreitum
     */
    public List<Integer> getEiturReitir(){
        return reitir;
    }
}