package texasholdem.model.texasholdem;

import texasholdem.model.evaluointi.VoittoEvaluointi;
import texasholdem.model.evaluointi.KasiEvaluointi5Parasta;
import texasholdem.model.domain.Kasi.KasiSijoitus;
import texasholdem.model.domain.Kortti;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import texasholdem.model.domain.Jakaja;
import texasholdem.model.domain.Pakka;
import texasholdem.model.domain.Pelaaja;
import texasholdem.model.domain.Poyta;

/**
 * Luokassa peli tekstimuodossa.
 */


public class TexasHoldEm {

    public static void main(String[] args) throws Exception {
        // variables
        Pakka pakka = new Pakka();
        Jakaja jakaja = new Jakaja();
        int korttiLaskuri = 0;
        int poytaLaskuri = 0;
        int panos = 0;
        int pelaajanKadenArvo = 0;
        int jakajanKadenArvo = 0;
        Pelaaja pelaaja = new Pelaaja();
        int rahat = 0;

        KasiEvaluointi5Parasta kadenEvaluointi = new KasiEvaluointi5Parasta();
        VoittoEvaluointi voittoEvaluointi = new VoittoEvaluointi();
        Scanner lukija = new Scanner(System.in);

        System.out.println("Aseta pelaajan rahat: ");
        try {
            rahat = Integer.parseInt(lukija.nextLine());
//            rahat = 100;
            pelaaja.setRahat(rahat);
        } catch (NumberFormatException e) {
            System.out.println("Aseta numeroita!!! ");
        }

        System.out.println("Pelaajan rahat: " + pelaaja.getRahat());
        rahat = pelaaja.getRahat();
        while (true) {

            Poyta poyta = new Poyta();
            pelaaja = new Pelaaja();
            jakaja = new Jakaja();

            if (!(rahat > 0)) {
                System.out.println("Rahat loppuivat! Kiitos Pelaamisesta!");
                break;
            }

            pakka.sekoitaPakka();
            System.out.println("Aseta panos: ");
            try {
                panos = Integer.parseInt(lukija.nextLine());
                if (rahat - panos < 0) {
                    System.out.println("Ei ole varaa panostaa");
                    continue;
                }
//                panos = 20;
                rahat -= panos;
                System.out.println("Pelaajan rahat: " + rahat);
            } catch (NumberFormatException e) {
                System.out.println("Aseta numero!!! ");
                continue;
            }

            for (int i = 0; i < 2; i++) {
                pelaaja.setKortti(pakka.getPakka().get(korttiLaskuri++), i);
            }

            // deal flop
            for (int i = 0; i < 3; i++) {
                poyta.setPoytaKortti(pakka.getPakka().get(korttiLaskuri++), poytaLaskuri++);
            }

            System.out.println("Pelaajan kortit: ");
            System.out.println(pelaaja.toString());

            System.out.println("Mitä teet: (call),(fold) tai (lopeta)");
            String teko = lukija.nextLine();
//            String teko = "fold";

            if (teko.equals("fold")) {
                korttiLaskuri = 0;
                poytaLaskuri = 0;
                panos = 0;
                continue;
                // ylös alkuun
            } else if (teko.equals("lopeta")) {
                break;
            } else if (!(teko.equals("fold") || teko.equals("lopeta") || teko.equals("call"))) {
                System.out.println("Error");
                korttiLaskuri = 0;

                panos = 0;
                pelaaja.setRahat(100);
                continue;
            }

            for (int i = 0; i < 2; i++) {
                jakaja.setKortti(pakka.getPakka().get(korttiLaskuri++), i);
            }

            while (teko.equals("call")) {
                //turn
                System.out.println("Jakajan kortit: ");
                System.out.println(jakaja.toString());
                
                poyta.setPoytaKortti(pakka.getPakka().get(korttiLaskuri++), poytaLaskuri++);

                //river
                poyta.setPoytaKortti(pakka.getPakka().get(korttiLaskuri++), poytaLaskuri++);

                System.out.println("Kortit jaettu\n");

                poyta.printPoyta();

                System.out.println("Pelaajan käsi: ");

                System.out.println(kadenEvaluointi.getParasKasi(poyta.getPoytaKortit(), pelaaja.getKasi()));
                pelaajanKadenArvo = kadenEvaluointi.getParasKasi(poyta.getPoytaKortit(), pelaaja.getKasi()).getArvo();
                System.out.println("Jakajan käsi: ");
                jakajanKadenArvo = kadenEvaluointi.getParasKasi(poyta.getPoytaKortit(), jakaja.getKasi()).getArvo();
                System.out.println(kadenEvaluointi.getParasKasi(poyta.getPoytaKortit(), jakaja.getKasi()));

                if (pelaajanKadenArvo > jakajanKadenArvo) {
                    voittoEvaluointi.setVoitto(kadenEvaluointi.getParasKasi(poyta.getPoytaKortit(), pelaaja.getKasi()), panos);
                    System.out.println("Voitto: " + voittoEvaluointi.getVoitto());
                    rahat += voittoEvaluointi.getVoitto();
                } else if (pelaajanKadenArvo == jakajanKadenArvo) {
                    System.out.println("Tasapeli");
                    rahat += panos;
                } else {
                    System.out.println("Ei voittoa");
                }
                poytaLaskuri = 0;
                korttiLaskuri = 0;
                break;

            }
        }
    }
}
