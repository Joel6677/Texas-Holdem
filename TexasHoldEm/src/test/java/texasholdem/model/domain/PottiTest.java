/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texasholdem.model.domain;

import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import texasholdem.botti.Botti;

/**
 *
 * @author Jolle
 */
public class PottiTest {
    
    Set<Pelaaja> osanottajat = new HashSet<Pelaaja>();
    Pelaaja pena = new Pelaaja("Pena", 0, new Botti());

    @Test
    public void Konstruktori() {

        Potti potti = new Potti(100);
        assertEquals(100, potti.getPanos());

    }

    @Test
    public void split() {
        Potti potti = new Potti(100);
        Pelaaja pelaaja = new Pelaaja("Paavo", 0, new Botti());
        potti.split(pelaaja, 50);
        assertEquals(50, potti.getPanos());
    }
    
    @Test
    public void getContributors() {
       Potti potti = new Potti(100);
        assertEquals(potti.getContributors(), potti.getContributors());   
    }
    
    @Test
    public void addContributer() {
        Potti potti = new Potti(100);
        osanottajat.add(pena);
        potti.addContributer(pena);
        assertEquals(true, potti.hasContributer(pena));
    }

    @Test
    public void clear() {
        Potti potti = new Potti(100);
       
        Pelaaja pelaaja = new Pelaaja("pena", 3, new Botti());
        
      
        potti.clear();
        assertEquals(0, potti.getArvo());
    }
}
