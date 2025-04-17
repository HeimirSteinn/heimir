package vinnsla;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.Random;

/**
 * Teninga klasi sem hermir eftir tening.
 */
public class Teningur {
    private static final int MAX = 6;
    private final SimpleIntegerProperty talaProperty = new SimpleIntegerProperty(MAX);
    private final Random random = new Random();

    /**
     * no args constructor fyrir Teningur klasann
     */
    public Teningur() {
    }

    /**
     * "kastar teningnum", setur random tölu frá og með 1 til og með 6
     * setur það síðan í talaProperty breytuna
     */
    public void kasta(){
        int kast = random.nextInt(MAX) + 1;
        talaProperty.set(0);
        talaProperty.set(kast);
    }

    /**
     * get aðferð fyrir kastið, sú tala sem kemur upp á teninginn
     * @return talan sem kemur upp á teninginn
     */
    public int getTala (){
        return talaProperty.get();
    }

    /**
     * skilar kastinu sem Simple Integer property
     * @return kastið sem Simple Integer Property
     */
    public SimpleIntegerProperty talaProperty() {
        return talaProperty;
    }
}
