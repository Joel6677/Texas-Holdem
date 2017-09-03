package texasholdem.model.domain;

/**
 * Luokasssa enumit Maa ja Sijoitus sekä useita luokkia kortille.
 */
public class Kortti {

    public enum Maa {
        PATA(3), HERTTA(2), RISTI(1), RUUTU(0);

        private final int arvo;

        private Maa(int arvo) {
            this.arvo = arvo;
        }

        public int getArvo() {
            return arvo;
        }
    }

    public enum Sijoitus {
        KAKSI(0), KOLME(1), NELJÄ(2), VIISI(3),
        KUUSI(4), SEITSEMÄN(5), KAHDEKSAN(6), YHDEKSÄN(7),
        KYMPPI(8), JÄTKÄ(9), AKKA(10), KURKO(11), ÄSSÄ(12);

        private final int arvo;

        private Sijoitus(int arvo) {
            this.arvo = arvo;
        }

        public int getArvo() {
            return arvo;
        }
    }

    private Sijoitus sijoitus;
    private Maa maa;

    /**
     * Konstruktori.
     *
     * @param sijoitus sijoitus
     * @param maa maa
     */
    public Kortti(Sijoitus sijoitus, Maa maa) {
        this.sijoitus = sijoitus;
        this.maa = maa;
    }

    /**
     * Get sijoitus.
     *
     * @return sijotus
     */
    public Sijoitus getSijoitus() {
        return sijoitus;
    }

    /**
     * Get maa.
     *
     * @return maa
     */
    public Maa getMaa() {
        return maa;
    }

    public void setSijoitus(Sijoitus sijoitus) {
        this.sijoitus = sijoitus;
    }

    /**
     * Set maa.
     *
     * @param maa maa
     */
    public void setMaa(Maa maa) {
        this.maa = maa;
    }

    /**
     * Compare to.
     *
     * @return -1,1 tai 0
     * @param kortti kortti
     */
    public int compareTo(Kortti kortti) {
        int thisValue = hashCode();
        int otherValue = kortti.hashCode();
        if (thisValue < otherValue) {
            return -1;
        } else if (thisValue > otherValue) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Kortti tekstiksi.
     *
     * @return Kortti tekstiksi
     */
    @Override
    public String toString() {
        return "Kortti{"
                + "sijoitus=" + sijoitus
                + ", maa=" + maa
                + '}';
    }

}
