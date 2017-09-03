package texasholdem.model.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import texasholdem.model.domain.Kortti;
import texasholdem.model.domain.Kasi;

public class KasiTest {

    Kasi kasi;
    Kortti[] pelaajanKasi;
    List<Kortti> alusta = new ArrayList<Kortti>();
    Kortti[] flop;
    Kortti kortti;
    Kortti kortti1;
    Kortti kortti2;
    Kortti kortti3;
    Kortti kortti4;
    Kortti kortti5;
    Kortti kortti6;
    Kortti kortti7;
    
    Kortti[] testiKasi = new Kortti[1];

    @Before
    public void setUp() {
        kasi = new Kasi();
        
        
        pelaajanKasi = new Kortti[2];
        kortti = new Kortti(Kortti.Sijoitus.VIISI, Kortti.Maa.PATA);
        kortti2 = new Kortti(Kortti.Sijoitus.KUUSI, Kortti.Maa.PATA);
        
        testiKasi[0] = kortti;
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

        alusta.add(kortti);

    }

    @Test
    public void listassaKymmenenKatta() {

        kasi.lisaaKadet();
        assertEquals(10, kasi.lista.size());
    }

    @Test
    public void asettaaKonstruktorinOikein() {

        kasi = new Kasi(pelaajanKasi);

        assertEquals("Kortti{sijoitus=VIISI, maa=PATA}" + " ja " + "Kortti{sijoitus=KUUSI, maa=PATA}", kasi.toString());

    }

    @Test
    public void asettaaKonstruktorinOikeinCollections() {

        kasi = new Kasi(alusta);

        assertEquals("null ja null", kasi.toString());

    }

    @Test
    public void getKasiOikein() {

        kasi.setKasi(pelaajanKasi);

        Assert.assertArrayEquals(pelaajanKasi, kasi.getKasi());

    }

    @Test
    public void setKasiOikein() {

        kasi.setKasi(pelaajanKasi);
        Assert.assertArrayEquals(pelaajanKasi, kasi.getKasi());
    }

    @Test
    public void getHaiKadestaToimii() {

        Kortti[] pelaajanKasi2 = new Kortti[2];
        Kortti kortti = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);
        Kortti kortti2 = new Kortti(Kortti.Sijoitus.KAKSI, Kortti.Maa.PATA);

        pelaajanKasi2[0] = kortti;
        pelaajanKasi2[1] = kortti2;

        kasi.setKasi(pelaajanKasi2);
        assertEquals(kortti, kasi.getHaiKadesta());
    }

    @Test
    public void getKasiSijoitusToimii() {
        kasi.setKasiSijoitus(Kasi.KasiSijoitus.Kaksi_paria);
        assertEquals(Kasi.KasiSijoitus.Kaksi_paria, kasi.getKasiSijoitus());
    }

    @Test
    public void getKortitToimii() {

        kasi.lisaaKortit(testiKasi);
        Assert.assertArrayEquals(testiKasi, kasi.getKortit());

    }


    @Test
    public void sizeToimii() {
        kasi.lisaaKortit(flop);
        assertEquals(5, kasi.size());
    }

    @Test
    public void lisaaPelaajanKorttiToimii() {
        kasi.lisaaPelaajanKortti(kortti);
        assertEquals(kortti, kasi.getPelaajanKortti(0));

    }



    @Test
    public void addCardsToimii() {
        kasi.addCards(alusta);
        assertEquals(1, kasi.size());
    }

//    @Test
//    public void katsoKasienSijoitusToimiiOikein() {
//        
//        
//        kasi.setKasi(pelaajanKasi);
//        assertEquals(kasi.kasiSijoitus.Kolmoset, kasi.katsoKasienSijoitus(flop));
//    }
}
