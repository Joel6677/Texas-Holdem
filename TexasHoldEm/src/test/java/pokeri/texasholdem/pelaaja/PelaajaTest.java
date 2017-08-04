/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokeri.texasholdem.pelaaja;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pokeri.texasholdem.kortti.Kortti;
import pokeri.texasholdem.kortti.Kortti.Arvo;
import pokeri.texasholdem.kortti.Kortti.Maa;

/**
 *
 * @author Jolle
 */
public class PelaajaTest {
    
   
     @Test
     public void konstruktoriTesti() {
         Kortti kortti1 = new Kortti(Arvo.FOUR, Maa.HEARTS);
         Kortti kortti2 = new Kortti(Arvo.ACE, Maa.SPADES);
         Pelaaja pelaaja =  new Pelaaja(kortti1, kortti2);
         assertEquals("FOUR of HEARTS ja ACE of SPADES", pelaaja.toString());
         
     }
     
     @Test
     public void setKortti() {
         Kortti kortti1 = new Kortti(Arvo.FOUR, Maa.HEARTS);
         Kortti kortti2 = new Kortti(Arvo.ACE, Maa.SPADES);
         Pelaaja pelaaja =  new Pelaaja();
         pelaaja.setKortti(kortti1, 0);
         assertEquals(kortti1,pelaaja.getKortti(0));
       
     }
     
     @Test
     public void getKortti() {
         Kortti kortti1 = new Kortti(Arvo.FOUR, Maa.HEARTS);
         Kortti kortti2 = new Kortti(Arvo.ACE, Maa.SPADES);
         Pelaaja pelaaja =  new Pelaaja(kortti1, kortti2);
         assertEquals(kortti1, pelaaja.getKortti(0));
     }
     
     @Test
     public void korttienMaara() {
         Kortti kortti1 = new Kortti(Arvo.FOUR, Maa.HEARTS);
         Kortti kortti2 = new Kortti(Arvo.ACE, Maa.SPADES);
         Pelaaja pelaaja =  new Pelaaja(kortti1, kortti2);
         assertEquals(2,pelaaja.korttienMaara());       
     }
}