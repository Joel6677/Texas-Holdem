
package texasholdem.main;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;

import texasholdem.model.domain.Kortti;
import texasholdem.model.domain.Client;
import texasholdem.poyta.Poyta;
import texasholdem.poyta.PoytaTyyppi;
import texasholdem.actions.Action;
import texasholdem.botti.Bot;
import texasholdem.botti.Botti;
import texasholdem.model.domain.Pelaaja;
import texasholdem.view.BoardPanel;
import texasholdem.view.BoardPanel;
import texasholdem.view.ControlPanel;
import texasholdem.view.ControlPanel;
import texasholdem.view.PlayerPanel;
import texasholdem.view.PlayerPanel;
import texasholdem.view.UIConstants;
import texasholdem.view.UIConstants;


public class Main extends JFrame implements Client {


    private static final PoytaTyyppi TABLE_TYPE = PoytaTyyppi.NO_LIMIT;

    private static final int BIG_BLIND = 10;
    
    private static final int STARTING_CASH = 500;
    
    private final Poyta table;
    
    private final Map<String, Pelaaja> pelaajat;
    
    private final GridBagConstraints gc;
    
    private final BoardPanel boardPanel;
    
    private final ControlPanel controlPanel;
    
    private final Map<String, PlayerPanel> playerPanels;
    
    private final Pelaaja humanPlayer;
    
    private String dealerName; 

    private String actorName; 

    public Main() {
        super("Texas Hold'em poker");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(UIConstants.TABLE_COLOR);
        setLayout(new GridBagLayout());

        gc = new GridBagConstraints();
        
        controlPanel = new ControlPanel(TABLE_TYPE);
        
        boardPanel = new BoardPanel(controlPanel);        
        addComponent(boardPanel, 1, 1, 1, 1);
        
        pelaajat = new LinkedHashMap<String, Pelaaja>();
        humanPlayer = new Pelaaja("Pelaaja", STARTING_CASH, this);
        pelaajat.put("Player", humanPlayer);
        pelaajat.put("Paavo",    new Pelaaja("Paavo",   STARTING_CASH, new Botti()));

        table = new Poyta(TABLE_TYPE, BIG_BLIND);
        for (Pelaaja pelaaja : pelaajat.values()) {
            table.addpelaaja(pelaaja);
        }
        
        playerPanels = new HashMap<String, PlayerPanel>();
        int i = 0;
        for (Pelaaja pelaaja : pelaajat.values()) {
            PlayerPanel panel = new PlayerPanel();
            playerPanels.put(pelaaja.getName(), panel);
            switch (i++) {
                case 0:
                 
                    addComponent(panel, 1, 0, 1, 1);
                    break;
                case 1:
                   
                    addComponent(panel, 2, 1, 1, 1);
                    break;
                case 2:
                    
                    addComponent(panel, 1, 2, 1, 1);
                    break;
                case 3:
              
                    addComponent(panel, 0, 1, 1, 1);
                    break;
                default:
          
            }
        }
        

        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

 
        table.run();
    }
    
    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void joinedTable(PoytaTyyppi type, int bigBlind, List<Pelaaja> pelaajat) {
        for (Pelaaja pelaaja : pelaajat) {
            PlayerPanel playerPanel = playerPanels.get(pelaaja.getName());
            if (playerPanel != null) {
                playerPanel.update(pelaaja);
            }
        }
    }

    @Override
    public void messageReceived(String message) {
        boardPanel.setMessage(message);
        boardPanel.waitForUserInput();
    }

    @Override
    public void handStarted(Pelaaja jakaja) {
        setDealer(false);
        dealerName = jakaja.getName();
        setDealer(true);
    }

    @Override
    public void actorRotated(Pelaaja actor) {
        setActorInTurn(false);
        actorName = actor.getName();
        setActorInTurn(true);
    }

    @Override
    public void alustaUpdated(List<Kortti> kortit, int bet, int pot) {
        boardPanel.update(kortit, bet, pot);
    }

    @Override
    public void playerUpdated(Pelaaja pelaaja) {
        PlayerPanel playerPanel = playerPanels.get(pelaaja.getName());
        if (playerPanel != null) {
            playerPanel.update(pelaaja);
        }
    }

    @Override
    public void playerActed(Pelaaja pelaaja) {
        String name = pelaaja.getName();
        PlayerPanel playerPanel = playerPanels.get(name);
        if (playerPanel != null) {
            playerPanel.update(pelaaja);
            Action action = pelaaja.getAction();
            if (action != null) {
                boardPanel.setMessage(String.format("%s %s.", name, action.getVerb()));
                if (pelaaja.getClient() != this) {
                    boardPanel.waitForUserInput();
                }
            }
        } else {
            throw new IllegalStateException(
                    String.format("Ei löydetty paneelia '%s'", name));
        }
    }

    @Override
    public Action act(int minBet, int currentBet, Set<Action> allowedActions) {
        boardPanel.setMessage("Valitse aktio:");
        return controlPanel.getUserInput(minBet, humanPlayer.getRahat(), allowedActions);
    }

    private void addComponent(Component component, int x, int y, int width, int height) {
        gc.gridx = x;
        gc.gridy = y;
        gc.gridwidth = width;
        gc.gridheight = height;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.NONE;
        gc.weightx = 0.0;
        gc.weighty = 0.0;
        getContentPane().add(component, gc);
    }

    private void setActorInTurn(boolean isInTurn) {
        if (actorName != null) {
            PlayerPanel playerPanel = playerPanels.get(actorName);
            if (playerPanel != null) {
                playerPanel.setInTurn(isInTurn);
            }
        }
    }

    private void setDealer(boolean isDealer) {
        if (dealerName != null) {
            PlayerPanel playerPanel = playerPanels.get(dealerName);
            if (playerPanel != null) {
                playerPanel.setDealer(isDealer);
            }
        }
    }

}
