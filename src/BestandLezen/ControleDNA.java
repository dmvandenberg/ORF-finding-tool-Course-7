package BestandLezen;

/**
 *
 * @author Anne van Ewijk (bi2c) datum: 26-03-2017 In deze class word de
 * sequentie gecontroleerd. Wanneer de sequengtie geen DNA is, wordt er een
 * exception opgeroepen.
 */
public class ControleDNA {

    //Controle of de sequentie alleen bestaat uit A, T, G, C of N en dus DNA is.
    //Wanneer het geen DNA is wordt er een exceptie opgeroepen.
    public void controleer(String sequentie) throws GeenDNA {
        if (sequentie.matches("[ATGCN]+")) {
            System.out.println("Dit is DNA.");
        } else {
            throw new GeenDNA("Dit is geen goede sequentie.");
        }
    }
}
