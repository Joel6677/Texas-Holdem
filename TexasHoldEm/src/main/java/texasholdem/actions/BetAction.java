package texasholdem.actions;

public class BetAction extends Action {

    public BetAction(int maara) {
        super("Panosta", "panostaa", maara);
    }

    @Override
    public String toString() {
        return String.format("Panosta(%d)", getAmount());
    }

}
