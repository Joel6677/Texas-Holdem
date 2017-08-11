package texasholdem.model;

import org.junit.Test;
import static org.junit.Assert.*;
import texasholdem.model.Kortti.Sijoitus;
import texasholdem.model.Kortti.Maa;

public class KorttiTest {

    @Test
    public void konstruktoriAsettaaArvotOikein() {
        Kortti kortti = new Kortti(Sijoitus.ÄSSÄ, Maa.PATA);
        assertEquals("Kortti{sijoitus=ÄSSÄ, maa=PATA}", kortti.toString());
    }

    @Test
    public void getSijoitusOikein() {
        Kortti kortti = new Kortti(Sijoitus.ÄSSÄ, Maa.PATA);
        assertEquals(Sijoitus.ÄSSÄ, kortti.getSijoitus());
    }

    @Test
    public void getMaaOikein() {
        Kortti kortti = new Kortti(Sijoitus.KAKSI, Maa.RUUTU);
        assertEquals(Maa.RUUTU, kortti.getMaa());

    }

    @Test
    public void setSijoitusOikein() {
        Kortti kortti = new Kortti(Sijoitus.ÄSSÄ, Maa.HERTTA);
        kortti.setSijoitus(Sijoitus.JÄTKÄ);
        assertEquals(Sijoitus.JÄTKÄ, kortti.getSijoitus());
    }

    @Test
    public void setMaaOikein() {
        Kortti kortti = new Kortti(Sijoitus.JÄTKÄ, Maa.HERTTA);
        kortti.setMaa(Maa.PATA);
        assertEquals(Maa.PATA, kortti.getMaa());
    }
}
