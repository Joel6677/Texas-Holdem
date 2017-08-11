/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texasholdem.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import texasholdem.model.Kortti;

/**
 *
 * @author hejoel
 */
public class KasiEvaluointi5HuonointaTest {

    @Test
    public void tomiikoOnSuora() {
        KasiEvaluointi5Huonointa kasi = new KasiEvaluointi5Huonointa();
        Kortti[] flop = new Kortti[5];
        flop[0] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[1] = new Kortti(Kortti.Sijoitus.KURKO, Kortti.Maa.PATA);
        flop[2] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);
        flop[3] = new Kortti(Kortti.Sijoitus.YHDEKSÄN, Kortti.Maa.RUUTU);
        flop[4] = new Kortti(Kortti.Sijoitus.KURKO, Kortti.Maa.RISTI);
        Kortti[] pelaajanKasi = new Kortti[2];
        pelaajanKasi[0] = new Kortti(Kortti.Sijoitus.JÄTKÄ, Kortti.Maa.PATA);
        pelaajanKasi[1] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);
        assertEquals(true, kasi.onSuora(flop, pelaajanKasi));
    }

    @Test
    public void tomiikoOnKolmoset() {
        KasiEvaluointi5Huonointa kasi = new KasiEvaluointi5Huonointa();
        Kortti[] flop = new Kortti[5];
        flop[0] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[1] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[2] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[3] = new Kortti(Kortti.Sijoitus.YHDEKSÄN, Kortti.Maa.RUUTU);
        flop[4] = new Kortti(Kortti.Sijoitus.KURKO, Kortti.Maa.RISTI);
        Kortti[] pelaajanKasi = new Kortti[2];
        pelaajanKasi[0] = new Kortti(Kortti.Sijoitus.JÄTKÄ, Kortti.Maa.PATA);
        pelaajanKasi[1] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);

        assertEquals(true, kasi.onKolmoset(flop, pelaajanKasi));
    }

    @Test
    public void toimiikoOnKaksiParia() {
        KasiEvaluointi5Huonointa kasi = new KasiEvaluointi5Huonointa();
        Kortti[] flop = new Kortti[5];
        flop[0] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[1] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[2] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);
        flop[3] = new Kortti(Kortti.Sijoitus.YHDEKSÄN, Kortti.Maa.RUUTU);
        flop[4] = new Kortti(Kortti.Sijoitus.KURKO, Kortti.Maa.RISTI);
        Kortti[] pelaajanKasi = new Kortti[2];
        pelaajanKasi[0] = new Kortti(Kortti.Sijoitus.JÄTKÄ, Kortti.Maa.PATA);
        pelaajanKasi[1] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);

        assertEquals(true, kasi.onPari(flop, pelaajanKasi));
    }

    @Test
    public void toimiikoOnPari() {
        KasiEvaluointi5Huonointa kasi = new KasiEvaluointi5Huonointa();
        Kortti[] flop = new Kortti[5];
        flop[0] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[1] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[2] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);
        flop[3] = new Kortti(Kortti.Sijoitus.YHDEKSÄN, Kortti.Maa.RUUTU);
        flop[4] = new Kortti(Kortti.Sijoitus.KURKO, Kortti.Maa.RISTI);
        Kortti[] pelaajanKasi = new Kortti[2];
        pelaajanKasi[0] = new Kortti(Kortti.Sijoitus.JÄTKÄ, Kortti.Maa.PATA);
        pelaajanKasi[1] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);

        assertEquals(true, kasi.onPari(flop, pelaajanKasi));
    }

    @Test
    public void toimiikoGetHaiKaikistaKorteista() {
        KasiEvaluointi5Huonointa kasi = new KasiEvaluointi5Huonointa();
        Kortti[] flop = new Kortti[5];
        flop[0] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[1] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[2] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);
        flop[3] = new Kortti(Kortti.Sijoitus.YHDEKSÄN, Kortti.Maa.RUUTU);
        flop[4] = new Kortti(Kortti.Sijoitus.KURKO, Kortti.Maa.RISTI);
        Kortti[] pelaajanKasi = new Kortti[2];
        pelaajanKasi[0] = new Kortti(Kortti.Sijoitus.JÄTKÄ, Kortti.Maa.PATA);
        pelaajanKasi[1] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);
        assertEquals(flop[3], kasi.getHaiKaikistaKorteista(flop, pelaajanKasi));
    }

}
