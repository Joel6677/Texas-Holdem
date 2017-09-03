package texasholdem.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import texasholdem.model.domain.Kortti;
import texasholdem.model.domain.Pelaaja;
import texasholdem.actions.Action;

/**
 * Paneeli osoittaa pelaajan pelissä.
 *
 */
public class PlayerPanel extends JPanel {


    private static final Icon BUTTON_PRESENT_ICON
            = new ImageIcon("src/main/java/texasholdem/images/button_present.png");

    private static final Icon BUTTON_ABSENT_ICON
            = new ImageIcon("src/main/java/texasholdem/images/button_absent.png");
//            ResourceManager.getIcon("/images/button_absent.png");

    private static final Icon CARD_PLACEHOLDER_ICON
            = new ImageIcon("src/main/java/texasholdem/images/card_placeholder.png");
//        ResourceManager.getIcon("/images/card_placeholder.png");

    private static final Icon CARD_BACK_ICON
           = new ImageIcon("src/main/java/texasholdem/images/card_back.png");
//            = ResourceManager.getIcon("/images/card_back.png");


    private static final Border BORDER = new EmptyBorder(10, 10, 10, 10);


    private JLabel nameLabel;

    private JLabel cashLabel;

    private JLabel actionLabel;

    private JLabel betLabel;

    private JLabel card1Label;

    private JLabel card2Label;
 
    private JLabel dealerButton;


    public PlayerPanel() {
        setBorder(BORDER);
        setBackground(UIConstants.TABLE_COLOR);
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        nameLabel = new MyLabel();
        cashLabel = new MyLabel();
        actionLabel = new MyLabel();
        betLabel = new MyLabel();
        card1Label = new JLabel(CARD_PLACEHOLDER_ICON);
        card2Label = new JLabel(CARD_PLACEHOLDER_ICON);
        dealerButton = new JLabel(BUTTON_ABSENT_ICON);

        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 2;
        gc.gridheight = 1;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.NONE;
        add(dealerButton, gc);
        gc.gridx = 0;
        gc.gridy = 1;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.insets = new Insets(1, 1, 1, 1);
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        add(nameLabel, gc);
        gc.gridx = 1;
        gc.gridy = 1;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(cashLabel, gc);
        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(actionLabel, gc);
        gc.gridx = 1;
        gc.gridy = 2;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(betLabel, gc);
        gc.gridx = 0;
        gc.gridy = 3;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.NONE;
        add(card1Label, gc);
        gc.gridx = 1;
        gc.gridy = 3;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.NONE;
        add(card2Label, gc);

        setInTurn(false);
        setDealer(false);
    }

    /**
     * update paneeli
     *
     * @param pelaaja
     */
    public void update(Pelaaja pelaaja) {
        nameLabel.setText(pelaaja.getName());
        cashLabel.setText("$ " + pelaaja.getRahat());
        int bet = pelaaja.getBet();
        if (bet == 0) {
            betLabel.setText(" ");
        } else {
            betLabel.setText("$ " + bet);
        }
        Action action = pelaaja.getAction();
        if (action != null) {
            actionLabel.setText(action.getName());
        } else {
            actionLabel.setText(" ");
        }
        if (pelaaja.hasCards()) {
            Kortti[] kortit = pelaaja.getCards();
            if (kortit.length == 2) {
                // Visible cards.
                card1Label.setIcon(ResourceManager.getCardImage(kortit[0]));
                card2Label.setIcon(ResourceManager.getCardImage(kortit[1]));
            } else {
                // Hidden cards (face-down).
                card1Label.setIcon(CARD_BACK_ICON);
                card2Label.setIcon(CARD_BACK_ICON);
            }
        } else {
            // No cards.
            card1Label.setIcon(CARD_PLACEHOLDER_ICON);
            card2Label.setIcon(CARD_PLACEHOLDER_ICON);
        }
    }

    /**
     * asettaa pelaajan dealeriksi
     *
     * @param isDealer onko dealer
     */
    public void setDealer(boolean isDealer) {
        if (isDealer) {
            dealerButton.setIcon(BUTTON_PRESENT_ICON);
        } else {
            dealerButton.setIcon(BUTTON_ABSENT_ICON);
        }
    }

    /**
     * Onko pelaajan vuoro toimia
     *
     * @param inTurn
     */
    public void setInTurn(boolean inTurn) {
        if (inTurn) {
            nameLabel.setForeground(Color.YELLOW);
        } else {
            nameLabel.setForeground(Color.GREEN);
        }
    }


    private static class MyLabel extends JLabel {



        public MyLabel() {
            setBorder(UIConstants.LABEL_BORDER);
            setForeground(UIConstants.TEXT_COLOR);
            setHorizontalAlignment(JLabel.HORIZONTAL);
            setText(" ");
        }

    } 

}
