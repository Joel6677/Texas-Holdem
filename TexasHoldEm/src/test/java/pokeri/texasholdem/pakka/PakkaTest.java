/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokeri.texasholdem.pakka;

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
public class PakkaTest {
    

  
     @Test
     public void oikeaMääräMaita() {
        Pakka pakka = new Pakka();
        int pata = 0;
        int ruutu = 0;
        int hertta = 0;
        int risti = 0;
        pakka.luoPakka();
        for (int i = 0; i < pakka.pakka.size(); i++) {
            if (pakka.pakka.get(i).maa.equals(Maa.CLUBS)) {
                risti++;
            }
            if (pakka.pakka.get(i).maa.equals(Maa.DIAMONDS)) {
                ruutu++;
            }
            if (pakka.pakka.get(i).maa.equals(Maa.HEARTS)) {
                hertta++;
            }
            if (pakka.pakka.get(i).maa.equals(Maa.SPADES)) {
                pata++;
            }
        }
            
         assertEquals(13, risti);
         assertEquals(13, ruutu);   
         assertEquals(13, hertta);
         assertEquals(13, pata);
         
     }
     
     @Test
     public void oikeaKokoPakassa() {
         Pakka pakka = new Pakka();
         pakka.luoPakka();
         assertEquals(52, pakka.pakka.size());
     }
     
     @Test
     public void oikeatArvotPakassa() {
         Pakka pakka = new Pakka();
        int kaksi = 0;
        int kolme = 0;
        int neljä = 0;
        int viisi = 0;
        int kuusi = 0;
        int seitsemän = 0;
        int kahdeksan = 0;
        int yhdeksän = 0;
        int kympit = 0;
        int jätkät = 0;
        int akat = 0;
        int kurkot = 0;
        int ässät = 0;
        
        pakka.luoPakka();
        
        for (int i = 0; i < pakka.pakka.size(); i++) {
            if (pakka.pakka.get(i).arvo.equals(Arvo.DEUCE)) {
                kaksi++;
            }
            if (pakka.pakka.get(i).arvo.equals(Arvo.THREE)) {
                kolme++;
            }if (pakka.pakka.get(i).arvo.equals(Arvo.FOUR)) {
                neljä++;
            }
           if (pakka.pakka.get(i).arvo.equals(Arvo.FIVE)) {
                viisi++;
            }
           if (pakka.pakka.get(i).arvo.equals(Arvo.SIX)) {
                kuusi++;
            }
           if (pakka.pakka.get(i).arvo.equals(Arvo.SEVEN)) {
                seitsemän++;
            }
           if (pakka.pakka.get(i).arvo.equals(Arvo.EIGHT)) {
                kahdeksan++;
            }
           if (pakka.pakka.get(i).arvo.equals(Arvo.NINE)) {
                yhdeksän++;
            }
           if (pakka.pakka.get(i).arvo.equals(Arvo.TEN)) {
                kympit++;
            }
           if (pakka.pakka.get(i).arvo.equals(Arvo.JACK)) {
                jätkät++;
            }
           if (pakka.pakka.get(i).arvo.equals(Arvo.QUEEN)) {
                akat++;
            }
           if (pakka.pakka.get(i).arvo.equals(Arvo.KING)) {
                kurkot++;
            }
           if (pakka.pakka.get(i).arvo.equals(Arvo.ACE)) {
                ässät++;
            }
        }
            
         assertEquals(4, kaksi);
         assertEquals(4, kolme);   
         assertEquals(4, neljä);
         assertEquals(4, viisi);
         assertEquals(4, kuusi);
         assertEquals(4, seitsemän);   
         assertEquals(4, kahdeksan);
         assertEquals(4, yhdeksän);
         assertEquals(4, kympit);
         assertEquals(4, jätkät);   
         assertEquals(4, akat);
         assertEquals(4, kurkot);
         assertEquals(4, ässät);
     }
     
     
     @Test
     public void pakkaSekoittuu() {
         Pakka pakka = new Pakka();
         
         pakka.luoPakka();
         boolean onkoSama = true;
         Kortti eka = pakka.pakka.get(0);
         pakka.sekoitaPakka();
         Kortti toka = pakka.pakka.get(0);
         if (eka != toka) {
             onkoSama = false;
         }
         assertEquals(false, onkoSama);
     }
     
     
}
