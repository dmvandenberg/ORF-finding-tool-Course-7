package BestandLezen;

import javax.swing.*;

/**
 *
 * @author Anne van Ewijk (bi2c) datum: 26-03-2017 In deze class wordt een
 * custon exception gemaakt. Deze wordt opgeroepen als de ingegeven sequentie
 * geen DNA is.
 *
 */
public class GeenDNA extends Exception {

    /**
     * Overschrijven van de constructor van Exception
     */
    public GeenDNA() {
        /**
         * call van de constructor van de super class: Exception
         */
        super();
        JOptionPane.showMessageDialog(null, "Corruptie in bestand gevonden.\n De sequentie is geen DNA sequentie", "GEEN DNA", JOptionPane.ERROR_MESSAGE);
    }

    /**
     *
     * @param err
     */
    public GeenDNA(String err) {
        super(err);
        JOptionPane.showMessageDialog(null, "Corruptie in bestand gevonden.\n De sequentie is geen DNA sequentie", "GEEN DNA", JOptionPane.ERROR_MESSAGE);
    }
}
