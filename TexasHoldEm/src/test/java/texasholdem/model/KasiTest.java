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
}
