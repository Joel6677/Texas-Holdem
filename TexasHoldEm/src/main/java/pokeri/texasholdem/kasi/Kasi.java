/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokeri.texasholdem.kasi;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jolle
 */
public class Kasi {

    public KasiSijoitus kasiSijoitus;
    public List<KasiSijoitus> lista = new ArrayList<KasiSijoitus>();

    public enum KasiSijoitus {
        Kuningasvärisuora,
        Värisuora,
        Neloset,
        Täyskäsi,
        Väri,
        Suora,
        Kolmoset,
        Kaksi_paria,
        Pari,
        Hai;
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

}
