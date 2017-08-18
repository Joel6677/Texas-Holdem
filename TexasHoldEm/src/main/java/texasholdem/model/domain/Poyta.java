/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texasholdem.model.domain;

import texasholdem.model.domain.Kortti;

/**
 * Luokassa poydan metodeita
 */
public class Poyta {

    private Kortti[] poyta = new Kortti[5];

    //constructor
    public Poyta() {
    }

    //methods
    public void setPoytaKortti(Kortti kortti, int kortinNumero) {
        this.poyta[kortinNumero] = kortti;
    }

    public Kortti getPoytaKortti(int cardNum) {
        return this.poyta[cardNum];
    }

    public Kortti[] getPoytaKortit() {
        return this.poyta;
    }

    public int getPoydanKoko() {
        return poyta.length;
    }

    public void printPoyta() {
        System.out.println("Pöydällä on seuraavat kortit:");
        for (int i = 0; i < poyta.length; i++) {
            System.out.println(i + 1 + ": " + getPoytaKortti(i).toString());
        }
        System.out.println("\n");
    }
}
