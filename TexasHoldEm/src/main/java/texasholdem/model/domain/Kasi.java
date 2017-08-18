package texasholdem.model.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Kasi {

    public Kortti[] kasi = new Kortti[2];
    public KasiSijoitus kasiSijoitus;
    public List<KasiSijoitus> lista = new ArrayList<KasiSijoitus>();

    public enum KasiSijoitus {
        
        Kuningasvarisuora(10),
        Värisuora(9),
        Neloset(8),
        Täyskäsi(7),
        Väri(6),
        Suora(5),
        Kolmoset(4),
        Kaksi_paria(3),
        Pari(2),
        Hai(1);
        
        private final int arvo;
        
        
        private KasiSijoitus(int arvo) {
            this.arvo = arvo;
        }

        public int getArvo() {
            return arvo;
        }

    }

    public Kasi() {

    }

    public Kasi(Kortti[] kasi) {
        this.kasi = kasi;
    }

    public Kortti[] getKasi() {

        if (this.kasi == null) {
            throw new IllegalArgumentException();
        }
        return this.kasi;

    }

    public void setKasi(Kortti[] kasi) {

        if (kasi == null) {
            throw new IllegalArgumentException();
        }

        this.kasi = kasi;
    }

//    public void printtaaKasi() {
//        for (Kortti k : kasi) {
//            System.out.println(k);
//        }
//    }
    public void setKasiSijoitus(KasiSijoitus kasiSijoitus) {
        this.kasiSijoitus = kasiSijoitus;
    }

    public KasiSijoitus getKasiSijoitus() {

        if (kasiSijoitus == null) {
            throw new IllegalArgumentException();
        }

        return kasiSijoitus;
    }

    public void lisaaKadet() {
        for (KasiSijoitus kasi : KasiSijoitus.values()) {
            lista.add(kasi);
        }
    }

//    public ArrayList<KasiSijoitus> getLista() {
//        return new ArrayList<KasiSijoitus>(lista);
//    }
//    public void printKasiSijoitus() {
//        for (int i = 0; i < lista.size(); i++) {
//            System.out.println(lista.get(i));
//        }
//    }
//    public KasiSijoitus katsoKasienSijoitus(Kortti[] flop) {
//        KasiEvaluointi5Parasta parhaatKadet = new KasiEvaluointi5Parasta();
//        KasiEvaluointi5Huonointa huonotKadet = new KasiEvaluointi5Huonointa();
//        if (parhaatKadet.onKuningasvarisuora(flop, kasi)) {
//            return KasiSijoitus.Kuningasvärisuora;
//        } else if (parhaatKadet.onVarisuora(flop, kasi)) {
//            return KasiSijoitus.Värisuora;
//        } else if (parhaatKadet.onNeloset(flop, kasi)) {
//            return KasiSijoitus.Neloset;
//        } else if (parhaatKadet.onTayskasi(flop, kasi)) {
//            return KasiSijoitus.Täyskäsi;
//        } else if (parhaatKadet.onVari(flop, kasi)) {
//            return KasiSijoitus.Väri;
//        } else if (huonotKadet.onSuora(flop, kasi)) {
//            return KasiSijoitus.Suora;
//        } else if (huonotKadet.onKolmoset(flop, kasi)) {
//            return KasiSijoitus.Kolmoset;
//        } else if (huonotKadet.onKaksiParia(flop, kasi)) {
//            return KasiSijoitus.Kaksi_paria;
//        } else if (huonotKadet.onPari(flop, kasi)) {
//            return KasiSijoitus.Pari;
//        } else {
//            return KasiSijoitus.Hai;
//        }
//
//    }
    public Kortti getHaiKadesta() {

        Arrays.sort(kasi, sijoituksenMukaan);
        return kasi[kasi.length - 1];
    }

    public String toString() {
        return kasi[0] + " ja " + kasi[1];
    }

    public Comparator<Kortti> sijoituksenMukaan = (Kortti left, Kortti right) -> {

        if (left.getSijoitus().getArvo() < right.getSijoitus().getArvo()) {
            return -1;
        } else {
            return 1;
        }
    };
}