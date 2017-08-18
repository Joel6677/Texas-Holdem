/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texasholdem.model.domain;

import texasholdem.model.domain.Kortti;
import texasholdem.model.domain.Jakaja;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Jolle
 */
public class JakajaTest {

    @Test
    public void konstruktoriTesti() {
        Kortti kortti1 = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.HERTTA);
        Kortti kortti2 = new Kortti(Kortti.Sijoitus.ÄSSÄ, Kortti.Maa.PATA);
        Jakaja jakaja = new Jakaja(kortti1, kortti2);
        assertEquals("Kortti{sijoitus=AKKA, maa=HERTTA}" + " ja " + "Kortti{sijoitus=ÄSSÄ, maa=PATA}", jakaja.toString());

    }

    @Test
    public void setKortti() {
        Kortti kortti1 = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.HERTTA);
        Kortti kortti2 = new Kortti(Kortti.Sijoitus.KURKO, Kortti.Maa.PATA);
        Jakaja jakaja = new Jakaja();
        jakaja.setKortti(kortti1, 0);
        assertEquals(kortti1, jakaja.getKortti(0));

    }

    @Test
    public void getKortti() {
        Kortti kortti1 = new Kortti(Kortti.Sijoitus.NELJÄ, Kortti.Maa.HERTTA);
        Kortti kortti2 = new Kortti(Kortti.Sijoitus.ÄSSÄ, Kortti.Maa.PATA);
        Jakaja jakaja = new Jakaja(kortti1, kortti2);
        assertEquals(kortti1, jakaja.getKortti(0));
    }

    @Test
    public void korttienMaara() {
        Kortti kortti1 = new Kortti(Kortti.Sijoitus.NELJÄ, Kortti.Maa.HERTTA);
        Kortti kortti2 = new Kortti(Kortti.Sijoitus.ÄSSÄ, Kortti.Maa.PATA);
        Jakaja jakaja = new Jakaja(kortti1, kortti2);
        assertEquals(2, jakaja.korttienMaara());
    }
}
