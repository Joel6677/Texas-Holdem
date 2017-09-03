/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texasholdem.model.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import texasholdem.model.domain.Kasi.KasiSijoitus;

/**
 *
 * @author Jolle
 */
public class HandValueTest {

    Kasi kasi;
    Kortti[] pelaajanKasi;
    Kortti[] flop;
    Kortti kortti;
    Kortti kortti1;
    Kortti kortti2;
    Kortti kortti3;
    Kortti kortti4;
    Kortti kortti5;
    Kortti kortti6;
    Kortti kortti7;

    @Before
    public void setUp() {
        kasi = new Kasi();
        pelaajanKasi = new Kortti[2];
        kortti = new Kortti(Kortti.Sijoitus.VIISI, Kortti.Maa.PATA);
        kortti2 = new Kortti(Kortti.Sijoitus.KUUSI, Kortti.Maa.PATA);

        pelaajanKasi[0] = kortti;
        pelaajanKasi[1] = kortti2;

        flop = new Kortti[5];
        kortti3 = new Kortti(Kortti.Sijoitus.SEITSEMÄN, Kortti.Maa.RUUTU);
        kortti4 = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.RISTI);
        kortti5 = new Kortti(Kortti.Sijoitus.JÄTKÄ, Kortti.Maa.RISTI);
        kortti6 = new Kortti(Kortti.Sijoitus.JÄTKÄ, Kortti.Maa.RISTI);
        kortti7 = new Kortti(Kortti.Sijoitus.JÄTKÄ, Kortti.Maa.PATA);

        flop[0] = kortti3;
        flop[1] = kortti4;
        flop[2] = kortti5;
        flop[3] = kortti6;
        flop[4] = kortti7;

    }
    
    
    @Test
    public void konstruktoriToimiiTyhja() {
        HandValue handValue = new HandValue();
        handValue.getParasKasi(pelaajanKasi, flop);
        assertEquals(String.format(handValue.toString(), handValue.getArvo()), handValue.toString());

    }

    @Test
    public void konstruktoriToimii() {
        HandValue handValue = new HandValue(pelaajanKasi, flop);

        assertEquals(4, handValue.getArvo());

    }

    @Test
    public void getParasKasiToimii() {
        HandValue handValue = new HandValue(pelaajanKasi, flop);

        assertEquals(4, handValue.getParasKasi(pelaajanKasi, flop).getArvo());
    }

    @Test
    public void getHaiArvoToimii() {

        HandValue handValue = new HandValue(pelaajanKasi, flop);

        assertEquals(10, handValue.getHaiArvo(pelaajanKasi, flop));
    }

    @Test
    public void getArvoToimii() {
    }

    @Test
    public void getKuvausToimii() {
        
        HandValue handValue = new HandValue(pelaajanKasi, flop);

        assertEquals("Kolmoset", handValue.getKuvaus());
        
    }

    @Test
    public void toStringToimii() {
    }
}
