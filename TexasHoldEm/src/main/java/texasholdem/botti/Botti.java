
package texasholdem.botti;

import java.util.List;
import java.util.Set;

import texasholdem.model.domain.Kortti;
import texasholdem.model.domain.Pelaaja;
import texasholdem.poyta.PoytaTyyppi;
import texasholdem.actions.Action;

/**
 * Botti joka aina passaa tai katsoo. 
 * 
 */
public class Botti extends Bot {

    @Override
    public void messageReceived(String message) {

    }


    @Override
    public void joinedTable(PoytaTyyppi type, int bigBlind, List<Pelaaja> players) {

    }


    @Override
    public void handStarted(Pelaaja dealer) {

    }


    @Override
    public void actorRotated(Pelaaja actor) {

    }


    @Override
    public void playerUpdated(Pelaaja player) {

    }


    @Override
    public void alustaUpdated(List<Kortti> cards, int bet, int pot) {

    }


    @Override
    public void playerActed(Pelaaja player) {

    }

    @Override
    public Action act(int minBet, int currentBet, Set<Action> allowedActions) {
        if (allowedActions.contains(Action.PASSAA)) {
            return Action.PASSAA;
        } else {
            return Action.KATSO;
        }
    }
    
}
