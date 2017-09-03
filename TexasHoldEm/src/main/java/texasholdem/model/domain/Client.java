package texasholdem.model.domain;

import texasholdem.poyta.PoytaTyyppi;
import java.util.List;
import java.util.Set;

import texasholdem.actions.Action;

/**
 * Nayttaa poydan tietoa ja toimii pelaajan puolesta.
 *
 */
public interface Client {

    /**
     * Pelin viesti.
     *
     * @param message The message.
     */
    void messageReceived(String message);

    /**
     * Hoitaa pelaajan liittymisen pöytään.
     *
     * @param tyyppi
     *
     * @param bigBlind
     *
     * @param pelaajat
     *
     */
    void joinedTable(PoytaTyyppi tyyppi, int bigBlind, List<Pelaaja> pelaajat);

    /**
     * Hoitaa uuden kaden startin.
     *
     * @param tyyppi
     * @param bigBlind
     * @param pelaajat
     * @param dealer
     *
     */
    void handStarted(Pelaaja dealer);

    /**
     * Hoitaa kenen vuoro on.
     *
     * @param actor The new actor.
     */
    void actorRotated(Pelaaja actor);

    /**
     * Hoitaa pelaajan updaten.
     *
     * @param pelaaja pelaaja
     */
    void playerUpdated(Pelaaja pelaaja);

    /**
     * Hoitaa alustan updaten.
     *
     * @param kortit kortit
     * @param bet panos
     * @param pot potti
     *
     */
    void alustaUpdated(List<Kortti> kortit, int bet, int pot);

    /**
     * Hoitaa pelaajan toiminnan.
     *
     * @param pelaaja pelaaja joka toimii
     */
    void playerActed(Pelaaja pelaaja);

    /**
     * Aktio.
     */
    
    Action act(int minBet, int currentBet, Set<Action> allowedActions);

}
