/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texasholdem.model.evaluointi;

import texasholdem.model.evaluointi.VoittoEvaluointi;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import texasholdem.model.domain.Kasi.KasiSijoitus;

/**
 *
 * @author Jolle
 */
public class VoittoEvaluointiTest {
    
    @Test
    public void getVoitto() {
        VoittoEvaluointi voitto = new VoittoEvaluointi();
        voitto.setVoitto(KasiSijoitus.Kuningasvarisuora, 1);
     
        assertEquals(100, voitto.getVoitto());

    }

    
}
