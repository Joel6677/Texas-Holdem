package texasholdem.model.domain;

import texasholdem.model.domain.Client;
import texasholdem.actions.Action;
import java.util.List;
import texasholdem.model.domain.Kasi.KasiSijoitus;
import texasholdem.model.domain.Kortti;

/**
 * Luokassa pelaajan metodeita.
 */
public class Pelaaja {

    private Kortti[] pelaajanKortit = new Kortti[2];
    private final String nimi;
    private final Client client;
    private int rahat;
    private boolean onKortit;
    private int bet;
    private Action action;
    private final Kasi kasi;

    /**
     * Konstruktori.
     *
     * @param nimi nimi
     * @param rahat rahat
     * @param client client
     *
     */
    public Pelaaja(String nimi, int rahat, Client client) {
        this.nimi = nimi;
        this.rahat = rahat;
        this.client = client;
        this.kasi = new Kasi();
        resetHand();
    }

    /**
     * Konstruktori.
     *
     * @param nimi nimi
     * @param rahat rahat
     * @param client client
     * @param kortti1 kortti1
     * @param kortti2 kortti2
     */
    public Pelaaja(Client client, String nimi, int rahat, Kortti kortti1, Kortti kortti2) {
        pelaajanKortit[0] = kortti1;
        pelaajanKortit[1] = kortti2;

        this.nimi = nimi;
        this.rahat = rahat;
        this.client = client;

        this.kasi = new Kasi();

        resetHand();
    }

    /**
     * Aseta pelaajan kortti.
     *
     * @param kortti kortti
     * @param kortinNumero kortin numero
     *
     */
    public void setKortti(Kortti kortti, int kortinNumero) {
        pelaajanKortit[kortinNumero] = kortti;
    }

    /**
     * Get rahat.
     *
     * @return rahat
     *
     */
    public int getRahat() {
        return this.rahat;
    }

    /**
     * Get kortti.
     *
     * @param kortinNumero kortin numero
     * @return pelaajan kortti
     *
     */
    public Kortti getKortti(int kortinNumero) {
        return pelaajanKortit[kortinNumero];
    }

    /**
     * Get kasi.
     *
     * @return pelaajan kortit
     *
     */
    public Kortti[] getKasi() {
        return pelaajanKortit;
    }

    /**
     * Get client.
     *
     * @return client
     *
     */
    public Client getClient() {
        return client;
    }

    /**
     * Resetoi käsi.
     *
     */
    public void resetHand() {
        onKortit = false;
        kasi.removeAllCards();
        resetBet();
    }

    /**
     * Resetoi panos.
     *
     */
    public void resetBet() {
        bet = 0;
        action = (onKortit && rahat == 0) ? Action.ALL_IN : null;
    }

    /**
     * Aseta kortit.
     *
     * @param kortit kortit
     */
    public void setCards(List<Kortti> kortit) {
        kasi.removeAllCards();
        if (kortit != null) {
            if (kortit.size() == 2) {
                kasi.addCards(kortit);
                kasi.lisaaPelaajanKortti(kortit.get(0));
                kasi.lisaaPelaajanKortti(kortit.get(1));
                pelaajanKortit[0] = kasi.getPelaajanKortti(0);
                pelaajanKortit[1] = kasi.getPelaajanKortti(1);
                onKortit = true;
            } else {
                throw new IllegalArgumentException("Väärä määrä kortteja");
            }
        }
    }

    /**
     * Onko kortit.
     *
     * @return onko kortit
     */
    public boolean hasCards() {
        return onKortit;
    }

    public String getName() {
        return nimi;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    /**
     * Onko allin.
     *
     * @return onko kortit ja rahat = 0
     */
    public boolean isAllIn() {
        return hasCards() && (rahat == 0);
    }

    /**
     * Get kortit.
     *
     * @return kortit kädestä
     */
    public Kortti[] getCards() {
        return kasi.getKortit();
    }
    
    /**
     * Aseta pikku blindi.
     * @param blind 
     */

    public void postSmallBlind(int blind) {
        action = Action.PIKKU_BLIND;
        rahat -= blind;
        bet += blind;
    }
    
    /**
     * Aseta iso blindi.
     * @param blind 
     */

    public void postBigBlind(int blind) {
        action = Action.ISO_BLIND;
        rahat -= blind;
        bet += blind;
    }

    /**
     * Maksa rahaa.
     *
     * @param maara Maksettava maara
     */
    public void maksaRahhoo(int maara) {
        if (maara > rahat) {
            throw new IllegalStateException("Ei riittävästi rahaa");
        }
        rahat -= maara;
    }

    /**
     * Voittaa maaran rahaa.
     *
     * @param maara maaraa jonka voittaa
     */
    public void voita(int maara) {
        rahat += maara;
    }

    /**
     * Tee klooni.
     *
     * @return klooni
     *
     */
    public Pelaaja publicClone() {
        Pelaaja clone = new Pelaaja(nimi, rahat, null);
        clone.onKortit = onKortit;
        clone.bet = bet;
        clone.action = action;
        return clone;
    }

    /**
     * Pelaajan kortit tekstiksi.
     *
     * @return pelaajan kortit tekstinä
     *
     */
    public String toString() {
        return pelaajanKortit[0] + " ja " + pelaajanKortit[1];
    }

}
