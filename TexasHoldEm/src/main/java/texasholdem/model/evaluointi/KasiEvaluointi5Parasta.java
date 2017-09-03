package texasholdem.model.evaluointi;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;
import texasholdem.model.domain.Kasi.KasiSijoitus;
import texasholdem.model.domain.Kortti;
import texasholdem.model.domain.Kortti;
import texasholdem.model.evaluointi.KasiEvaluointi5Huonointa;
import texasholdem.model.domain.Kortti.Sijoitus;

/**
 * Luokka evaluoi 5 parasta kättä.
 */
public class KasiEvaluointi5Parasta {

    private Kortti[] kaikkiKortit;

    /**
     * Metodille annetaan pöydän kortit sekä pelaajan käsi. Metodi palauttaa
     * parhaan käden mitä näiden korttien yhdistelmästä saa, jos käsi on viiden
     * parhaan joukossa
     *
     * @param poydanKortit pöydän kortit (5 kpl)
     * @param pelaajanKasi pelaajan kahden kortin käsi
     * @return paras käsi jonka pelaajan korteilla saa yhdistettynä pöydän
     * kortteihin
     *
     */
    public KasiSijoitus getParasKasi(Kortti[] poydanKortit, Kortti[] pelaajanKasi) {

        KasiEvaluointi5Huonointa huonot = new KasiEvaluointi5Huonointa();

        if (onKuningasvarisuora(poydanKortit, pelaajanKasi)) {
            return KasiSijoitus.Kuningasvarisuora;
        } else if (onVarisuora(poydanKortit, pelaajanKasi)) {
            return KasiSijoitus.Värisuora;
        } else if (onNeloset(poydanKortit, pelaajanKasi)) {
            return KasiSijoitus.Neloset;
        } else if (onTayskasi(poydanKortit, pelaajanKasi)) {
            return KasiSijoitus.Täyskäsi;
        } else if (onVari(poydanKortit, pelaajanKasi)) {
            return KasiSijoitus.Väri;
        } else {
            return huonot.getHuonoKasi(poydanKortit, pelaajanKasi);
        }
    }

    private Kortti[] kaikkiKortit(Kortti[] poydanKortit, Kortti[] pelaajanKasi) {
        kaikkiKortit = Stream.concat(Arrays.stream(poydanKortit), Arrays.stream(pelaajanKasi))
                .toArray(Kortti[]::new);

        return kaikkiKortit;
    }

    private boolean onKuningasvarisuora(Kortti[] poydanKortit, Kortti[] pelaajanKasi) {
        boolean onAssa = false, onKurko = false, onAkka = false, onJatka = false, onKymppi = false;
        if (onVari(poydanKortit, pelaajanKasi)) {
            for (Kortti k : kaikkiKortit(poydanKortit, pelaajanKasi)) {
                switch (k.getSijoitus()) {
                    case ÄSSÄ:
                        onAssa = true;
                        break;
                    case KURKO:
                        onKurko = true;
                        break;
                    case AKKA:
                        onAkka = true;
                        break;
                    case JÄTKÄ:
                        onJatka = true;
                        break;
                    case KYMPPI:
                        onKymppi = true;
                        break;
                }
            }
            return (onAssa && onKurko && onAkka && onJatka && onKymppi);
        } else {
            return false;
        }
    }

    private void jarjestaKaikkiKortit() {
        Arrays.sort(kaikkiKortit, sijoituksenMukaan);
    }

    private boolean onVarisuora(Kortti[] poydanKortit, Kortti[] pelaajanKasi) {
        KasiEvaluointi5Huonointa kasi = new KasiEvaluointi5Huonointa();
        return onVari(poydanKortit, pelaajanKasi) && kasi.onSuora(poydanKortit, pelaajanKasi);
    }

    private boolean onNeloset(Kortti[] poydanKortit, Kortti[] pelaajanKasi) {
        kaikkiKortit(poydanKortit, pelaajanKasi);
        jarjestaKaikkiKortit();
        int kortinToistot = 0;
        boolean onNeloset = false;
        int i = 0;
        int k = i + 1;
        while (i < kaikkiKortit.length) {
            k = i + 1;
            if (k >= 6) {
                break;
            }
            kortinToistot = 0;
            while (k < kaikkiKortit.length) {
                if (kaikkiKortit[i].getSijoitus().getArvo() == kaikkiKortit[k].getSijoitus().getArvo()) {
                    kortinToistot++;
                    if (kortinToistot == 3) {
                        return true;
                    }
                }
                k++;
            }
            i++;
        }
        return false;
    }

    private boolean onTayskasi(Kortti[] poydanKortit, Kortti[] pelaajanKasi) {
        kaikkiKortit(poydanKortit, pelaajanKasi);
        jarjestaKaikkiKortit();
        int toistojenMaara = 1;
        int kolmosia = 0;
        boolean onKolmoset = false;
        boolean onPari = false;

        Sijoitus kortti1;
        Sijoitus kortti2;
        Sijoitus kortti3;

        for (int i = 0; i < kaikkiKortit.length - 1; i++) {
            kortti1 = kaikkiKortit[i].getSijoitus();
            kortti2 = kaikkiKortit[i + 1].getSijoitus();

            if (kaikkiKortit[i].getSijoitus().getArvo() == kaikkiKortit[i + 1].getSijoitus().getArvo()) {
                toistojenMaara++;
                if (toistojenMaara == 3) {
                    onKolmoset = true;
                    kolmosia++;
                    toistojenMaara = 1;
                } else if (i < 5 && kaikkiKortit[i + 1].getSijoitus().getArvo() != kaikkiKortit[i + 2].getSijoitus().getArvo() && toistojenMaara == 2) {
                    onPari = true;
                    toistojenMaara = 1;
                }
            } else {
                toistojenMaara = 1;
            }
        }
        if (kolmosia >= 2) {
            return true;
        }
        return (onPari && onKolmoset);

    }

    private boolean onVari(Kortti[] poydanKortit, Kortti[] pelaajanKasi) {
        kaikkiKortit(poydanKortit, pelaajanKasi);
        int hertat = 0;
        int padat = 0;
        int ristit = 0;
        int ruudut = 0;
        for (Kortti k : kaikkiKortit) {
            switch (k.getMaa()) {
                case HERTTA:
                    hertat++;
                    break;
                case PATA:
                    padat++;
                    break;
                case RISTI:
                    ristit++;
                    break;
                case RUUTU:
                    ruudut++;
                    break;
            }
        }
        return (hertat >= 5 || padat >= 5 || ristit >= 5 || ruudut >= 5);
    }

    private Comparator<Kortti> sijoituksenMukaan = (Kortti left, Kortti right) -> {
        if (left.getSijoitus().getArvo() < right.getSijoitus().getArvo()) {
            return -1;
        } else {
            return 1;
        }
    };

    /**
     * Get hai kaikista korteista.
     *
     * @param poydanKortit poydan kortit
     * @param pelaajanKasi pelaajan kortit
     * @return onko käsi hai
     */
    public Kortti getHaiKaikistaKorteista(Kortti[] poydanKortit, Kortti[] pelaajanKasi) {
        kaikkiKortit(poydanKortit, pelaajanKasi);
        jarjestaKaikkiKortit();
        return kaikkiKortit[kaikkiKortit.length - 1];
    }

}
