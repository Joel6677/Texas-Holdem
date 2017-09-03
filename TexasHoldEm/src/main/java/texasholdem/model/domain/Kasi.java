package texasholdem.model.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import texasholdem.model.evaluointi.KasiEvaluointi5Huonointa;
import texasholdem.model.domain.Kortti;
import texasholdem.model.evaluointi.KasiEvaluointi5Parasta;

/**
 * Luokasssa enumeina kaden sijoitukset ja arvot. Luokassa erinäisiä käteen
 * liittyviä metodeita.
 */
public class Kasi {

    private Kortti[] pelaajanKasi = new Kortti[2];
    private KasiSijoitus kasiSijoitus;
    public List<KasiSijoitus> lista = new ArrayList<KasiSijoitus>();
    private KasiEvaluointi5Parasta hai = new KasiEvaluointi5Parasta();
    private static final int MAX_NO_OF_CARDS = 7;
    private Kortti[] kaikkiKortit = new Kortti[MAX_NO_OF_CARDS];
    private int noOfCards = 0;
    private int noOfPlayerCards = 0;

    public enum KasiSijoitus {

        Kuningasvarisuora(10), Värisuora(9), Neloset(8), Täyskäsi(7), Väri(6),
        Suora(5), Kolmoset(4), Kaksi_paria(3), Pari(2), Hai(1);

        private final int arvo;

        private KasiSijoitus(int arvo) {

            this.arvo = arvo;
        }

        public int getArvo() {
            return arvo;
        }
    }

    /**
     * Konstruktori.
     */
    public Kasi() {

    }

    /**
     * Konstruktori.
     *
     * @param kasi käsi
     */
    public Kasi(Kortti[] kasi) {
        this.pelaajanKasi = kasi;
    }

    /**
     * Konstruktori.
     *
     * @param kortit kortit
     */
    public Kasi(Collection<Kortti> kortit) {
        if (kortit == null) {
            throw new IllegalArgumentException("Null array");
        }
        for (Kortti kortti : kortit) {
            lisaaKortti(kortti);
        }
    }

    /**
     * Return pelaajan käsi.
     *
     * @return pelaajan käsi
     */
    public Kortti[] getKasi() {

        if (this.pelaajanKasi == null) {
            throw new IllegalArgumentException("Null array");
        }
        return this.pelaajanKasi;

    }

    /**
     * Set pelaajan käsi.
     *
     * @param kasi käsi
     */
    public void setKasi(Kortti[] kasi) {
        if (kasi == null) {
            throw new IllegalArgumentException("Null array");
        }
        this.pelaajanKasi = kasi;
    }

    /**
     * Set käsisijoitus.
     *
     * @param kasiSijoitus käsisijoitus
     */
    public void setKasiSijoitus(KasiSijoitus kasiSijoitus) {
        this.kasiSijoitus = kasiSijoitus;
    }

    /**
     * Get kasisijoitus.
     *
     * @return kasisijoitus
     */
    public KasiSijoitus getKasiSijoitus() {

        if (kasiSijoitus == null) {
            throw new IllegalArgumentException("Null array");
        }

        return kasiSijoitus;
    }

    /**
     * Lisaa kadet.
     */
    public void lisaaKadet() {
        for (KasiSijoitus kasi : KasiSijoitus.values()) {
            lista.add(kasi);
        }
    }

    /**
     * Get kortit.
     *
     * @return kortti
     */
    public Kortti[] getKortit() {
        Kortti[] kortti = new Kortti[noOfCards];
        System.arraycopy(kaikkiKortit, 0, kortti, 0, noOfCards);
        return kortti;
    }

    /**
     * Poista kaikki kortit.
     */
    public void removeAllCards() {
        this.noOfCards = 0;
        this.noOfPlayerCards = 0;
    }

    /**
     * Korttien määrä.
     *
     * @return noOfCards
     */
    public int size() {
        return noOfCards;
    }

    /**
     * Lisää pelaajan kortti.
     *
     * @param kortti kortti
     *
     */
    public void lisaaPelaajanKortti(Kortti kortti) {
        if (kortti == null) {
            throw new IllegalArgumentException("Null kortti");
        }

        int insertIndex = -1;
        for (int i = 0; i < noOfPlayerCards; i++) {
            if (kortti.compareTo(pelaajanKasi[i]) > 0) {
                insertIndex = i;
                break;
            }
        }
        if (insertIndex == -1) {

            pelaajanKasi[noOfPlayerCards++] = kortti;
        } else {
            for (int i = noOfPlayerCards; i > insertIndex; i--) {
                pelaajanKasi[i] = kaikkiKortit[i - 1];
            }
            pelaajanKasi[insertIndex] = kortti;
            noOfPlayerCards++;
        }
    }

    /**
     * Lisää kortti.
     *
     * @param kortti Lisätään yksi kortti kaikkiin kortteihin.
     */
    public void lisaaKortti(Kortti kortti) {
        if (kortti == null) {
            throw new IllegalArgumentException("Null kortti");
        }

        int insertIndex = -1;
        for (int i = 0; i < noOfCards; i++) {
            if (kortti.compareTo(kaikkiKortit[i]) > 0) {
                insertIndex = i;
                break;
            }
        }
        if (insertIndex == -1) {
            // Could not insert anywhere, so append at the end.
            kaikkiKortit[noOfCards++] = kortti;
        } else {
            for (int i = noOfCards; i > insertIndex; i--) {
                kaikkiKortit[i] = kaikkiKortit[i - 1];
            }
            kaikkiKortit[insertIndex] = kortti;
            noOfCards++;
        }
    }

    /**
     * Lisää kortit.
     *
     * @param kortit Lisätään useita kortteja kaikkiin kortteihin maksimi
     * korttejen määrä 7.
     */
    public void lisaaKortit(Kortti[] kortit) {
        if (kortit == null) {
            throw new IllegalArgumentException("Null array");
        }
        if (kortit.length > 7) {
            throw new IllegalArgumentException("Liikaa kortteja");
        }
        for (Kortti kortti : kortit) {
            lisaaKortti(kortti);
        }
    }

    /**
     * Lisää kortit.
     *
     * @param kortit tässä Collection käytössä Lisätään useita kortteja kaikkiin
     * kortteihin maksimi korttien määrä 52.
     */
    public void addCards(Collection<Kortti> kortit) {
        if (kortit == null) {
            throw new IllegalArgumentException("Null collection");
        }
        if (kortit.size() > 52) {
            throw new IllegalArgumentException("Liikaa kortteja");
        }
        for (Kortti kortti : kortit) {
            lisaaKortti(kortti);
        }
    }

    /**
     * Get pelaajan kortti.
     *
     * @param arvo arvo
     * @return pelaajan käsi
     */
    public Kortti getPelaajanKortti(int arvo) {
        return this.pelaajanKasi[arvo];
    }

    /**
     * Pelaajan käsi tekstiksi.
     *
     * @return pelaajankäsi tekstinä
     */
    public String toString() {
        return pelaajanKasi[0] + " ja " + pelaajanKasi[1];
    }
    
    public Comparator<Kortti> sijoituksenMukaan = (Kortti left, Kortti right) -> {

        if (left.getSijoitus().getArvo() < right.getSijoitus().getArvo()) {
            return -1;
        } else {
            return 1;
        }
    };
    
     /**
     * Get hai kädestä.
     *
     * @return hai
     */

    public Kortti getHaiKadesta() {

        Arrays.sort(pelaajanKasi, sijoituksenMukaan);
        return pelaajanKasi[pelaajanKasi.length - 1];
    }
}
