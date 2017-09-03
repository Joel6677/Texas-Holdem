package texasholdem.model.domain;

import texasholdem.model.domain.Kortti;
import org.junit.Test;
import static org.junit.Assert.*;
import texasholdem.model.domain.Kortti.Sijoitus;
import texasholdem.model.domain.Kortti.Maa;

public class KorttiTest {
    
    private Kortti kortti1 = new Kortti(Sijoitus.JÄTKÄ, Maa.HERTTA);
    private Kortti kortti2 = new Kortti(Sijoitus.AKKA, Maa.HERTTA);

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
    
    @Test
    public void getArvoOikein() {
        Kortti kortti = new Kortti(Sijoitus.JÄTKÄ, Maa.HERTTA);
        
        assertEquals(2, kortti.getMaa().getArvo());
    }
    
    @Test
    public void compToOikein() {
        
        assertEquals(kortti1.compareTo(kortti2),kortti1.compareTo(kortti2));
    }
    
}