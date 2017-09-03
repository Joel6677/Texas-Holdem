package texasholdem.actions;

/**
 * Pelaajan action.
 */
public abstract class Action {

    /**
     * Pelaaja all-in.
     */
    public static final Action ALL_IN = new AllInAction();

    /**
     * Panosta.
     */
    public static final Action PANOSTA = new BetAction(0);

    /**
     * Postaa big blind.
     */
    public static final Action ISO_BLIND = new BigBlindAction();

    /**
     * Call.
     */
    public static final Action KATSO = new CallAction();

    /**
     * Check.
     */
    public static final Action PASSAA = new CheckAction();

    /**
     * Jatka.
     */
    public static final Action JATKA = new ContinueAction();

    /**
     * Fold.
     */
    public static final Action LUOVUTA = new FoldAction();

    /**
     * Raise.
     */
    public static final Action KOROTA = new RaiseAction(0);

    /**
     * Postaa small blind.
     */
    public static final Action PIKKU_BLIND = new SmallBlindAction();

    /**
     * action nimi.
     */
    private final String nimi;

    /**
     * action verb.
     */
    private final String verb;

    /**
     * maara.
     */
    private final int maara;

    /**
     * Konstruktori.
     *
     * @param nimi action nimi.
     * @param verb action verb.
     */
    public Action(String nimi, String verb) {
        this(nimi, verb, 0);
    }

    /**
     * Konstruktori.
     *
     * @param nimi action nimi.
     * @param verb action verb.
     * @param maara action maara.
     */
    public Action(String nimi, String verb, int maara) {
        this.nimi = nimi;
        this.verb = verb;
        this.maara = maara;
    }

    /**
     * Return action nimi.
     *
     * @return action nimi.
     */
    public final String getName() {
        return nimi;
    }

    /**
     * Return action verb.
     *
     * @return action verb.
     */
    public final String getVerb() {
        return verb;
    }

    /**
     * Return action maara.
     *
     * @return action maara.
     */
    public final int getAmount() {
        return maara;
    }

    @Override
    public String toString() {
        return nimi;
    }

}
