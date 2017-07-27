/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texasholdem.card;

/**
 *
 * @author hejoel
 */
public class Card {

    public boolean arvonLaitto(int arvo) {
        if (arvo >= 1 && arvo <= 14) {
            return true;
        } else {
            return false;
        }
    }
    
    public static void main (String[] args) {
        
    }

}
