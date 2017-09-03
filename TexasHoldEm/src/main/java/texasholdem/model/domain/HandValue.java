/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texasholdem.model.domain;

import texasholdem.model.domain.Kasi.KasiSijoitus;
import texasholdem.model.evaluointi.KasiEvaluointi5Huonointa;
import texasholdem.model.evaluointi.KasiEvaluointi5Parasta;

/**
 * Luokassa määritellään käden arvo.
 *
 */
public class HandValue {

    private KasiSijoitus sijoitus;
    private KasiEvaluointi5Parasta evaluator = new KasiEvaluointi5Parasta();
    private int arvo;

    /**
     * Konstruktori.
     *
     */
    public HandValue() {

    }

    /**
     * Konstruktori.
     *
     * @param poydanKortit kortit poydalla
     * @param pelaajanKasi pelaajan kortit
     */
    public HandValue(Kortti[] poydanKortit, Kortti[] pelaajanKasi) {
        sijoitus = evaluator.getParasKasi(poydanKortit, pelaajanKasi);
        arvo = sijoitus.getArvo();

    }

    /**
     * Antaa parhaan käden pöydän sekä pelaajan korttien yhdistelmästä.
     *
     * @param poydanKortit poydan kortit
     * @param pelaajanKasi pelaajan kortit
     * @sijoitus määritetään paras käsi
     * @return paras käsi
     *
     */
    public KasiSijoitus getParasKasi(Kortti[] poydanKortit, Kortti[] pelaajanKasi) {
        this.sijoitus = evaluator.getParasKasi(poydanKortit, pelaajanKasi);
        this.arvo = sijoitus.getArvo();
        return evaluator.getParasKasi(poydanKortit, pelaajanKasi);
    }

    /**
     * Palauttaa hain.
     *
     * @param poydanKortit poydan kortit
     * @param pelaajanKasi pelaajan kortit
     * @return käden hai
     *
     */
    public int getHaiArvo(Kortti[] poydanKortit, Kortti[] pelaajanKasi) {
        return evaluator.getHaiKaikistaKorteista(poydanKortit, pelaajanKasi).getSijoitus().getArvo();
    }

    public int getArvo() {
        return this.arvo;
    }

    public String getKuvaus() {
        return sijoitus.toString();
    }

    /**
     * Antaa sijoituksen ja arvon tekstimuodossa.
     * @return sijoitus ja arvo tekstimuodossa
     */
    public String toString() {
        return String.format("%s (%d)", sijoitus.toString(), arvo);
    }

}
