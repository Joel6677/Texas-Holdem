package texasholdem.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import texasholdem.model.Kortti;

public class Kasi {

    private Kortti[] kasi = new Kortti[2];
    public KasiSijoitus kasiSijoitus;
    public List<KasiSijoitus> lista = new ArrayList<KasiSijoitus>();

    public enum KasiSijoitus {
        Kuningasvarisuora,
        Varisuora,
        Neloset,
        Tayskasi,
        Vari,
        Suora,
        Kolmoset,
        Kaksi_paria,
        Pari,
        Hai;
    }

    public Kasi() {
    }

    public Kasi(Kortti[] kasi) {
        this.kasi = kasi;
    }

    public Kortti[] getKortti() {
        return this.kasi;
    }

    public void setKasi(Kortti[] kasi) {
        this.kasi = kasi;
    }

    public void printtaaKasi() {
        for (Kortti k : kasi) {
            System.out.println(k);
        }
    }

    public KasiSijoitus kasiSijoitus() {
        return kasiSijoitus;
    }

    public void lisaaKadet() {
        for (KasiSijoitus kasi : KasiSijoitus.values()) {
            lista.add(kasi);
        }
    }

    public ArrayList<KasiSijoitus> getLista() {
        return new ArrayList<KasiSijoitus>(lista); // Return copy of prototype deck
    }

    public void printKasiSijoitus() {
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
        }
    }

    public KasiSijoitus katsoKasienSijoitus(Kortti[] flop) {
        boolean testi = false;
        testi = onKuningasvarisuora(flop);
        if (onKuningasvarisuora(flop)) {
            return KasiSijoitus.Kuningasvarisuora;
        } else if (onVarisuora(flop)) {
            return KasiSijoitus.Varisuora;
        } else if (onNeloset(flop)) {
            return KasiSijoitus.Neloset;
        } else if (onTayskasi(flop)) {
            return KasiSijoitus.Tayskasi;
        } else if (onVari(flop)) {
            return KasiSijoitus.Vari;
        } else if (onSuora(flop)) {
            return KasiSijoitus.Suora;
        } else if (onKolmoset(flop)) {
            return KasiSijoitus.Kolmoset;
        } else if (onKaksiParia(flop)) {
            return KasiSijoitus.Kaksi_paria;
        } else if (onPari(flop)) {
            return KasiSijoitus.Pari;
        } else {
            return KasiSijoitus.Hai;
        }

    }

    public boolean onKuningasvarisuora(Kortti[] flop) {
        Kortti[] kaikkiKortit = Stream.concat(Arrays.stream(flop), Arrays.stream(kasi))
                .toArray(Kortti[]::new);
        boolean onAssa = false, onKurko = false, onAkka = false, onJatka = false, onKymppi = false;
        if (onVari(flop)) {

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

    public boolean onSuora(Kortti[] flop) {
        Kortti[] kaikkiKortit = Stream.concat(Arrays.stream(flop), Arrays.stream(kasi))
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

    public boolean onVari(Kortti[] flop) {
        Kortti[] kaikkiKortit = Stream.concat(Arrays.stream(flop), Arrays.stream(kasi)) // yhdistää käden floppiin
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

    public boolean onKolmoset(Kortti[] flop) {
        Kortti[] kaikkiKortit = Stream.concat(Arrays.stream(flop), Arrays.stream(kasi))
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

    public boolean onKaksiParia(Kortti[] flop) {
        Kortti[] kaikkiKortit = Stream.concat(Arrays.stream(flop), Arrays.stream(kasi))
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

    public boolean onPari(Kortti[] flop) {
        Kortti[] allCards = Stream.concat(Arrays.stream(flop), Arrays.stream(kasi))
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
    public Comparator<Kortti> sijoituksenMukaan = (Kortti left, Kortti right) -> {
        if (left.getSijoitus().getArvo() < right.getSijoitus().getArvo()) {
            return -1;
        } else {
            return 1;
        }
    };

    public boolean onTayskasi(Kortti[] flop) {
        Kortti[] kaikkiKortit = Stream.concat(Arrays.stream(flop), Arrays.stream(kasi))
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

    public boolean onNeloset(Kortti[] flop) {
        Kortti[] kaikkiKortit = Stream.concat(Arrays.stream(flop), Arrays.stream(kasi))
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

    public boolean onVarisuora(Kortti[] flop) {
        return onVari(flop) && onSuora(flop);
    }

    public Kortti getHaiKaikistaKorteista(Kortti[] flop) {
        Kortti[] allCards = Stream.concat(Arrays.stream(flop), Arrays.stream(kasi))
                .toArray(Kortti[]::new);
        Arrays.sort(allCards, sijoituksenMukaan);
        return allCards[0];
    }

    public Kortti getHaiKadesta() {
        Arrays.sort(kasi, sijoituksenMukaan);
        return kasi[0];
    }
}
