package texasholdem.model;

import org.junit.Test;
import static org.junit.Assert.*;
import texasholdem.model.Kortti;
import texasholdem.model.Kortti.Sijoitus;
import texasholdem.model.Kortti.Maa;

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
            if (pakka.pakka.get(i).getSijoitus().equals(Sijoitus.KAKSI)) {
                kaksi++;
            }
            if (pakka.pakka.get(i).getSijoitus().equals(Sijoitus.KOLME)) {
                kolme++;
            }
            if (pakka.pakka.get(i).getSijoitus().equals(Sijoitus.NELJÄ)) {
                neljä++;
            }
            if (pakka.pakka.get(i).getSijoitus().equals(Sijoitus.VIISI)) {
                viisi++;
            }
            if (pakka.pakka.get(i).getSijoitus().equals(Sijoitus.KUUSI)) {
                kuusi++;
            }
            if (pakka.pakka.get(i).getSijoitus().equals(Sijoitus.SEITSEMÄN)) {
                seitsemän++;
            }
            if (pakka.pakka.get(i).getSijoitus().equals(Sijoitus.KAHDEKSAN)) {
                kahdeksan++;
            }
            if (pakka.pakka.get(i).getSijoitus().equals(Sijoitus.YHDEKSÄN)) {
                yhdeksän++;
            }
            if (pakka.pakka.get(i).getSijoitus().equals(Sijoitus.KYMPPI)) {
                kympit++;
            }
            if (pakka.pakka.get(i).getSijoitus().equals(Sijoitus.JÄTKÄ)) {
                jätkät++;
            }
            if (pakka.pakka.get(i).getSijoitus().equals(Sijoitus.AKKA)) {
                akat++;
            }
            if (pakka.pakka.get(i).getSijoitus().equals(Sijoitus.KURKO)) {
                kurkot++;
            }
            if (pakka.pakka.get(i).getSijoitus().equals(Sijoitus.ÄSSÄ)) {
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

    @Test
    public void getPakkaToimii() {
        Pakka pakka = new Pakka();
        pakka.luoPakka();
        Kortti kortti = pakka.getPakka().get(5);
        assertEquals(kortti, pakka.getPakka().get(5));
    }

}
