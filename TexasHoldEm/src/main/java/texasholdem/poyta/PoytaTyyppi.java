/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texasholdem.poyta;

/**
 *
 * @author Jolle
 */
public enum PoytaTyyppi {
    /** Fixed-Limit Texas Hold'em. */
    FIXED_LIMIT("Fixed-Limit"),
    
    /** No-Limit Texas Hold'em. */
    NO_LIMIT("No-Limit"),
    
    ;
    
    private String nimi;
    
    /**
     * Konstruktori
     * 
     * @param nimi
     *            nayta nimi.
     */
    PoytaTyyppi(String nimi) {
        this.nimi = nimi;
    }
    
    public String getName() {
        return nimi;
    }
    
}
