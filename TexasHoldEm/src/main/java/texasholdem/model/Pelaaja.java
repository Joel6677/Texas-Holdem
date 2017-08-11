package texasholdem.model;

import texasholdem.model.Kortti;

public class Pelaaja {

    private Kortti[] kortit = new Kortti[2];

    public Pelaaja() {

    }

    public Pelaaja(Kortti kortti1, Kortti kortti2) {
        kortit[0] = kortti1;
        kortit[1] = kortti2;
    }

    public void setKortti(Kortti kortti, int kortinNumero) {
        kortit[kortinNumero] = kortti;
    }

    public Kortti getKortti(int kortinNumero) {
        return kortit[kortinNumero];
    }

    public int korttienMaara() {
        return kortit.length;
    }

    public String toString() {
        return kortit[0] + " ja " + kortit[1];
    }

}
