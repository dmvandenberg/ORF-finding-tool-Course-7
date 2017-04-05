package BestandLezen;

import javax.swing.*;

/**
 *
 * @author Anne van Ewijk (bi2c) datum: 26-03-2017 In deze class wordt een
 * custom exception gemaakt. Deze wordt opgeroepen als er geen path is
 * meegegeven en er toch op de open button wordt gedrukt.
 */
public class GeenBestandIngevuld extends Exception {
    /**
     * Overschrijven van de constructor van Exception
     */
    public GeenBestandIngevuld() {
        /**
         * call van de constructor van de super class: Exception
         */
        super();
        JOptionPane.showMessageDialog(null, "Er is geen bestand gekozen!", "LET OP", JOptionPane.WARNING_MESSAGE);
    }

    /**
     *
     * @param err
     */
    public GeenBestandIngevuld(String err) {
        super(err);
        JOptionPane.showMessageDialog(null, "Er is geen bestand gekozen!", "LET OP", JOptionPane.WARNING_MESSAGE);
    }
}
