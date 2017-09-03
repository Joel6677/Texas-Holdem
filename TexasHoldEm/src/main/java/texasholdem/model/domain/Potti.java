package texasholdem.model.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Luokassa määritellään potti ja siinä on siihen liittyviä metodoita.
 *
 */
public class Potti {

    private int panos;

    public final Set<Pelaaja> osanottajat;

    /**
     * Konstruktori.
     *
     * @param panos panos
     */
    public Potti(int panos) {
        this.panos = panos;
        osanottajat = new HashSet<Pelaaja>();
    }

    public int getPanos() {
        return panos;
    }

    /**
     * Get kontributorit.
     *
     * @return osanottajat
     */
    public Set<Pelaaja> getContributors() {
        return Collections.unmodifiableSet(osanottajat);
    }

    /**
     * Lisää osanottaja.
     *
     * @param pelaaja pelaaja
     */
    public void addContributer(Pelaaja pelaaja) {
        osanottajat.add(pelaaja);
    }

    /**
     * Sisältääkö osanottajat pelaajan.
     *
     * @param pelaaja pelaaja
     * @return osanottajat sisältäen pelaajan
     */
    public boolean hasContributer(Pelaaja pelaaja) {
        return osanottajat.contains(pelaaja);
    }

    /**
     * Get arvo.
     *
     * @return panos * osanottajien koko
     */
    public int getArvo() {
        return panos * osanottajat.size();
    }

    /**
     * Jakaa potin osiin.
     *
     * @param pelaaja pelaaja
     * @param osaPanos panoksen osa
     *
     * @return potti
     */
    public Potti split(Pelaaja pelaaja, int osaPanos) {
        Potti potti = new Potti(panos - osaPanos);
        for (Pelaaja contributer : osanottajat) {
            potti.addContributer(contributer);
        }
        panos = osaPanos;
        osanottajat.add(pelaaja);
        return potti;
    }

    /**
     * Poistaa potin.
     */
    public void clear() {
        panos = 0;
        osanottajat.clear();
    }
}
