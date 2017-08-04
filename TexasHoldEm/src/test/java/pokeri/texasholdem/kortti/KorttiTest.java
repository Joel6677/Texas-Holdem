/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokeri.texasholdem.kortti;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pokeri.texasholdem.kortti.Kortti.Arvo;
import pokeri.texasholdem.kortti.Kortti.Maa;

/**
 *
 * @author Jolle
 */
public class KorttiTest {
    


     @Test
     public void konstruktoriAsettaaArvotOikein() {
         Kortti kortti = new Kortti(Arvo.ACE, Maa.CLUBS);
         assertEquals("ACE of CLUBS", kortti.toString());   
     }
       
     @Test
     public void getArvoOikein() {
         Kortti kortti = new Kortti(Arvo.ACE, Maa.CLUBS);
         assertEquals(Arvo.ACE, kortti.getArvo());
     }
     @Test
     public void getMaaOikein() {
         Kortti kortti = new Kortti(Arvo.DEUCE, Maa.DIAMONDS);
         assertEquals(Maa.DIAMONDS, kortti.getMaa());
         
     }
     @Test
     public void setArvoOikein() {
         Kortti kortti = new Kortti(Arvo.ACE, Maa.HEARTS);
         kortti.setArvo(Arvo.JACK);
         assertEquals(Arvo.JACK, kortti.getArvo());
     }
     @Test
     public void setMaaOikein() {
         Kortti kortti = new Kortti(Arvo.ACE, Maa.HEARTS);
         kortti.setMaa(Maa.SPADES);
         assertEquals(Maa.SPADES, kortti.getMaa());
     }
}