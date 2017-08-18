package texasholdem.model.domain;

/**
 * Luokassa jakajan erinäisiä metodeita
 */
public class Jakaja {

    private Kortti[] jakajanKortit = new Kortti[2];

    public Jakaja() {

    }

    public Jakaja(Kortti kortti1, Kortti kortti2) {
        jakajanKortit[0] = kortti1;
        jakajanKortit[1] = kortti2;
    }

    public void setKortti(Kortti kortti, int kortinNumero) {
        jakajanKortit[kortinNumero] = kortti;
    }

    public Kortti getKortti(int kortinNumero) {
        return jakajanKortit[kortinNumero];
    }

    public Kortti[] getKasi() {
        return jakajanKortit;
    }

    public int korttienMaara() {
        return jakajanKortit.length;
    }

    public String toString() {
        return jakajanKortit[0] + " ja " + jakajanKortit[1];
    }

}
