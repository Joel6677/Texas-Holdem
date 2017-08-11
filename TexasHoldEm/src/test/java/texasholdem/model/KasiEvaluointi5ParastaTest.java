/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texasholdem.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import texasholdem.model.Kortti;

/**
 *
 * @author hejoel
 */
public class KasiEvaluointi5ParastaTest {
    

    @Test
    public void tomiikoOnKuningasvärisuoraTrue() {
        KasiEvaluointi5Parasta kasi = new KasiEvaluointi5Parasta();
        Kortti[] flop = new Kortti[5];
        flop[0] = new Kortti(Kortti.Sijoitus.ÄSSÄ, Kortti.Maa.PATA);
        flop[1] = new Kortti(Kortti.Sijoitus.KURKO, Kortti.Maa.PATA);
        flop[2] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[3] = new Kortti(Kortti.Sijoitus.KAKSI, Kortti.Maa.RUUTU);
        flop[4] = new Kortti(Kortti.Sijoitus.KURKO, Kortti.Maa.RISTI);
        Kortti[] pelaajanKäsi = new Kortti[2];
        pelaajanKäsi[0] = new Kortti(Kortti.Sijoitus.JÄTKÄ, Kortti.Maa.PATA);
        pelaajanKäsi[1] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);
        assertEquals(true, kasi.onKuningasvarisuora(flop, pelaajanKäsi));
    }

    @Test
    public void tomiikoOnKuningasvärisuoraFalse() {
        KasiEvaluointi5Parasta kasi = new KasiEvaluointi5Parasta();
        Kortti[] flop = new Kortti[5];
        flop[0] = new Kortti(Kortti.Sijoitus.ÄSSÄ, Kortti.Maa.RUUTU);
        flop[1] = new Kortti(Kortti.Sijoitus.KURKO, Kortti.Maa.PATA);
        flop[2] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[3] = new Kortti(Kortti.Sijoitus.KAKSI, Kortti.Maa.RUUTU);
        flop[4] = new Kortti(Kortti.Sijoitus.KURKO, Kortti.Maa.RISTI);
        Kortti[] pelaajanKäsi = new Kortti[2];
        pelaajanKäsi[0] = new Kortti(Kortti.Sijoitus.JÄTKÄ, Kortti.Maa.PATA);
        pelaajanKäsi[1] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);
        assertFalse(kasi.onKuningasvarisuora(flop, pelaajanKäsi));
    }

    @Test
    public void tomiikoOnVäri() {
        KasiEvaluointi5Parasta kasi = new KasiEvaluointi5Parasta();;
        Kortti[] flop = new Kortti[5];
        flop[0] = new Kortti(Kortti.Sijoitus.ÄSSÄ, Kortti.Maa.PATA);
        flop[1] = new Kortti(Kortti.Sijoitus.KURKO, Kortti.Maa.PATA);
        flop[2] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[3] = new Kortti(Kortti.Sijoitus.KAKSI, Kortti.Maa.RUUTU);
        flop[4] = new Kortti(Kortti.Sijoitus.KURKO, Kortti.Maa.RISTI);
        Kortti[] pelaajanKäsi = new Kortti[2];
        pelaajanKäsi[0] = new Kortti(Kortti.Sijoitus.JÄTKÄ, Kortti.Maa.PATA);
        pelaajanKäsi[1] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);
        assertEquals(true, kasi.onVari(flop, pelaajanKäsi));
    }

    @Test
    public void toimiikoOnNeloset() {
        KasiEvaluointi5Parasta kasi = new KasiEvaluointi5Parasta();
        Kortti[] flop = new Kortti[5];
        flop[0] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[1] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[2] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[3] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.RUUTU);
        flop[4] = new Kortti(Kortti.Sijoitus.KURKO, Kortti.Maa.RISTI);
        Kortti[] pelaajanKäsi = new Kortti[2];
        pelaajanKäsi[0] = new Kortti(Kortti.Sijoitus.JÄTKÄ, Kortti.Maa.PATA);
        pelaajanKäsi[1] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);
        assertEquals(true, kasi.onNeloset(flop, pelaajanKäsi));
    }

    @Test
    public void toimiikoOnTayskasi() {
        KasiEvaluointi5Parasta kasi = new KasiEvaluointi5Parasta();
        Kortti[] pokerikäsi = new Kortti[5];
        pokerikäsi[0] = new Kortti(Kortti.Sijoitus.KAKSI, Kortti.Maa.PATA);
        pokerikäsi[1] = new Kortti(Kortti.Sijoitus.ÄSSÄ, Kortti.Maa.PATA);
        pokerikäsi[2] = new Kortti(Kortti.Sijoitus.ÄSSÄ, Kortti.Maa.PATA);
        pokerikäsi[3] = new Kortti(Kortti.Sijoitus.KAKSI, Kortti.Maa.RUUTU);
        pokerikäsi[4] = new Kortti(Kortti.Sijoitus.ÄSSÄ, Kortti.Maa.RISTI);

        Kortti[] kädenKortit = new Kortti[2];
        kädenKortit[0] = new Kortti(Kortti.Sijoitus.KAKSI, Kortti.Maa.PATA);
        kädenKortit[1] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);
        assertEquals(true, kasi.onTayskasi(pokerikäsi, kädenKortit));

    }

    @Test
    public void toimiikoOnVarisuora() {
        KasiEvaluointi5Parasta kasi = new KasiEvaluointi5Parasta();
        Kortti[] flop = new Kortti[5];
        flop[0] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[1] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[2] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);
        flop[3] = new Kortti(Kortti.Sijoitus.YHDEKSÄN, Kortti.Maa.RUUTU);
        flop[4] = new Kortti(Kortti.Sijoitus.KURKO, Kortti.Maa.RISTI);
        Kortti[] pelaajanKasi = new Kortti[2];
        pelaajanKasi[0] = new Kortti(Kortti.Sijoitus.JÄTKÄ, Kortti.Maa.PATA);
        pelaajanKasi[1] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);
        assertEquals(true, kasi.onVarisuora(flop, pelaajanKasi));
    }

}
