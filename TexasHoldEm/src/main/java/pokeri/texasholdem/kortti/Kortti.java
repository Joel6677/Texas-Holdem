/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokeri.texasholdem.kortti;

public class Kortti {

    public enum Arvo {
        DEUCE, THREE, FOUR, FIVE, SIX,
        SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
    }

    public enum Maa {
        CLUBS, DIAMONDS, HEARTS, SPADES
    }

    public Arvo arvo;
    public Maa maa;

    public Kortti(Arvo arvo, Maa maa) {
        this.arvo = arvo;
        this.maa = maa;
    }

    public Arvo arvo() {
        return arvo;
    }

    public Maa maa() {
        return maa;
    }

    public String toString() {
        return arvo + " of " + maa;
    }

    public Arvo getArvo() {
        return arvo;
    }

    public Maa getMaa() {
        return maa;
    }

    public void setArvo(Arvo arvo) {
        this.arvo = arvo;
    }

    public void setMaa(Maa maa) {
        this.maa = maa;
    }

}
