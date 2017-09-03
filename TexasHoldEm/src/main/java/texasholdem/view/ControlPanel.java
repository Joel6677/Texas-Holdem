package texasholdem.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import texasholdem.poyta.PoytaTyyppi;
import texasholdem.actions.Action;
import texasholdem.actions.BetAction;
import texasholdem.actions.RaiseAction;

/**
 * Paneeli nappuloille.
 */
public class ControlPanel extends JPanel implements ActionListener {
    
    private final PoytaTyyppi poytaTyyppi;
    private final JButton checkButton;  
    private final JButton callButton;
    private final JButton betButton;
    private final JButton raiseButton;
    private final JButton foldButton;
    private final JButton continueButton;
    private final AmountPanel amountPanel;
    private final Object monitor = new Object();
    private Action selectedAction;
    

    public ControlPanel(PoytaTyyppi poytaTyyppi) {
        this.poytaTyyppi = poytaTyyppi;
        setBackground(UIConstants.TABLE_COLOR);
        continueButton = createActionButton(Action.JATKA);
        checkButton = createActionButton(Action.PASSAA);
        callButton = createActionButton(Action.KATSO);
        betButton = createActionButton(Action.PANOSTA);
        raiseButton = createActionButton(Action.KOROTA);
        foldButton = createActionButton(Action.LUOVUTA);
        amountPanel = new AmountPanel();
    }
    
    /**
     * Odottaa jatka napin painoa
     */
    public void waitForUserInput() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                removeAll();
                add(continueButton);
                repaint();
            }
        });
        Set<Action> allowedActions = new HashSet<Action>();
        allowedActions.add(Action.JATKA);
        getUserInput(0, 0, allowedActions);
    }
    
    /**
     * Odottaa napin painoa ja palauttaa aktion
     * 
     * @param minBet
     *         
     * @param cash
     *         
     * @param allowedActions
     *   
     * 
     * @return palauttaa aktion
     */
    public Action getUserInput(int minBet, int cash, final Set<Action> allowedActions) {
        selectedAction = null;
        while (selectedAction == null) {
            // Show the buttons for the allowed actions.
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    removeAll();
                    if (allowedActions.contains(Action.JATKA)) {
                        add(continueButton);
                    } else {
                        if (allowedActions.contains(Action.PASSAA)) {
                            add(checkButton);
                        }
                        if (allowedActions.contains(Action.KATSO)) {
                            add(callButton);
                        }
                        if (allowedActions.contains(Action.PANOSTA)) {
                            add(betButton);
                        }
                        if (allowedActions.contains(Action.KOROTA)) {
                            add(raiseButton);
                        }
                        if (allowedActions.contains(Action.LUOVUTA)) {
                            add(foldButton);
                        }
                    }
                    repaint();
                }
            });
            
            synchronized (monitor) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    // Ignore.
                }
            }
            
            if (poytaTyyppi == PoytaTyyppi.NO_LIMIT && (selectedAction == Action.PANOSTA || selectedAction == Action.KOROTA)) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        removeAll();
                        add(amountPanel);
                        repaint();
                    }
                });
                selectedAction = amountPanel.show(selectedAction, minBet, cash);
                if (selectedAction == Action.PANOSTA) {
                    selectedAction = new BetAction(amountPanel.getAmount());
                } else if (selectedAction == Action.KOROTA) {
                    selectedAction = new RaiseAction(amountPanel.getAmount());
                } else {
                    // User cancelled.
                    selectedAction = null;
                }
            }
        }
        
        return selectedAction;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == continueButton) {
            selectedAction = Action.JATKA;
        } else if (source == checkButton) {
            selectedAction = Action.PASSAA;
        } else if (source == callButton) {
            selectedAction = Action.KATSO;
        } else if (source == betButton) {
            selectedAction = Action.PANOSTA;
        } else if (source == raiseButton) {
            selectedAction = Action.KOROTA;
        } else {
            selectedAction = Action.LUOVUTA;
        }
        synchronized (monitor) {
            monitor.notifyAll();
        }
    }
    

    private JButton createActionButton(Action action) {
        String label = action.getName();
        JButton button = new JButton(label);
        button.setMnemonic(label.charAt(0));
        button.setSize(100, 30);
        button.addActionListener(this);
        return button;
    }

}
