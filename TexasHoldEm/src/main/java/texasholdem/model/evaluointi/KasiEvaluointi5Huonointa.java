package texasholdem.model.evaluointi;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;
import texasholdem.model.domain.Kasi;
import texasholdem.model.domain.Kasi.KasiSijoitus;
import texasholdem.model.domain.Kortti;
import texasholdem.model.domain.Kortti;

/**
 * Luokassa evaluooidaan 5 huonointa kättä 10:nestä.
 */
public class KasiEvaluointi5Huonointa {

    private Kortti[] kaikkiKortit;

    /**
     * Metodille annetaan pöydän kortit sekä pelaajan käsi. Metodi palauttaa
     * parhaan käden mitä näiden korttien yhdistelmästä saa, jos käsi on 5
     * huonoimman joukossa
     *
     * @param poydanKortit pöydän kortit (5 kpl)
     * @param pelaajanKasi pelaajan kahden kortin käsi
     * @return paras käsi jonka pelaajan korteilla saa yhdistettynä pöydän
     * kortteihin
     */
    public KasiSijoitus getHuonoKasi(Kortti[] poydanKortit, Kortti[] pelaajanKasi) {

        if (onSuora(poydanKortit, pelaajanKasi)) {
            return KasiSijoitus.Suora;
        } else if (onKolmoset(poydanKortit, pelaajanKasi)) {
            return KasiSijoitus.Kolmoset;
        } else if (onKaksiParia(poydanKortit, pelaajanKasi)) {
            return KasiSijoitus.Kaksi_paria;
        } else if (onPari(poydanKortit, pelaajanKasi)) {
            return KasiSijoitus.Pari;
        } else {
            return Kasi.KasiSijoitus.Hai;
        }
    }

    private Kortti[] kaikkiKortit(Kortti[] poydanKortit, Kortti[] pelaajanKasi) {
        kaikkiKortit = Stream.concat(Arrays.stream(poydanKortit), Arrays.stream(pelaajanKasi))
                .toArray(Kortti[]::new);

        return kaikkiKortit;
    }

    private void jarjestaKaikkiKortit() {
        Arrays.sort(kaikkiKortit, sijoituksenMukaan);
    }

    /**
     * Onko käsi suora.
     *
     * @param poydanKortit pöydän kortit
     * @param pelaajanKasi pelaajan käsi
     * @return onko suora
     */
    public boolean onSuora(Kortti[] poydanKortit, Kortti[] pelaajanKasi) {

        kaikkiKortit(poydanKortit, pelaajanKasi);
        jarjestaKaikkiKortit();
        int korttejaJarjestyksessa = 0;
        int pos = 0;
        int kortti1 = 0;
        int kortti2 = 0;
        boolean onSuora = false;
        while (pos < kaikkiKortit.length - 1 && !onSuora) {
            kortti1 = kaikkiKortit[pos].getSijoitus().getArvo();
            kortti2 = kaikkiKortit[pos + 1].getSijoitus().getArvo();
            if (kortti2 - kortti1 == 1) {
                korttejaJarjestyksessa++;
                pos++;
                if (korttejaJarjestyksessa == 4) {
                    onSuora = true;
                }
            } else if (kortti1 == kortti2) {
                pos++;
            } else {
                korttejaJarjestyksessa = 0;
                pos++;
            }
        }
        return onSuora;
    }

    private boolean onKolmoset(Kortti[] poydanKortit, Kortti[] pelaajanKasi) {
        kaikkiKortit(poydanKortit, pelaajanKasi);
        jarjestaKaikkiKortit();
        int samojaKortteja = 1;
        int i = 0;
        while (i < kaikkiKortit.length) {
            int k = i + 1;
            samojaKortteja = 1;
            while (k < kaikkiKortit.length) {
                if (kaikkiKortit[i].getSijoitus().getArvo() == kaikkiKortit[k].getSijoitus().getArvo()) {
                    samojaKortteja++;
                    if (samojaKortteja == 3) {
                        return true;
                    }
                }
                k++;
            }
            k = 0;
            i++;
        }
        return false;
    }

    private boolean onKaksiParia(Kortti[] poydanKortit, Kortti[] pelaajanKasi) {
        kaikkiKortit(poydanKortit, pelaajanKasi);
        jarjestaKaikkiKortit();
        int samojaKortteja = 1;
        int samojenKorttejenMaara = 0;
        boolean onKaksiParia = false;
        int i = 0;
        int k = 0;
        int kortti1 = 0;
        int kortti2 = 0;
        while (i < kaikkiKortit.length && !onKaksiParia) {
            k = i + 1;
            samojaKortteja = 1;
            while (k < kaikkiKortit.length && !onKaksiParia) {
                kortti1 = kaikkiKortit[i].getSijoitus().getArvo();
                kortti2 = kaikkiKortit[k].getSijoitus().getArvo();
                if (kaikkiKortit[i].getSijoitus().getArvo() == kaikkiKortit[k].getSijoitus().getArvo()) {
                    samojaKortteja++;
                    if (samojaKortteja == 2) {
                        samojaKortteja = 1;
                        samojenKorttejenMaara++;
                        if (samojenKorttejenMaara == 2) {
                            onKaksiParia = true;

                        }
                    }

                }
                k++;
            }
            k = 0;
            i++;
        }
        return onKaksiParia;
    }

    private boolean onPari(Kortti[] poydanKortit, Kortti[] pelaajanKasi) {
        kaikkiKortit(poydanKortit, pelaajanKasi);
        jarjestaKaikkiKortit();
        int cardRepeats = 1;
        boolean onPari = false;
        int i = 0;

        while (i < kaikkiKortit.length && !onPari) {
            int k = i + 1;
            cardRepeats = 1;
            while (k < kaikkiKortit.length && !onPari) {
                if (kaikkiKortit[i].getSijoitus().getArvo() == kaikkiKortit[k].getSijoitus().getArvo()) {
                    cardRepeats++;
                    if (cardRepeats == 2) {
                        onPari = true;
                    }
                }
                k++;
            }
            k = 0;
            i++;
        }
        return onPari;
    }

    private Comparator<Kortti> sijoituksenMukaan = (Kortti left, Kortti right) -> {
        if (left.getSijoitus().getArvo() < right.getSijoitus().getArvo()) {
            return -1;
        } else {
            return 1;
        }
    };

}
