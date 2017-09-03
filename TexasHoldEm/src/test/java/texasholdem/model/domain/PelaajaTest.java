package texasholdem.model.domain;

import java.util.ArrayList;
import texasholdem.model.domain.Pelaaja;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import texasholdem.actions.Action;
import texasholdem.botti.Botti;
import texasholdem.model.domain.Kortti;
import texasholdem.model.domain.Kortti.Sijoitus;
import texasholdem.model.domain.Kortti.Maa;

public class PelaajaTest {

    @Test
    public void konstruktoriTesti() {
        Kortti kortti1 = new Kortti(Sijoitus.AKKA, Maa.HERTTA);
        Kortti kortti2 = new Kortti(Sijoitus.ÄSSÄ, Maa.PATA);
        Pelaaja pelaaja = new Pelaaja("Pena", 100, new Botti());
        pelaaja.setKortti(kortti1, 0);
        pelaaja.setKortti(kortti2, 1);
        pelaaja.resetHand();
        assertEquals("Kortti{sijoitus=AKKA, maa=HERTTA}" + " ja " + "Kortti{sijoitus=ÄSSÄ, maa=PATA}", pelaaja.toString());

    }

    @Test
    public void konstruktoriTesti2() {
        Kortti kortti1 = new Kortti(Sijoitus.AKKA, Maa.HERTTA);
        Kortti kortti2 = new Kortti(Sijoitus.ÄSSÄ, Maa.PATA);
        Pelaaja pelaaja = new Pelaaja(new Botti(), "Jaakko", 100, kortti1, kortti2);
//        pelaaja.setKortti(kortti1, 0);
//        pelaaja.setKortti(kortti2, 1);
        pelaaja.resetHand();
        assertEquals("Kortti{sijoitus=AKKA, maa=HERTTA}" + " ja " + "Kortti{sijoitus=ÄSSÄ, maa=PATA}", pelaaja.toString());

    }

    @Test
    public void getClient() {
        Pelaaja pelaaja = new Pelaaja("Pena", 100, new Botti());
        assertEquals(pelaaja.getClient(), pelaaja.getClient());
    }

    @Test
    public void setKortti() {
        Kortti kortti1 = new Kortti(Sijoitus.AKKA, Maa.HERTTA);
        Kortti kortti2 = new Kortti(Sijoitus.KURKO, Maa.PATA);
        Pelaaja pelaaja = new Pelaaja("Pena", 100, new Botti());
        pelaaja.setKortti(kortti1, 0);
        assertEquals(kortti1, pelaaja.getKortti(0));

    }

    @Test
    public void getKortti() {
        Kortti kortti1 = new Kortti(Sijoitus.NELJÄ, Maa.HERTTA);
        Kortti kortti2 = new Kortti(Sijoitus.ÄSSÄ, Maa.PATA);
        Pelaaja pelaaja = new Pelaaja("Pena", 100, new Botti());
        pelaaja.setKortti(kortti1, 0);
        assertEquals(kortti1, pelaaja.getKortti(0));
    }

    @Test
    public void maksaRahhoo() {
        Pelaaja pelaaja = new Pelaaja("Pena", 100, new Botti());
        pelaaja.maksaRahhoo(100);
        assertEquals(0, pelaaja.getRahat());
    }

    @Test
    public void getKasi() {
        Kortti kortti1 = new Kortti(Sijoitus.AKKA, Maa.HERTTA);
        Kortti kortti2 = new Kortti(Sijoitus.KURKO, Maa.PATA);
        Pelaaja pelaaja = new Pelaaja("Pena", 100, new Botti());

        Kortti[] pakka = new Kortti[2];
        pakka[0] = kortti1;
        pakka[1] = kortti2;

        pelaaja.setKortti(kortti1, 0);
        pelaaja.setKortti(kortti2, 1);
        Assert.assertArrayEquals(pakka, pelaaja.getKasi());
    }

    @Test
    public void resetHand() {
        Pelaaja pelaaja = new Pelaaja("Pena", 100, new Botti());
        Kasi kasi = new Kasi();
        kasi.removeAllCards();
        pelaaja.resetBet();
        pelaaja.resetHand();
    }

    @Test
    public void setCards() {
        Pelaaja pelaaja = new Pelaaja("Pena", 100, new Botti());
        ArrayList<Kortti> kortit = new ArrayList<Kortti>();
        Kortti kortti1 = new Kortti(Sijoitus.AKKA, Maa.HERTTA);
        Kortti kortti2 = new Kortti(Sijoitus.KURKO, Maa.PATA);
        kortit.add(kortti1);
        kortit.add(kortti2);
        pelaaja.setCards(kortit);
        assertEquals(pelaaja.getKortti(0), pelaaja.getKortti(0));

    }

    @Test
    public void hasCards() {
        Pelaaja pelaaja = new Pelaaja("Pena", 100, new Botti());
        assertEquals(false, pelaaja.hasCards());
    }

    @Test
    public void getName() {
        Pelaaja pelaaja = new Pelaaja("Pena", 100, new Botti());
        assertEquals("Pena", pelaaja.getName());
    }

    @Test
    public void getBet() {
        Pelaaja pelaaja = new Pelaaja("Pena", 100, new Botti());
        assertEquals(0, pelaaja.getBet());
    }

    @Test
    public void setBet() {
        Pelaaja pelaaja = new Pelaaja("Pena", 100, new Botti());
        pelaaja.setBet(50);
        assertEquals(50, pelaaja.getBet());
    }

    @Test
    public void getAction() {
        Pelaaja pelaaja = new Pelaaja("Pena", 100, new Botti());
        pelaaja.setAction(Action.ALL_IN);
        assertEquals(Action.ALL_IN, pelaaja.getAction());
    }

    @Test
    public void isAllin() {
        Pelaaja pelaaja = new Pelaaja("Pena", 100, new Botti());
        pelaaja.isAllIn();
        assertEquals(100, pelaaja.getRahat());
    }

    @Test
    public void postSmallBlind() {
        Pelaaja pelaaja = new Pelaaja("Pena", 100, new Botti());
        pelaaja.postSmallBlind(10);
        assertEquals(90, pelaaja.getRahat());
    }

    @Test
    public void postBigBlind() {
        Pelaaja pelaaja = new Pelaaja("Pena", 100, new Botti());
        pelaaja.postBigBlind(30);
        assertEquals(70, pelaaja.getRahat());
    }

    @Test
    public void voita() {
        Pelaaja pelaaja = new Pelaaja("Pena", 100, new Botti());
        pelaaja.voita(100);
        assertEquals(200, pelaaja.getRahat());
    }
    
    @Test
    public void klooni() {
        Pelaaja pelaaja = new Pelaaja("Pena", 100, new Botti());
        Pelaaja pelaaja2 = new Pelaaja("Paavo", 200, new Botti());
        
        assertNotEquals(pelaaja2,pelaaja.publicClone());
    }

}
