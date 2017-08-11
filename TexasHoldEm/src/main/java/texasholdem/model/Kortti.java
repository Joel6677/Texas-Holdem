package texasholdem.model;

public class Kortti {

    public static enum Maa {
        PATA,
        HERTTA,
        RISTI,
        RUUTU
    }

    public static enum Sijoitus {
        KAKSI(2), KOLME(3), NELJÄ(4), VIISI(5),
        KUUSI(6), SEITSEMÄN(7), KAHDEKSAN(8), YHDEKSÄN(9),
        KYMPPI(10), JÄTKÄ(11), AKKA(12), KURKO(13), ÄSSÄ(14);

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

    public Kortti(Sijoitus sijoitus, Maa maa) {
        this.sijoitus = sijoitus;
        this.maa = maa;
    }

    public Sijoitus getSijoitus() {
        return sijoitus;
    }

    public Maa getMaa() {
        return maa;
    }

    public void setSijoitus(Sijoitus sijoitus) {
        this.sijoitus = sijoitus;
    }

    public void setMaa(Maa maa) {
        this.maa = maa;
    }

    @Override
    public String toString() {
        return "Kortti{"
                + "sijoitus=" + sijoitus
                + ", maa=" + maa
                + '}';
    }

}
