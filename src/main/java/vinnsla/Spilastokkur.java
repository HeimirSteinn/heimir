package vinnsla;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Spilastokkur {
    ArrayList<String> spil;

    /**
     * constructor fyrir spilastokk
     * stokkar spilin
     */
    public Spilastokkur(){
        spil = new ArrayList<>(List.of("h1", "h2", "h3", "h4", "h5", "h6", "h7", "h8", "h9", "h10", "h11", "h12", "h13" +
                "t1", "t2", "t3", "t4", "t5", "t6", "t7", "t8", "t9", "t10", "t11", "t12", "t13" +
                "s1", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9", "s10", "s11", "s12", "s13" +
                "l1", "l2", "l3", "l4", "l5", "l6", "l7", "l8", "l9", "l10", "l11", "l12", "l13"));
        Collections.shuffle(spil);
    }

    /**
     * skilar einu spili og fjarlægir það úr bunkanum
     * @return efsta spilið í bunkanum
     */
    public String getSpil(){
        return spil.remove(spil.size() - 1);
    }
}
