
package texasholdem.view;

import java.net.URL;

import javax.swing.ImageIcon;

import texasholdem.model.domain.Kortti;


public abstract class ResourceManager {

    private static final String IMAGE_PATH_FORMAT = "/images/card_%s.png";

    /**
     * Palauttaa kortin kuvan
     *
     * @param kortti 
     *
     * @return kuva.
     */
    public static ImageIcon getCardImage(Kortti kortti) {
        // Use image order, which is different from value order.
        int sequenceNr = kortti.getMaa().getArvo() * 13 + kortti.getSijoitus().getArvo();
        String sequenceNrString = String.valueOf(sequenceNr);
        if (sequenceNrString.length() == 1) {
            sequenceNrString = "0" + sequenceNrString;
        }
        String path = String.format(IMAGE_PATH_FORMAT, sequenceNrString);
        return new ImageIcon("src/main/java/texasholdem" + path);
//        return getIcon(path);
    }

    /**
     * palauttaa image resource
     *
     * @param path classpath
     *
     * @return image resource
     *
     * @throws RuntimeException jos kuvaa ei löydy
     */
    public static ImageIcon getIcon(String path) {
        URL url = ResourceManager.class.getResource(path);
        if (url != null) {
            return new ImageIcon(url);
        } else {
            throw new RuntimeException("Resource file not found: " + path);
        }
    }

}
