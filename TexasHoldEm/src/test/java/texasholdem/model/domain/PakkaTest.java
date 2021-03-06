package texasholdem.model.domain;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import texasholdem.model.domain.Kortti;
import texasholdem.model.domain.Kortti.Sijoitus;
import texasholdem.model.domain.Kortti.Maa;
import texasholdem.model.domain.Pakka;

public class PakkaTest {

    @Test
    public void oikeaMääräMaita() {
        Pakka pakka = new Pakka();
        int pata = 0;
        int ruutu = 0;
        int hertta = 0;
        int risti = 0;
//        pakka.luoPakka();
        for (int i = 0; i < pakka.pakka.size(); i++) {
            if (pakka.pakka.get(i).getMaa().equals(Maa.RISTI)) {
                risti++;
            }
            if (pakka.pakka.get(i).getMaa().equals(Maa.RUUTU)) {
                ruutu++;
            }
            if (pakka.pakka.get(i).getMaa().equals(Maa.HERTTA)) {
                hertta++;
            }
            if (pakka.pakka.get(i).getMaa().equals(Maa.PATA)) {
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
//        pakka.luoPakka();
        assertEquals(52, pakka.pakka.size());
    }

    @Test
    public void oikeatArvotPakassa() {
        Pakka pakka = new Pakka();
        int kaksi = 0;
        int kolme = 0;
        int neljÄ = 0;
        int viisi = 0;
        int kuusi = 0;
        int seitsemÄn = 0;
        int kahdeksan = 0;
        int yhdeksÄn = 0;
        int kympit = 0;
        int jÄtkÄt = 0;
        int akat = 0;
        int kurkot = 0;
        int ÄssÄt = 0;

//        pakka.luoPakka();
        for (int i = 0; i < pakka.pakka.size(); i++) {
            if (pakka.pakka.get(i).getSijoitus().equals(Sijoitus.KAKSI)) {
                kaksi++;
            }
            if (pakka.pakka.get(i).getSijoitus().equals(Sijoitus.KOLME)) {
                kolme++;
            }
            if (pakka.pakka.get(i).getSijoitus().equals(Sijoitus.NELJÄ)) {
                neljÄ++;
            }
            if (pakka.pakka.get(i).getSijoitus().equals(Sijoitus.VIISI)) {
                viisi++;
            }
            if (pakka.pakka.get(i).getSijoitus().equals(Sijoitus.KUUSI)) {
                kuusi++;
            }
            if (pakka.pakka.get(i).getSijoitus().equals(Sijoitus.SEITSEMÄN)) {
                seitsemÄn++;
            }
            if (pakka.pakka.get(i).getSijoitus().equals(Sijoitus.KAHDEKSAN)) {
                kahdeksan++;
            }
            if (pakka.pakka.get(i).getSijoitus().equals(Sijoitus.YHDEKSÄN)) {
                yhdeksÄn++;
            }
            if (pakka.pakka.get(i).getSijoitus().equals(Sijoitus.KYMPPI)) {
                kympit++;
            }
            if (pakka.pakka.get(i).getSijoitus().equals(Sijoitus.JÄTKÄ)) {
                jÄtkÄt++;
            }
            if (pakka.pakka.get(i).getSijoitus().equals(Sijoitus.AKKA)) {
                akat++;
            }
            if (pakka.pakka.get(i).getSijoitus().equals(Sijoitus.KURKO)) {
                kurkot++;
            }
            if (pakka.pakka.get(i).getSijoitus().equals(Sijoitus.ÄSSÄ)) {
                ÄssÄt++;
            }
        }

        assertEquals(4, kaksi);
        assertEquals(4, kolme);
        assertEquals(4, neljÄ);
        assertEquals(4, viisi);
        assertEquals(4, kuusi);
        assertEquals(4, seitsemÄn);
        assertEquals(4, kahdeksan);
        assertEquals(4, yhdeksÄn);
        assertEquals(4, kympit);
        assertEquals(4, jÄtkÄt);
        assertEquals(4, akat);
        assertEquals(4, kurkot);
        assertEquals(4, ÄssÄt);
    }

    @Test
    public void pakkaSekoittuu() {
        Pakka pakka = new Pakka();

//        pakka.luoPakka();
        boolean onkoSama = true;
        Kortti eka = pakka.pakka.get(0);
        pakka.sekoitaPakka();
        Kortti toka = pakka.pakka.get(0);
        if (eka != toka) {
            onkoSama = false;
        }
        assertEquals(false, onkoSama);
    }

    @Test
    public void dealToimii() {
        Pakka pakka = new Pakka();

        assertEquals(pakka.getPakka().get(0), pakka.deal());
    }

    @Test
    public void dealToimiiNoOfCards() {
        Pakka pakka = new Pakka();
        List<Kortti> jaetutKortit = new ArrayList<Kortti>();
        jaetutKortit.add(pakka.getPakka().get(0));

        assertEquals(jaetutKortit, pakka.deal(1));

    }

}
