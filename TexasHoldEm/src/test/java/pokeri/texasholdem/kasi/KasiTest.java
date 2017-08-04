/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokeri.texasholdem.kasi;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jolle
 */
public class KasiTest {

    @Test
    public void listassaKymmenenKättä() {
         Kasi kasi = new Kasi();
         kasi.lisaaKadet();
         assertEquals(10, kasi.lista.size());
    }
   
}