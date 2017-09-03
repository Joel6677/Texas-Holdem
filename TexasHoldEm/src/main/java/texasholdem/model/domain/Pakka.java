package texasholdem.model.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import texasholdem.model.domain.Kortti;
import texasholdem.model.domain.Kortti.Maa;
import texasholdem.model.domain.Kortti.Sijoitus;

/**
 * Luokasssa enumit Maa ja Sijoitus sekä useita luokkia kortille.
 */
public class Pakka {

    public final List<Kortti> pakka = new ArrayList<Kortti>();
    private Kortti[] kortit;
    private int nextCardIndex = 0;

    /**
     * Konstruktori.
     */
    public Pakka() {

        this.luoPakka();

    }

    private void luoPakka() {
        for (Maa maa : Maa.values()) {
            for (Sijoitus sijoitus : Sijoitus.values()) {
                pakka.add(new Kortti(sijoitus, maa));
            }
        }
    }

    /**
     * Get Pakka.
     *
     * @return pakka
     */
    public ArrayList<Kortti> getPakka() {
        return new ArrayList<Kortti>(pakka);
    }

    /**
     * Sekoita pakka.
     */
    public void sekoitaPakka() {
        Collections.shuffle(pakka);
    }

    /**
     * metodi jakajaa kortit.
     *
     * @return kortit
     */
    public Kortti deal() {
        if (nextCardIndex + 1 >= 52) {
            throw new IllegalStateException("Ei kortteja pakassa jäljellä");
        }
        return pakka.get(nextCardIndex++);
    }

    /**
     * metodi jakaa kortit.
     *
     * @param noOfCards korttien määrä
     * @return palauttaa jaetut kortit
     */
    public List<Kortti> deal(int noOfCards) {
        if (noOfCards < 1) {
            throw new IllegalArgumentException("noOfCards < 1");
        }
        if (nextCardIndex + noOfCards >= 52) {
            throw new IllegalStateException("Ei kortteja pakassa jäljellä");
        }
        List<Kortti> dealtCards = new ArrayList<Kortti>();
        for (int i = 0; i < noOfCards; i++) {
            dealtCards.add(pakka.get(nextCardIndex++));
        }
        return dealtCards;
    }

}
