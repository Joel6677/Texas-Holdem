package texasholdem.model;

import org.junit.Test;
import static org.junit.Assert.*;
import texasholdem.model.Kortti;
import texasholdem.model.Kortti.Sijoitus;
import texasholdem.model.Kortti.Maa;

public class PelaajaTest {

    @Test
    public void konstruktoriTesti() {
        Kortti kortti1 = new Kortti(Sijoitus.AKKA, Maa.HERTTA);
        Kortti kortti2 = new Kortti(Sijoitus.ÄSSÄ, Maa.PATA);
        Pelaaja pelaaja = new Pelaaja(kortti1, kortti2);
        assertEquals("Kortti{sijoitus=AKKA, maa=HERTTA}" + " ja " + "Kortti{sijoitus=ÄSSÄ, maa=PATA}", pelaaja.toString());

    }

    @Test
    public void setKortti() {
        Kortti kortti1 = new Kortti(Sijoitus.AKKA, Maa.HERTTA);
        Kortti kortti2 = new Kortti(Sijoitus.KURKO, Maa.PATA);
        Pelaaja pelaaja = new Pelaaja();
        pelaaja.setKortti(kortti1, 0);
        assertEquals(kortti1, pelaaja.getKortti(0));

    }

    @Test
    public void getKortti() {
        Kortti kortti1 = new Kortti(Sijoitus.NELJÄ, Maa.HERTTA);
        Kortti kortti2 = new Kortti(Sijoitus.ÄSSÄ, Maa.PATA);
        Pelaaja pelaaja = new Pelaaja(kortti1, kortti2);
        assertEquals(kortti1, pelaaja.getKortti(0));
    }

    @Test
    public void korttienMaara() {
        Kortti kortti1 = new Kortti(Sijoitus.NELJÄ, Maa.HERTTA);
        Kortti kortti2 = new Kortti(Sijoitus.ÄSSÄ, Maa.PATA);
        Pelaaja pelaaja = new Pelaaja(kortti1, kortti2);
        assertEquals(2, pelaaja.korttienMaara());
    }
}
