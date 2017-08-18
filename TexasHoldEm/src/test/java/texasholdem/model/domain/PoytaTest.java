/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texasholdem.model.domain;

import texasholdem.model.domain.Poyta;
import texasholdem.model.domain.Kortti;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Jolle
 */
public class PoytaTest {
    

    @Test
    public void setPoytaKortti() {
        Kortti kortti1 = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.HERTTA);
        Kortti kortti2 = new Kortti(Kortti.Sijoitus.KURKO, Kortti.Maa.PATA);
        Poyta poyta = new Poyta();
        poyta.setPoytaKortti(kortti1, 0);
        assertEquals(kortti1, poyta.getPoytaKortti(0));

    }

    @Test
    public void getPoytaKortit() {
        Kortti kortti1 = new Kortti(Kortti.Sijoitus.NELJÄ, Kortti.Maa.HERTTA);
        Kortti kortti2 = new Kortti(Kortti.Sijoitus.ÄSSÄ, Kortti.Maa.PATA);
        Kortti kortti3 = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.HERTTA);
        Kortti kortti4 = new Kortti(Kortti.Sijoitus.NELJÄ, Kortti.Maa.HERTTA);
        Kortti kortti5 = new Kortti(Kortti.Sijoitus.ÄSSÄ, Kortti.Maa.PATA);
        Poyta poyta = new Poyta();
        
        poyta.setPoytaKortti(kortti1, 0);
        poyta.setPoytaKortti(kortti2, 1);
        poyta.setPoytaKortti(kortti3, 2);
        poyta.setPoytaKortti(kortti4, 3);
        poyta.setPoytaKortti(kortti5, 4);

        Kortti[] kortit = new Kortti[5];
        kortit[0] = kortti1;
        kortit[1] = kortti2;
        kortit[2] = kortti3;
        kortit[3] = kortti4;
        kortit[4] = kortti5;
        
        Assert.assertArrayEquals(kortit, poyta.getPoytaKortit());
    }

    @Test
    public void poydanKoko() {
        
        Poyta poyta = new Poyta();

        assertEquals(5, poyta.getPoydanKoko());
    }
    
    
}
