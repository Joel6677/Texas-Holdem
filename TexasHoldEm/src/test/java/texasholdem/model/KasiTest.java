package texasholdem.model;

import org.junit.Test;
import static org.junit.Assert.*;
import texasholdem.model.Kortti;

public class KasiTest {

    @Test
    public void listassaKymmenenKatta() {
        Kasi kasi = new Kasi();
        kasi.lisaaKadet();
        assertEquals(10, kasi.lista.size());
    }

    @Test
    public void tomiikoOnKuningasvarisuora() {
        Kasi kasi = new Kasi();
        Kortti[] flop = new Kortti[5];
        flop[0] = new Kortti(Kortti.Sijoitus.ÄSSÄ, Kortti.Maa.PATA);
        flop[1] = new Kortti(Kortti.Sijoitus.KURKO, Kortti.Maa.PATA);
        flop[2] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[3] = new Kortti(Kortti.Sijoitus.KAKSI, Kortti.Maa.RUUTU);
        flop[4] = new Kortti(Kortti.Sijoitus.KURKO, Kortti.Maa.RISTI);
        Kortti[] pelaajanKäsi = new Kortti[2];
        pelaajanKäsi[0] = new Kortti(Kortti.Sijoitus.JÄTKÄ, Kortti.Maa.PATA);
        pelaajanKäsi[1] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);
        kasi.setKasi(pelaajanKäsi);
        assertEquals(true, kasi.onKuningasvarisuora(flop));
    }

    @Test
    public void tomiikoOnVäri() {
        Kasi kasi = new Kasi();
        Kortti[] flop = new Kortti[5];
        flop[0] = new Kortti(Kortti.Sijoitus.ÄSSÄ, Kortti.Maa.PATA);
        flop[1] = new Kortti(Kortti.Sijoitus.KURKO, Kortti.Maa.PATA);
        flop[2] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[3] = new Kortti(Kortti.Sijoitus.KAKSI, Kortti.Maa.RUUTU);
        flop[4] = new Kortti(Kortti.Sijoitus.KURKO, Kortti.Maa.RISTI);
        Kortti[] pelaajanKäsi = new Kortti[2];
        pelaajanKäsi[0] = new Kortti(Kortti.Sijoitus.JÄTKÄ, Kortti.Maa.PATA);
        pelaajanKäsi[1] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);
        kasi.setKasi(pelaajanKäsi);
        assertEquals(true, kasi.onVari(flop));
    }

    @Test
    public void tomiikoOnSuora() {
        Kasi kasi = new Kasi();
        Kortti[] flop = new Kortti[5];
        flop[0] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[1] = new Kortti(Kortti.Sijoitus.KURKO, Kortti.Maa.PATA);
        flop[2] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);
        flop[3] = new Kortti(Kortti.Sijoitus.YHDEKSÄN, Kortti.Maa.RUUTU);
        flop[4] = new Kortti(Kortti.Sijoitus.KURKO, Kortti.Maa.RISTI);
        Kortti[] pelaajanKäsi = new Kortti[2];
        pelaajanKäsi[0] = new Kortti(Kortti.Sijoitus.JÄTKÄ, Kortti.Maa.PATA);
        pelaajanKäsi[1] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);
        kasi.setKasi(pelaajanKäsi);
        assertEquals(true, kasi.onSuora(flop));
    }

    @Test
    public void tomiikoOnKolmoset() {
        Kasi kasi = new Kasi();
        Kortti[] flop = new Kortti[5];
        flop[0] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[1] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[2] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[3] = new Kortti(Kortti.Sijoitus.YHDEKSÄN, Kortti.Maa.RUUTU);
        flop[4] = new Kortti(Kortti.Sijoitus.KURKO, Kortti.Maa.RISTI);
        Kortti[] pelaajanKäsi = new Kortti[2];
        pelaajanKäsi[0] = new Kortti(Kortti.Sijoitus.JÄTKÄ, Kortti.Maa.PATA);
        pelaajanKäsi[1] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);
        kasi.setKasi(pelaajanKäsi);
        assertEquals(true, kasi.onKolmoset(flop));
    }

    @Test
    public void toimiikoOnKaksiParia() {
        Kasi kasi = new Kasi();
        Kortti[] flop = new Kortti[5];
        flop[0] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[1] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[2] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);
        flop[3] = new Kortti(Kortti.Sijoitus.YHDEKSÄN, Kortti.Maa.RUUTU);
        flop[4] = new Kortti(Kortti.Sijoitus.KURKO, Kortti.Maa.RISTI);
        Kortti[] pelaajanKäsi = new Kortti[2];
        pelaajanKäsi[0] = new Kortti(Kortti.Sijoitus.JÄTKÄ, Kortti.Maa.PATA);
        pelaajanKäsi[1] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);
        kasi.setKasi(pelaajanKäsi);
        assertEquals(true, kasi.onPari(flop));
    }

    @Test
    public void toimiikoOnPari() {
        Kasi kasi = new Kasi();
        Kortti[] flop = new Kortti[5];
        flop[0] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[1] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[2] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);
        flop[3] = new Kortti(Kortti.Sijoitus.YHDEKSÄN, Kortti.Maa.RUUTU);
        flop[4] = new Kortti(Kortti.Sijoitus.KURKO, Kortti.Maa.RISTI);
        Kortti[] pelaajanKäsi = new Kortti[2];
        pelaajanKäsi[0] = new Kortti(Kortti.Sijoitus.JÄTKÄ, Kortti.Maa.PATA);
        pelaajanKäsi[1] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);
        kasi.setKasi(pelaajanKäsi);
        assertEquals(true, kasi.onPari(flop));
    }

    @Test
    public void toimiikoOnNeloset() {
        Kasi kasi = new Kasi();
        Kortti[] flop = new Kortti[5];
        flop[0] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[1] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[2] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[3] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.RUUTU);
        flop[4] = new Kortti(Kortti.Sijoitus.KURKO, Kortti.Maa.RISTI);
        Kortti[] pelaajanKäsi = new Kortti[2];
        pelaajanKäsi[0] = new Kortti(Kortti.Sijoitus.JÄTKÄ, Kortti.Maa.PATA);
        pelaajanKäsi[1] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);
        kasi.setKasi(pelaajanKäsi);
        assertEquals(true, kasi.onNeloset(flop));
    }

    @Test
    public void toimiikoOnTayskasi() {
        Kasi kasi = new Kasi();
        Kortti[] pokerikäsi = new Kortti[5];
        pokerikäsi[0] = new Kortti(Kortti.Sijoitus.KAKSI, Kortti.Maa.PATA);
        pokerikäsi[1] = new Kortti(Kortti.Sijoitus.ÄSSÄ, Kortti.Maa.PATA);
        pokerikäsi[2] = new Kortti(Kortti.Sijoitus.ÄSSÄ, Kortti.Maa.PATA);
        pokerikäsi[3] = new Kortti(Kortti.Sijoitus.KAKSI, Kortti.Maa.RUUTU);
        pokerikäsi[4] = new Kortti(Kortti.Sijoitus.ÄSSÄ, Kortti.Maa.RISTI);

        Kortti[] kädenKortit = new Kortti[2];
        kädenKortit[0] = new Kortti(Kortti.Sijoitus.KAKSI, Kortti.Maa.PATA);
        kädenKortit[1] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);
        kasi.setKasi(kädenKortit);

        assertEquals(true, kasi.onTayskasi(pokerikäsi));

    }

    @Test
    public void toimiikoOnVärisuora() {
        Kasi kasi = new Kasi();
        Kortti[] flop = new Kortti[5];
        flop[0] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[1] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[2] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);
        flop[3] = new Kortti(Kortti.Sijoitus.YHDEKSÄN, Kortti.Maa.RUUTU);
        flop[4] = new Kortti(Kortti.Sijoitus.KURKO, Kortti.Maa.RISTI);
        Kortti[] pelaajanKäsi = new Kortti[2];
        pelaajanKäsi[0] = new Kortti(Kortti.Sijoitus.JÄTKÄ, Kortti.Maa.PATA);
        pelaajanKäsi[1] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);
        kasi.setKasi(pelaajanKäsi);
        assertEquals(true, kasi.onVarisuora(flop));
    }

    @Test
    public void toimiikoGetHaiKaikistaKorteista() {
        Kasi kasi = new Kasi();
        Kortti[] flop = new Kortti[5];
        flop[0] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[1] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[2] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);
        flop[3] = new Kortti(Kortti.Sijoitus.YHDEKSÄN, Kortti.Maa.RUUTU);
        flop[4] = new Kortti(Kortti.Sijoitus.KURKO, Kortti.Maa.RISTI);
        Kortti[] pelaajanKäsi = new Kortti[2];
        pelaajanKäsi[0] = new Kortti(Kortti.Sijoitus.JÄTKÄ, Kortti.Maa.PATA);
        pelaajanKäsi[1] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);
        kasi.setKasi(pelaajanKäsi);
        assertEquals(flop[3], kasi.getHaiKaikistaKorteista(flop));
    }

    @Test
    public void toimiikoGetHaiKädestä() {
        Kasi kasi = new Kasi();
        Kortti[] flop = new Kortti[5];
        Kortti[] pelaajanKäsi = new Kortti[2];
        pelaajanKäsi[0] = new Kortti(Kortti.Sijoitus.JÄTKÄ, Kortti.Maa.PATA);
        pelaajanKäsi[1] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);
        kasi.setKasi(pelaajanKäsi);
        assertEquals(pelaajanKäsi[1], kasi.getHaiKadesta());
    }

}
