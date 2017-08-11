package texasholdem.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import texasholdem.model.Kortti;

import texasholdem.model.Kortti.Maa;
import texasholdem.model.Kortti.Sijoitus;

public class Pakka {

    public final List<Kortti> pakka = new ArrayList<Kortti>();
    public Map<Maa, Map<Sijoitus, Kortti>> table
            = new EnumMap<Maa, Map<Sijoitus, Kortti>>(Maa.class);

    public Pakka() {
    }

    public void luoPakka() {
        for (Maa maa : Maa.values()) {
            for (Sijoitus sijoitus : Sijoitus.values()) {
                pakka.add(new Kortti(sijoitus, maa));
            }
        }
    }

//    public void luoTaulu() {
//        for (Maa maa : Maa.values()) {
//            Map<Sijoitus, Kortti> suitTable = new EnumMap<Sijoitus, Kortti>(Sijoitus.class);
//            for (Sijoitus sijoitus : Sijoitus.values()) {
//                suitTable.put(sijoitus, new Kortti(sijoitus, maa));
//            }
//            table.put(maa, suitTable);
//        }
//    }
    public ArrayList<Kortti> getPakka() {

        for (Kortti kortti : pakka) {
            if (kortti == null) {
                throw new IllegalArgumentException();
            }
        }
        return new ArrayList<Kortti>(pakka);
    }

    public void sekoitaPakka() {
        Collections.shuffle(pakka);
    }

//    public Kortti valueOf(Arvo arvo, Maa maa) {
//        return table.get(maa).get(arvo);
//    }
//    public void printPakka() {
//        for (int i = 0; i < pakka.size(); i++) {
//            System.out.println(pakka.get(i));
//        }
//    }
}
