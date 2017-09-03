
package texasholdem.poyta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import texasholdem.actions.Action;
import texasholdem.actions.BetAction;
import texasholdem.actions.RaiseAction;
import texasholdem.model.domain.HandValue;
import texasholdem.model.domain.Kasi;
import texasholdem.model.domain.Kortti;
import texasholdem.model.domain.Pakka;
import texasholdem.model.domain.Pelaaja;
import texasholdem.model.domain.Potti;
import texasholdem.model.evaluointi.KasiEvaluointi5Parasta;

/**
 * Limit Texas Hold'em poker table. <br />
 * <br />
 * 
 * This class forms the heart of the poker engine. It controls the game flow for a single poker table.
 * 
 * @author Oscar Stigter
 */
public class Poyta {
    
    private static final int MAX_KOROTUKSET = 3;
    private static final boolean AINA_KUTSU_SHOWDOWN = false;
    private final PoytaTyyppi poytaTyyppi;
    private final int bigBlind;
    private final List<Pelaaja> pelaajat;
    private final List<Pelaaja> aktiivipelaajat;
    private final Pakka pakka;
    private final List<Kortti> alusta;
    private int jakajaPositio;
    private Pelaaja jakaja;
    private int actorPositio;    
    private Pelaaja actor;
    private int minPanos;    
    private int panos;    
    private final List<Potti> potit;  
    private Pelaaja vikaPanostaja;   
    private int korotukset;
    
    /**
     * Konstruktori.
     * 
     * @param bigBlind big blindin suuruus.
     * @param tyyppi poydan tyyppi nolimit, limit
     * 
     */
    public Poyta(PoytaTyyppi tyyppi, int bigBlind) {
        this.poytaTyyppi = tyyppi;
        this.bigBlind = bigBlind;
        pelaajat = new ArrayList<Pelaaja>();
        aktiivipelaajat = new ArrayList<Pelaaja>();
        pakka = new Pakka();
        alusta = new ArrayList<Kortti>();
        potit = new ArrayList<Potti>();
    }
    
  
    public void addpelaaja(Pelaaja pelaaja) {
        pelaajat.add(pelaaja);
    }
    
    /**
     * Main peli looppi.
     */
    public void run() {
        for (Pelaaja pelaaja : pelaajat) {
            pelaaja.getClient().joinedTable(poytaTyyppi, bigBlind, pelaajat);
        }
        jakajaPositio = -1;
        actorPositio = -1;
        while (true) {
            int noOfActivepelaajas = 0;
            for (Pelaaja pelaaja : pelaajat) {
                if (pelaaja.getRahat()>= bigBlind) {
                    noOfActivepelaajas++;
                }
            }
            if (noOfActivepelaajas > 1) {
                playHand();
            } else {
                break;
            }
        }
        
        // Game over.
        alusta.clear();
        potit.clear();
        panos = 0;
        notifyBoardUpdated();
        for (Pelaaja pelaaja : pelaajat) {
            pelaaja.resetHand();
        }
        notifypelaajasUpdated(false);
        notifyMessage("Game over.");
    }
    
    /**
     * pelaa yksi kasi
     */
    private void playHand() {
        resetHand();
        
        // Small blind.
        if (aktiivipelaajat.size() > 2) {
            rotateActor();
        }
        postSmallBlind();
        
        // Big blind.
        rotateActor();
        postBigBlind();
        
        // Pre-Flop.
        dealHoleCards();
        doBettingRound();
        
        // Flop.
        if (aktiivipelaajat.size() > 1) {
            panos = 0;
            dealCommunityCards(3);
            minPanos = bigBlind;
            doBettingRound();

            // Turn.
            if (aktiivipelaajat.size() > 1) {
                panos = 0;
                dealCommunityCards(1);
                if (poytaTyyppi == PoytaTyyppi.FIXED_LIMIT) {
                    minPanos = 2 * bigBlind;
                } else {
                    minPanos = bigBlind;
                }
                doBettingRound();

                // River.
                if (aktiivipelaajat.size() > 1) {
                    panos = 0;
                    dealCommunityCards(1);
                    if (poytaTyyppi == PoytaTyyppi.FIXED_LIMIT) {
                        minPanos = 2 * bigBlind;
                    } else {
                        minPanos = bigBlind;
                    }
                    doBettingRound();

                    // Showdown.
                    if (aktiivipelaajat.size() > 1) {
                        panos = 0;
                        minPanos = bigBlind;
                        doShowdown();
                    }
                }
            }
        }
    }
    
    /**
     * Resetoi peli uutta kätta varten.
     */
    private void resetHand() {
        // Clear the alusta.
        alusta.clear();
        potit.clear();
        notifyBoardUpdated();
        

        aktiivipelaajat.clear();
        for (Pelaaja pelaaja : pelaajat) {
            pelaaja.resetHand();

            if (pelaaja.getRahat() >= bigBlind) {
                aktiivipelaajat.add(pelaaja);
            }
        }
        

        jakajaPositio = (jakajaPositio + 1) % aktiivipelaajat.size();
        jakaja = aktiivipelaajat.get(jakajaPositio);


        pakka.sekoitaPakka();


        actorPositio = jakajaPositio;
        actor = aktiivipelaajat.get(actorPositio);
        

        minPanos = bigBlind;
        panos = minPanos;
        

        for (Pelaaja pelaaja : pelaajat) {
            pelaaja.getClient().handStarted(jakaja);
        }
        notifypelaajasUpdated(false);

    }

    private void rotateActor() {
        actorPositio = (actorPositio + 1) % aktiivipelaajat.size();
        actor = aktiivipelaajat.get(actorPositio);
        for (Pelaaja pelaaja : pelaajat) {
            pelaaja.getClient().actorRotated(actor);
        }
    }
    
    private void postSmallBlind() {
        final int smallBlind = bigBlind / 2;
        actor.postSmallBlind(smallBlind);
        contributePot(smallBlind);
        notifyBoardUpdated();
        notifypelaajaActed();
    }

    private void postBigBlind() {
        actor.postBigBlind(bigBlind);
        contributePot(bigBlind);
        notifyBoardUpdated();
        notifypelaajaActed();
    }
    

    private void dealHoleCards() {
        for (Pelaaja pelaaja : aktiivipelaajat) {
            pelaaja.setCards(pakka.deal(2));
            
        }
        System.out.println();
        notifypelaajasUpdated(false);

    }
    

    private void dealCommunityCards(int noOfCards) {
        for (int i = 0; i < noOfCards; i++) {
            alusta.add(pakka.deal());
        }
        notifypelaajasUpdated(false);

    }
    

    private void doBettingRound() {
 
        int pelaajasToAct = aktiivipelaajat.size();
  
        if (alusta.size() == 0) {
  
            panos = bigBlind;
        } else {

            actorPositio = jakajaPositio;
            panos = 0;
        }
               
        vikaPanostaja = null;
        korotukset = 0;
        notifyBoardUpdated();
        
        while (pelaajasToAct > 0) {
            rotateActor();
            Action action = null;
            if (actor.isAllIn()) {
                action = Action.PASSAA;
                pelaajasToAct--;
            } else {
                Set<Action> allowedActions = getAllowedActions(actor);
                action = actor.getClient().act(minPanos, panos, allowedActions);
                if (!allowedActions.contains(action)) {
                    if (!(action instanceof BetAction && allowedActions.contains(Action.PANOSTA)) && !(action instanceof RaiseAction && allowedActions.contains(Action.KOROTA))) {
                        throw new IllegalStateException(String.format("pelaaja '%s' acted with illegal %s action", actor, action));
                    }
                }
                pelaajasToAct--;
                if (action == Action.PASSAA) {
                } else if (action == Action.KATSO) {
                    int panosIncrement = panos - actor.getBet();
                    if (panosIncrement > actor.getRahat()) {
                        panosIncrement = actor.getRahat();
                    }
                    actor.maksaRahhoo(panosIncrement);
                    actor.setBet(actor.getBet() + panosIncrement);
                    contributePot(panosIncrement);
                } else if (action instanceof BetAction) {
                    int maara = (poytaTyyppi == PoytaTyyppi.FIXED_LIMIT) ? minPanos : action.getAmount();
                    if (maara < minPanos && maara < actor.getRahat()) {
                        throw new IllegalStateException("Illegal client action: panos vähemmän kuin min panos!");
                    }
                    if (maara > actor.getRahat() && actor.getRahat() >= minPanos) {
                        throw new IllegalStateException("Illegal client action: panos enemmän kuin varaa!");
                    }
                    panos = maara;
                    minPanos = maara;
                    int panosIncrement = panos - actor.getBet();
                    if (panosIncrement > actor.getRahat()) {
                        panosIncrement = actor.getRahat();
                    }
                    actor.setBet(panos);
                    actor.maksaRahhoo(panosIncrement);
                    contributePot(panosIncrement);
                    vikaPanostaja = actor;
                    pelaajasToAct = (poytaTyyppi == PoytaTyyppi.FIXED_LIMIT) ? aktiivipelaajat.size() : (aktiivipelaajat.size() - 1);
                } else if (action instanceof RaiseAction) {
                    int maara = (poytaTyyppi == PoytaTyyppi.FIXED_LIMIT) ? minPanos : action.getAmount();
                    if (maara < minPanos && maara < actor.getRahat()) {
                        throw new IllegalStateException("Illegal client action: raise less than minimum panos!");
                    }
                    if (maara > actor.getRahat() && actor.getRahat() >= minPanos) {
                        throw new IllegalStateException("Illegal client action: raise more cash than you own!");
                    }
                    panos += maara;
                    minPanos = maara;
                    int panosIncrement = panos - actor.getBet();
                    if (panosIncrement > actor.getRahat()) {
                        panosIncrement = actor.getRahat();
                    }
                    actor.setBet(panos);
                    actor.maksaRahhoo(panosIncrement);
                    contributePot(panosIncrement);
                    vikaPanostaja = actor;
                    korotukset++;
                    if (poytaTyyppi == PoytaTyyppi.FIXED_LIMIT && (korotukset < MAX_KOROTUKSET || aktiivipelaajat.size() == 2)) {
                        pelaajasToAct = aktiivipelaajat.size();
                    } else {
                        pelaajasToAct = aktiivipelaajat.size() - 1;
                    }
                } else if (action == Action.LUOVUTA) {
                    actor.setCards(null);
                    aktiivipelaajat.remove(actor);
                    actorPositio--;
                    if (aktiivipelaajat.size() == 1) {
                        notifyBoardUpdated();
                        notifypelaajaActed();
                        Pelaaja voittaja = aktiivipelaajat.get(0);
                        int maara = getTotalPot();
                        voittaja.voita(maara);
                        notifyBoardUpdated();
                        notifyMessage("%s wins $ %d.", voittaja, maara);
                        pelaajasToAct = 0;
                    }
                } else {
                    throw new IllegalStateException("Invalid action: " + action);
                }
            }
            actor.setAction(action);
            if (aktiivipelaajat.size() > 1) {
                notifyBoardUpdated();
                notifypelaajaActed();
            }
        }
        
        for (Pelaaja pelaaja : aktiivipelaajat) {
            pelaaja.resetBet();
        }
        notifyBoardUpdated();
        notifypelaajasUpdated(false);
    }
    
    private Set<Action> getAllowedActions(Pelaaja pelaaja) {
        Set<Action> actions = new HashSet<Action>();
        if (pelaaja.isAllIn()) {
            actions.add(Action.PASSAA);
        } else {
            int actorBet = actor.getBet();
            if (panos == 0) {
                actions.add(Action.PASSAA);
                if (poytaTyyppi == PoytaTyyppi.NO_LIMIT || korotukset < MAX_KOROTUKSET || aktiivipelaajat.size() == 2) {
                    actions.add(Action.PANOSTA);
                }
            } else {
                if (actorBet < panos) {
                    actions.add(Action.KATSO);
                    if (poytaTyyppi == PoytaTyyppi.NO_LIMIT || korotukset < MAX_KOROTUKSET || aktiivipelaajat.size() == 2) {
                        actions.add(Action.KOROTA);
                    }
                } else {
                    actions.add(Action.PASSAA);
                    if (poytaTyyppi == PoytaTyyppi.NO_LIMIT || korotukset < MAX_KOROTUKSET || aktiivipelaajat.size() == 2) {
                        actions.add(Action.KOROTA);
                    }
                }
            }
            actions.add(Action.LUOVUTA);
        }
        return actions;
    }
    
    private void contributePot(int maara) {
        for (Potti potti : potit) {
            if (!potti.hasContributer(actor)) {
                int potBet = potti.getPanos();
                if (maara >= potBet) {
                    // Regular call, panos or raise.
                    potti.addContributer(actor);
                    maara -= potti.getPanos();
                } else {
                    // Partial call (all-in); redistribute potit.
                    potit.add(potti.split(actor, maara));
                    maara = 0;
                }
            }
            if (maara <= 0) {
                break;
            }
        }
        if (maara > 0) {
            Potti potti = new Potti(maara);
            potti.addContributer(actor);
            potit.add(potti);
        }
    }
    

    private void doShowdown() {

        List<Pelaaja> naytaPelaajat = new ArrayList<Pelaaja>();
        for (Potti potti : potit) {
            for (Pelaaja contributor : potti.getContributors()) {
                if (!naytaPelaajat.contains(contributor) && contributor.isAllIn()) {
                    naytaPelaajat.add(contributor);
                }
            }
        }
        if (vikaPanostaja != null) {
            if (!naytaPelaajat.contains(vikaPanostaja)) {
                naytaPelaajat.add(vikaPanostaja);
            }
        }
        int pos = (jakajaPositio + 1) % aktiivipelaajat.size();
        while (naytaPelaajat.size() < aktiivipelaajat.size()) {
            Pelaaja pelaaja = aktiivipelaajat.get(pos);
            if (!naytaPelaajat.contains(pelaaja)) {
                naytaPelaajat.add(pelaaja);
            }
            pos = (pos + 1) % aktiivipelaajat.size();
        }
        
        boolean firstToShow = true;
        int bestHandValue = -1;
        for (Pelaaja pelaajaToShow : naytaPelaajat) {
            Kasi kasi = new Kasi(alusta); 
            
            HandValue handValue = new HandValue();
            
            boolean doShow = AINA_KUTSU_SHOWDOWN;
            if (!doShow) {
                if (pelaajaToShow.isAllIn()) {

                    doShow = true;
                    firstToShow = false;
                } else if (firstToShow) {

                    doShow = true;
                    bestHandValue = handValue.getParasKasi(kasi.getKortit(), pelaajaToShow.getCards()).getArvo();
                    System.out.println(handValue.getParasKasi(kasi.getKortit(), pelaajaToShow.getCards()));
                    firstToShow = false;
                } else {

                    if (handValue.getParasKasi(kasi.getKortit(), pelaajaToShow.getCards()).getArvo() >= bestHandValue) {
                        doShow = true;
                        bestHandValue = handValue.getParasKasi(kasi.getKortit(), pelaajaToShow.getCards()).getArvo();
                        System.out.println(handValue.getParasKasi(kasi.getKortit(), pelaajaToShow.getCards()));
                    }
                }
            }
            if (doShow) {

                for (Pelaaja pelaaja : pelaajat) {
                    pelaaja.getClient().playerUpdated(pelaajaToShow);
                }
                notifyMessage(pelaajaToShow.getName() + " käsi: " + handValue.getKuvaus());
            } else {
                // Fold.
                pelaajaToShow.setCards(null);
                aktiivipelaajat.remove(pelaajaToShow);
                for (Pelaaja pelaaja : pelaajat) {
                    if (pelaaja.equals(pelaajaToShow)) {
                        pelaaja.getClient().playerUpdated(pelaajaToShow);
                    } else {
                        pelaaja.getClient().playerUpdated(pelaajaToShow.publicClone());
                    }
                }
                notifyMessage(pelaajaToShow.getName() + " käsi: " + handValue.getKuvaus());
            }
        }
        
        Map<Integer, List<Pelaaja>> rankatutpelaajat = new TreeMap<Integer, List<Pelaaja>>();
        for (Pelaaja pelaaja : aktiivipelaajat) {
            Kasi kasi = new Kasi(alusta);
            
            int handValue = new HandValue(kasi.getKortit(), pelaaja.getCards()).getArvo(); // käden evaluointi
            List<Pelaaja> pelaajaList = rankatutpelaajat.get(handValue);
            if (pelaajaList == null) {
                pelaajaList = new ArrayList<Pelaaja>();
            }
            pelaajaList.add(pelaaja);
            rankatutpelaajat.put(handValue, pelaajaList);
        }
        
        int totalPot = getTotalPot();
        Map<Pelaaja, Integer> potDivision = new HashMap<Pelaaja, Integer>();
        for (Integer handValue : rankatutpelaajat.keySet()) {
            List<Pelaaja> voittajat = rankatutpelaajat.get(handValue); // 
            for (Potti potti : potit) {
                int noOfvoittajasInPot = 0;
                for (Pelaaja voittaja : voittajat) {
                    if (potti.hasContributer(voittaja)) {
                        noOfvoittajasInPot++;
                    }
                }
                if (noOfvoittajasInPot > 0) {
                    int potShare = potti.getArvo() / noOfvoittajasInPot;
                    for (Pelaaja voittaja : voittajat) {
                        if (potti.hasContributer(voittaja)) {
                            Integer oldShare = potDivision.get(voittaja);
                            if (oldShare != null) {
                                potDivision.put(voittaja, oldShare + potShare);
                            } else {
                                potDivision.put(voittaja, potShare);
                            }
                            
                        }
                    }
                    int oddChips = potti.getArvo() % noOfvoittajasInPot;
                    if (oddChips > 0) {
                        pos = jakajaPositio;
                        while (oddChips > 0) {
                            pos = (pos + 1) % aktiivipelaajat.size();
                            Pelaaja voittaja = aktiivipelaajat.get(pos);
                            Integer oldShare = potDivision.get(voittaja);
                            if (oldShare != null) {
                                potDivision.put(voittaja, oldShare + 1);
                                oddChips--;
                            }
                        }
                        
                    }
                    potti.clear();
                }
            }
        }
        
        StringBuilder voittajaText = new StringBuilder();
        int totalWon = 0;
        for (Pelaaja voittaja : potDivision.keySet()) {
            int potShare = potDivision.get(voittaja);
            voittaja.voita(potShare);
            totalWon += potShare;
            if (voittajaText.length() > 0) {
                voittajaText.append(", ");
            }
            voittajaText.append(String.format("Voittaja: " + voittaja.getName() + ", potti: " + potShare));
            notifypelaajasUpdated(true);
        }
        voittajaText.append('.');
        notifyMessage(voittajaText.toString());
        
        if (totalWon != totalPot) {
            throw new IllegalStateException("Incorrect pot division!");
        }
    }
    

    private void notifyMessage(String message, Object... args) {
        message = String.format(message, args);
        for (Pelaaja pelaaja : pelaajat) {
            pelaaja.getClient().messageReceived(message);
        }
    }
    
    private void notifyBoardUpdated() {
        int pot = getTotalPot();
        for (Pelaaja pelaaja : pelaajat) {
            pelaaja.getClient().alustaUpdated(alusta, panos, pot);
        }
    }
    
    private int getTotalPot() {
        int totalPot = 0;
        for (Potti potti : potit) {
            totalPot += potti.getArvo();
        }
        return totalPot;
    }

    private void notifypelaajasUpdated(boolean showdown) {
        for (Pelaaja pelaajaToNotify : pelaajat) {
            for (Pelaaja pelaaja : pelaajat) {
                if (!showdown && !pelaaja.equals(pelaajaToNotify)) {
                    // Hide secret information to other pelaajas.
                    pelaaja = pelaaja.publicClone();
                }
                pelaajaToNotify.getClient().playerUpdated(pelaaja);
            }
        }
    }
    
    private void notifypelaajaActed() {
        for (Pelaaja p : pelaajat) {
            Pelaaja pelaajaInfo = p.equals(actor) ? actor : actor.publicClone();
            p.getClient().playerActed(pelaajaInfo);
        }
    }
    
}
