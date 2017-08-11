package texasholdem.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class KasiEvaluointi5Huonointa {

    public boolean onSuora(Kortti[] flop, Kortti[] pelaajanKasi) {
        Kortti[] kaikkiKortit = Stream.concat(Arrays.stream(flop), Arrays.stream(pelaajanKasi))
                .toArray(Kortti[]::new);
        Arrays.sort(kaikkiKortit, sijoituksenMukaan);
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

    public boolean onKolmoset(Kortti[] flop, Kortti[] pelaajanKasi) {
        Kortti[] kaikkiKortit = Stream.concat(Arrays.stream(flop), Arrays.stream(pelaajanKasi))
                .toArray(Kortti[]::new);
        int samojaKortteja = 1;
        int i = 0;
        int k = i + 1;
        while (i < kaikkiKortit.length) {
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
            i++;
        }
        return false;
    }

    public boolean onKaksiParia(Kortti[] flop, Kortti[] pelaajanKasi) {
        Kortti[] kaikkiKortit = Stream.concat(Arrays.stream(flop), Arrays.stream(pelaajanKasi))
                .toArray(Kortti[]::new);
        int samojaKortteja = 1;
        int samojenKorttejenMaara = 0;
        boolean onKaksiParia = false;
        int i = 0;
        int k = i + 1;
        while (i < kaikkiKortit.length && !onKaksiParia) {
            samojaKortteja = 1;
            while (k < kaikkiKortit.length && !onKaksiParia) {
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
            i++;
        }
        return onKaksiParia;
    }

    public boolean onPari(Kortti[] flop, Kortti[] pelaajanKasi) {
        Kortti[] allCards = Stream.concat(Arrays.stream(flop), Arrays.stream(pelaajanKasi))
                .toArray(Kortti[]::new);
        int cardRepeats = 1;
        boolean isPair = false;
        int i = 0;
        int k = i + 1;
        while (i < allCards.length && !isPair) {
            cardRepeats = 1;
            while (k < allCards.length && !isPair) {
                if (allCards[i].getSijoitus().getArvo() == allCards[k].getSijoitus().getArvo()) {
                    cardRepeats++;
                    if (cardRepeats == 2) {
                        isPair = true;
                    }
                }
                k++;
            }
            i++;
        }
        return isPair;
    }

    public Kortti getHaiKaikistaKorteista(Kortti[] flop, Kortti[] pelaajanKasi) {
        Kortti[] allCards = Stream.concat(Arrays.stream(flop), Arrays.stream(pelaajanKasi))
                .toArray(Kortti[]::new);
        Arrays.sort(allCards, sijoituksenMukaan);
        return allCards[0];
    }

    public Comparator<Kortti> sijoituksenMukaan = (Kortti left, Kortti right) -> {
        if (left.getSijoitus().getArvo() < right.getSijoitus().getArvo()) {
            return -1;
        } else {
            return 1;
        }
    };

}
