/**
 * Luokassa määritetään voittokerroin kädelle
 */
package texasholdem.model.evaluointi;

import texasholdem.model.domain.Kasi.KasiSijoitus;
import texasholdem.model.domain.Pelaaja;

public class VoittoEvaluointi {

    private int voitto;    


    public int getVoitto() {
        return this.voitto;
    }

    public void setVoitto(KasiSijoitus kasi, int panos) {
        
        if (kasi.equals(KasiSijoitus.Kuningasvarisuora)) {
            this.voitto = (100 * panos);
        } else if (kasi.equals(KasiSijoitus.Värisuora)) {
            this.voitto = (50 * panos);
        } else if (kasi.equals(KasiSijoitus.Neloset)) {
            this.voitto = (40 * panos);
        } else if (kasi.equals(KasiSijoitus.Täyskäsi)) {
            this.voitto = (30 * panos);
        } else if (kasi.equals(KasiSijoitus.Väri)) {
            this.voitto = (20 * panos);
        } else if (kasi.equals(KasiSijoitus.Suora)) {
            this.voitto = (15 * panos);
        } else if (kasi.equals(KasiSijoitus.Kolmoset)) {
            this.voitto = (10 * panos);
        } else if (kasi.equals(KasiSijoitus.Kaksi_paria)) {
            this.voitto = (5 * panos);
        } else if (kasi.equals(KasiSijoitus.Pari)) {
            this.voitto = (2 * panos);
        } else {
            this.voitto = 0;
        }
    }

}
