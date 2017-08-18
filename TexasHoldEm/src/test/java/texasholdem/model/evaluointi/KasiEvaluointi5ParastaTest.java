package texasholdem.model.evaluointi;

import texasholdem.model.evaluointi.KasiEvaluointi5Parasta;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;
import texasholdem.model.domain.Kasi.KasiSijoitus;
import texasholdem.model.domain.Kortti;
import texasholdem.model.domain.Kortti;


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
        assertEquals(KasiSijoitus.Kuningasvarisuora, kasi.getParasKasi(flop,pelaajanKäsi));
    }
    
    @Test
    public void toimiikOnKuningasvärisuoraFalse(){
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
        assertNotEquals(KasiSijoitus.Kuningasvarisuora, kasi.getParasKasi(flop,pelaajanKäsi));   
    }

    @Test
    public void tomiikoOnVäri() {
        KasiEvaluointi5Parasta kasi = new KasiEvaluointi5Parasta();;
        Kortti[] flop = new Kortti[5];
        flop[0] = new Kortti(Kortti.Sijoitus.KAHDEKSAN, Kortti.Maa.PATA);
        flop[1] = new Kortti(Kortti.Sijoitus.KURKO, Kortti.Maa.PATA);
        flop[2] = new Kortti(Kortti.Sijoitus.AKKA, Kortti.Maa.PATA);
        flop[3] = new Kortti(Kortti.Sijoitus.KAKSI, Kortti.Maa.RUUTU);
        flop[4] = new Kortti(Kortti.Sijoitus.KAKSI, Kortti.Maa.RISTI);
        Kortti[] pelaajanKäsi = new Kortti[2];
        pelaajanKäsi[0] = new Kortti(Kortti.Sijoitus.JÄTKÄ, Kortti.Maa.PATA);
        pelaajanKäsi[1] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);       
        assertEquals(KasiSijoitus.Väri, kasi.getParasKasi(flop,pelaajanKäsi));
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
        assertEquals(KasiSijoitus.Neloset, kasi.getParasKasi(flop,pelaajanKäsi));
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
   
        Kortti[] kadenKortit = new Kortti[2];
        kadenKortit[0] = new Kortti(Kortti.Sijoitus.KAKSI, Kortti.Maa.PATA);
        kadenKortit[1] = new Kortti(Kortti.Sijoitus.KYMPPI, Kortti.Maa.PATA);          
        assertEquals(KasiSijoitus.Täyskäsi, kasi.getParasKasi(pokerikäsi,kadenKortit));
    
      
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
        assertEquals(KasiSijoitus.Värisuora, kasi.getParasKasi(flop,pelaajanKasi));
    }

    
}