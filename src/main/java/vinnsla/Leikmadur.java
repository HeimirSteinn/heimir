package vinnsla;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * klasi fyrir leikmann hlut
 */
public class Leikmadur {
    private final SimpleStringProperty nafn;
    private final SimpleIntegerProperty reitur = new SimpleIntegerProperty();


    public Leikmadur() {
        reitur.set(1);
        nafn = new SimpleStringProperty();
    }

    /**
     * færir leikmanninn frá núverandi reit í nýjan reit
     * og passar að nýji reiturinn er aldrei meira en maxReitir
     * @param nyrReitur int gildi á nýja reitnum sem leikmadur fer á
     * @param max max reitir sem leikmaður getur mögulega farið á
     */
    public void faera(int nyrReitur, int max){
        if (nyrReitur < max){
            reitur.set(nyrReitur);
        }
        else {
            reitur.set(max);
        }
    }

    /**
     * getter aðferð sem skilar int gildi á SimpleIntegerProperty breytunni reitur
     * @return int gildi á núverandi reit
     */
    public int getReitur(){
        return reitur.get();
    }

    /**
     * getter aðferð fyrir SimpleIntegerProperty breytuna reitur
     * @return SimpleIntegerProperty gildi á núverandi reit
     */
    public SimpleIntegerProperty reiturProperty(){
        return reitur;
    }

    /**
     * Getter aðferð fyrir Streng nafn
     * @return String af nafni leikmanns
     */
    public String getNafn(){
        return nafn.get();
    }
    public void setNafn(String nyttNafn){
        nafn.set(nyttNafn);
    }


}
