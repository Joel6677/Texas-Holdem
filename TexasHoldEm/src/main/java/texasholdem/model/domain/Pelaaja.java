package texasholdem.model.domain;

import texasholdem.model.domain.Kasi.KasiSijoitus;
import texasholdem.model.domain.Kortti;

/**
 * Luokassa pelaajan metodeita
 */

public class Pelaaja {

    private Kortti[] pelaajanKortit = new Kortti[2];
    private int rahat = 0;

    public Pelaaja() {

    }

    public Pelaaja(Kortti kortti1, Kortti kortti2) {
        pelaajanKortit[0] = kortti1;
        pelaajanKortit[1] = kortti2;
    }

    public void setKortti(Kortti kortti, int kortinNumero) {
        pelaajanKortit[kortinNumero] = kortti;
    }
       
    public int getRahat() {
        return this.rahat;
    }
    
    public void setRahat(int rahat) {
        this.rahat += rahat;
    }
    
    public Kortti getKortti(int kortinNumero) {
        return pelaajanKortit[kortinNumero];
    }
    
    public Kortti[] getKasi() {
        return pelaajanKortit;
    }

    public int korttienMaara() {
        return pelaajanKortit.length;
    }

    public String toString() {
        return pelaajanKortit[0] + " ja " + pelaajanKortit[1];
    }

}