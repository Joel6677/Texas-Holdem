package texasholdem.model.evaluointi;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;
import static org.junit.Assert.*;
import org.junit.Test;
import texasholdem.model.domain.Kasi.KasiSijoitus;
import texasholdem.model.domain.Kortti;
import texasholdem.model.domain.Kortti;

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
        assertEquals(KasiSijoitus.Suora, kasi.getHuonoKasi(flop, pelaajanKasi));
    }

    @Test
    public void tomiikoOnKolmoset() {
        KasiEvaluointi5Huonointa kasi = new KasiEvaluointi5Huonointa();
        Kortti[] flop = new Kortti[5];
        flop[0] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[1] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[2] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[3] = new Kortti(Kortti.Sijoitus.YHDEKSÄN, Kortti.Maa.RUUTU);
        flop[4] = new Kortti(Kortti.Sijoitus.KAKSI, Kortti.Maa.RISTI);
        Kortti[] pelaajanKasi = new Kortti[2];
        pelaajanKasi[0] = new Kortti(Kortti.Sijoitus.KOLME, Kortti.Maa.PATA);
        pelaajanKasi[1] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);

        assertEquals(KasiSijoitus.Kolmoset, kasi.getHuonoKasi(flop, pelaajanKasi));
    }

    @Test
    public void toimiikoOnKaksiPariaTrue() {
        KasiEvaluointi5Huonointa kasi = new KasiEvaluointi5Huonointa();
        Kortti[] flop = new Kortti[5];
        flop[0] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[1] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[2] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);
        flop[3] = new Kortti(Kortti.Sijoitus.KAKSI, Kortti.Maa.RUUTU);
        flop[4] = new Kortti(Kortti.Sijoitus.KURKO, Kortti.Maa.RISTI);
        Kortti[] pelaajanKasi = new Kortti[2];
        pelaajanKasi[0] = new Kortti(Kortti.Sijoitus.KOLME, Kortti.Maa.PATA);
        pelaajanKasi[1] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);

        assertEquals(KasiSijoitus.Kaksi_paria, kasi.getHuonoKasi(flop, pelaajanKasi));
    }
    
    @Test
    public void toimiikoOnKaksiPariaFalse() {
        KasiEvaluointi5Huonointa kasi = new KasiEvaluointi5Huonointa();
        Kortti[] flop = new Kortti[5];
        flop[0] = new Kortti(Kortti.Sijoitus.KAKSI, Kortti.Maa.PATA);
        flop[1] = new Kortti(Kortti.Sijoitus.KOLME, Kortti.Maa.PATA);
        flop[2] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);
        flop[3] = new Kortti(Kortti.Sijoitus.YHDEKSÄN, Kortti.Maa.RUUTU);
        flop[4] = new Kortti(Kortti.Sijoitus.KURKO, Kortti.Maa.RISTI);
        Kortti[] pelaajanKasi = new Kortti[2];
        pelaajanKasi[0] = new Kortti(Kortti.Sijoitus.JÄTKÄ, Kortti.Maa.PATA);
        pelaajanKasi[1] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);

        assertNotEquals(KasiSijoitus.Kaksi_paria, kasi.getHuonoKasi(flop, pelaajanKasi));
    }

    @Test
    public void toimiikoOnPari() {
        KasiEvaluointi5Huonointa kasi = new KasiEvaluointi5Huonointa();
        Kortti[] flop = new Kortti[5];
        flop[0] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[1] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[2] = new Kortti(Kortti.Sijoitus.KAKSI, Kortti.Maa.PATA);
        flop[3] = new Kortti(Kortti.Sijoitus.YHDEKSÄN, Kortti.Maa.RUUTU);
        flop[4] = new Kortti(Kortti.Sijoitus.KURKO, Kortti.Maa.RISTI);
        Kortti[] pelaajanKasi = new Kortti[2];
        pelaajanKasi[0] = new Kortti(Kortti.Sijoitus.KOLME, Kortti.Maa.PATA);
        pelaajanKasi[1] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);

        assertEquals(KasiSijoitus.Pari, kasi.getHuonoKasi(flop, pelaajanKasi));
    }

}