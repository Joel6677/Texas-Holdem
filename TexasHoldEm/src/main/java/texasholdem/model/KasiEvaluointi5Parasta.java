package texasholdem.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;
import texasholdem.model.Kortti;

public class KasiEvaluointi5Parasta {

    public boolean onKuningasvarisuora(Kortti[] flop, Kortti[] pelaajanKasi) {
        Kortti[] kaikkiKortit = Stream.concat(Arrays.stream(flop), Arrays.stream(pelaajanKasi))
                .toArray(Kortti[]::new);
        boolean onAssa = false, onKurko = false, onAkka = false, onJatka = false, onKymppi = false;
        if (onVari(flop, pelaajanKasi)) {

            for (Kortti k : kaikkiKortit) {
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

    public boolean onVarisuora(Kortti[] flop, Kortti[] pelaajanKasi) {
        KasiEvaluointi5Huonointa kasi = new KasiEvaluointi5Huonointa();
        return onVari(flop, pelaajanKasi) && kasi.onSuora(flop, pelaajanKasi);
    }

    public boolean onNeloset(Kortti[] flop, Kortti[] pelaajanKasi) {
        Kortti[] kaikkiKortit = Stream.concat(Arrays.stream(flop), Arrays.stream(pelaajanKasi))
                .toArray(Kortti[]::new);
        Arrays.sort(kaikkiKortit, sijoituksenMukaan);
        int kortinToistot = 0;
        boolean onNeloset = false;
        int i = 0;
        int k = i + 1;
        int kortti1 = 0;
        int kortti2 = 0;
        while (i < kaikkiKortit.length) {
            k = i + 1;
            if (k >= 6) {
                break;
            }
            kortti1 = kaikkiKortit[i].getSijoitus().getArvo();
            kortti2 = kaikkiKortit[k].getSijoitus().getArvo();
            kortinToistot = 0;
            while (k < kaikkiKortit.length) {
                kortti1 = kaikkiKortit[i].getSijoitus().getArvo();
                kortti2 = kaikkiKortit[k].getSijoitus().getArvo();
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

    public boolean onTayskasi(Kortti[] flop, Kortti[] pelaajanKasi) {
        Kortti[] kaikkiKortit = Stream.concat(Arrays.stream(flop), Arrays.stream(pelaajanKasi))
                .toArray(Kortti[]::new);
        Arrays.sort(kaikkiKortit, sijoituksenMukaan);
        int toistojenMaara = 1;
        int kolmosia = 0;
        boolean onKolmoset = false;
        boolean onPari = false;
        int kortti1 = 0;
        int kortti2 = 0;
        for (int i = 0; i < kaikkiKortit.length - 1; i++) {
            kortti1 = kaikkiKortit[i].getSijoitus().getArvo();
            kortti2 = kaikkiKortit[i + 1].getSijoitus().getArvo();
            if (kaikkiKortit[i].getSijoitus().getArvo() == kaikkiKortit[i + 1].getSijoitus().getArvo()) {
                toistojenMaara++;
                if (toistojenMaara == 3) {
                    onKolmoset = true;
                    kolmosia++;
                    toistojenMaara = 1;
                } else if (kaikkiKortit[i + 1].getSijoitus().getArvo() != kaikkiKortit[i + 2].getSijoitus().getArvo() && toistojenMaara == 2) {
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

    public boolean onVari(Kortti[] flop, Kortti[] pelaajanKasi) {
        Kortti[] kaikkiKortit = Stream.concat(Arrays.stream(flop), Arrays.stream(pelaajanKasi)) // yhdistää käden floppiin
                .toArray(Kortti[]::new);
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

    public Comparator<Kortti> sijoituksenMukaan = (Kortti left, Kortti right) -> {
        if (left.getSijoitus().getArvo() < right.getSijoitus().getArvo()) {
            return -1;
        } else {
            return 1;
        }
    };

}
