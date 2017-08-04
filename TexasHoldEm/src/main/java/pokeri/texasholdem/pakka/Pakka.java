/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokeri.texasholdem.pakka;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import pokeri.texasholdem.kortti.Kortti;
import pokeri.texasholdem.kortti.Kortti.Arvo;
import pokeri.texasholdem.kortti.Kortti.Maa;

/**
 *
 * @author Jolle
 */
public class Pakka {

    public final List<Kortti> pakka = new ArrayList<Kortti>();
    public Map<Maa, Map<Arvo, Kortti>> table
            = new EnumMap<Maa, Map<Arvo, Kortti>>(Maa.class);

    public Pakka() {
    }

    public void luoPakka() {
        for (Maa maa : Maa.values()) {
            for (Arvo arvo : Arvo.values()) {
                pakka.add(new Kortti(arvo, maa));
            }
        }
    }

    public void luoTaulu() {
        for (Maa maa : Maa.values()) {
            Map<Arvo, Kortti> suitTable = new EnumMap<Arvo, Kortti>(Arvo.class);
            for (Arvo arvo : Arvo.values()) {
                suitTable.put(arvo, new Kortti(arvo, maa));
            }
            table.put(maa, suitTable);
        }
    }

    public ArrayList<Kortti> getPakka() {
        return new ArrayList<Kortti>(pakka); // Return copy of prototype deck
    }

    public void sekoitaPakka() {
        Collections.shuffle(pakka);
    }

    public Kortti valueOf(Arvo arvo, Maa maa) {
        return table.get(maa).get(arvo);
    }

    public void printPakka() {
        for (int i = 0; i < pakka.size(); i++) {
            System.out.println(pakka.get(i));
        }
    }

}
