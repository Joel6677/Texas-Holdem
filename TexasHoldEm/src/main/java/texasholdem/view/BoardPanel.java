package texasholdem.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.ImageIcon;

import javax.swing.JLabel;
import javax.swing.JPanel;

import texasholdem.view.ResourceManager;
import texasholdem.model.domain.Kortti;

/**
 * Näyttää pöydän kortit (community cards).
 *  
 */
public class BoardPanel extends JPanel {
    

    private static final int NO_OF_CARDS = 5;
    
    private final ControlPanel controlPanel;
    
    private final JLabel panosLabel;

    private final JLabel pottiLabel;

    private final JLabel[] cardLabels;

    private final JLabel messageLabel;
    

    public BoardPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
        
        setBorder(UIConstants.PANEL_BORDER);
        setBackground(UIConstants.TABLE_COLOR);
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        
        JLabel label = new JLabel("Bet");
        label.setForeground(Color.GREEN);
        gc.gridx = 1;
        gc.gridy = 0;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.NONE;
        gc.weightx = 1.0;
        gc.weighty = 0.0;
        gc.insets = new Insets(0, 5, 0, 5);
        add(label, gc);
        
        label = new JLabel("Pot");
        label.setForeground(Color.GREEN);
        gc.gridx = 3;
        gc.gridy = 0;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.NONE;
        gc.weightx = 1.0;
        gc.weighty = 0.0;
        gc.insets = new Insets(0, 5, 0, 5);
        add(label, gc);
        
        panosLabel = new JLabel(" ");
        panosLabel.setBorder(UIConstants.LABEL_BORDER);
        panosLabel.setForeground(Color.GREEN);
        panosLabel.setHorizontalAlignment(JLabel.CENTER);
        gc.gridx = 1;
        gc.gridy = 1;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.weightx = 1.0;
        gc.weighty = 0.0;
        gc.insets = new Insets(5, 5, 5, 5);
        add(panosLabel, gc);

        pottiLabel = new JLabel(" ");
        pottiLabel.setBorder(UIConstants.LABEL_BORDER);
        pottiLabel.setForeground(Color.GREEN);
        pottiLabel.setHorizontalAlignment(JLabel.CENTER);
        gc.gridx = 3;
        gc.gridy = 1;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.weightx = 1.0;
        gc.weighty = 0.0;
        gc.insets = new Insets(5, 5, 5, 5);
        add(pottiLabel, gc);

        cardLabels = new JLabel[NO_OF_CARDS];
        for (int i = 0; i < 5; i++) {
//            cardLabels[i] = new JLabel(ResourceManager.getIcon("images/card_placeholder.png"));
            cardLabels[i] = new JLabel(new ImageIcon("src/main/java/texasholdem/images/card_placeholder.png"));
            gc.gridx = i;
            gc.gridy = 2;
            gc.gridwidth = 1;
            gc.gridheight = 1;
            gc.anchor = GridBagConstraints.CENTER;
            gc.fill = GridBagConstraints.NONE;
            gc.weightx = 0.0;
            gc.weighty = 0.0;
            gc.insets = new Insets(5, 1, 5, 1);
            add(cardLabels[i], gc);
        }
        
        // Message label.
        messageLabel = new JLabel();
        messageLabel.setForeground(Color.YELLOW);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        gc.gridx = 0;
        gc.gridy = 3;
        gc.gridwidth = 5;
        gc.gridheight = 1;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        gc.insets = new Insets(0, 0, 0, 0);
        add(messageLabel, gc);
        
        // Control panel.
        gc.gridx = 0;
        gc.gridy = 4;
        gc.gridwidth = 5;
        gc.gridheight = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        add(controlPanel, gc);
        
        setPreferredSize(new Dimension(400, 270));
        
        update(null, 0, 0);
    }
    
    /**
     * Updateaa kaden.
     * 
     * @param panos
     *             panos.
     * @param pottiti
     *             pottiti.
     */
    public void update(List<Kortti> cards, int panos, int potti) {
        if (panos == 0) {
            panosLabel.setText(" ");
        } else {
            panosLabel.setText("$ " + panos);
        }
        if (potti == 0) {
            pottiLabel.setText(" ");
        } else {
            pottiLabel.setText("$ " + potti);
        }
        int noOfCards = (cards == null) ? 0 : cards.size();
        for (int i = 0; i < NO_OF_CARDS; i++) {
            if (i < noOfCards) {
                cardLabels[i].setIcon(ResourceManager.getCardImage(cards.get(i)));
            } else {
                cardLabels[i].setIcon(new ImageIcon("src/main/java/texasholdem/images/card_placeholder.png"));
//                cardLabels[i].setIcon(ResourceManager.getIcon("images/card_placeholder.png"));
            }
        }
    }
    
    /**
     * Sets a custom message.
     * 
     * @param message
     *            The message.
     */
    public void setMessage(String message) {
        if (message.length() == 0) {
            messageLabel.setText(" ");
        } else {
            messageLabel.setText(message);
        }
    }
    
    /**
     * Waits for the user to continue.
     */
    public void waitForUserInput() {
        controlPanel.waitForUserInput();
    }
    
}
