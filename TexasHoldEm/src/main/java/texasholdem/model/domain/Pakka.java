package texasholdem.model.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import texasholdem.model.domain.Kortti;
import texasholdem.model.domain.Kortti.Maa;
import texasholdem.model.domain.Kortti.Sijoitus;

public class Pakka {

    public final List<Kortti> pakka = new ArrayList<Kortti>();
//    private Map<Maa, Map<Sijoitus, Kortti>> table
//            = new EnumMap<Maa, Map<Sijoitus, Kortti>>(Maa.class);

    public Pakka() {
        
        this.luoPakka();
        
    }

    private void luoPakka() {
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
        return new ArrayList<Kortti>(pakka); // Return copy of prototype deck
    }

    public void sekoitaPakka() {
        Collections.shuffle(pakka);
    }

//    public Kortti valueOf(Arvo arvo, Maa maa) {
//        return table.get(maa).get(arvo);
//    }
    public void printPakka() {
        for (int i = 0; i < pakka.size(); i++) {
            System.out.println(pakka.get(i));
        }
    }

}