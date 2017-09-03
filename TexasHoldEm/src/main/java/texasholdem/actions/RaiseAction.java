package texasholdem.actions;

public class RaiseAction extends Action {

    public RaiseAction(int amount) {
        super("Korota", "korottaa", amount);
    }

    @Override
    public String toString() {
        return String.format("Korota(%d)", getAmount());
    }

}
