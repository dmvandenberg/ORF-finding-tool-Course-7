package BestandLezen;

import VindenORF.ManipulatieSequentie;
import VindenORF.VoorspellerORF;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Anne van Ewijk (bi2c) datum: 26-03-2017 In deze class wordt het
 * bestand gelezen en worden andere classes aangeroepen.
 */
public class LeesBestand {

    private static BufferedReader inFile;
    private String line;
    public String sequentie = "", header = "";
    public static int count;

    /**
     * @return the inFile
     */
    public static BufferedReader getInFile() {
        return inFile;
    }

    /**
     * @param aInFile the inFile to set
     */
    public static void setInFile(BufferedReader aInFile) {
        inFile = aInFile;
    }

    /**
     *
     * @param path wordt als parameter gebruikt om het bestand te kunnen lezen.
     * @throws BestandLezen.GeenBestandIngevuld In deze methode wordt het
     * gekozen bestand ingelezen. er worden meerdere exceptions opgeroepen,
     * onderandere wanneer er meerdere sequentie in staan wordt er een exception
     * opgeroepen.
     */
    public void lezen(String path) throws GeenBestandIngevuld {
        if (path.isEmpty()) {
            throw new GeenBestandIngevuld(); //Wanneer er path staat in het textfiel wordt er een exception opgeroepen.
        } else {
            try {
                StringBuilder bestand = new StringBuilder();
                setInFile(new BufferedReader(new FileReader(path)));
                // Hij gaat regel voor regel het bestand lezen, en meteen kijken of de regel niet leeg is.                
                while ((line = getInFile().readLine()) != null) {
                    //Wanneer de regel begint met > is dit de header
                    if (line.startsWith(">")) {
                        header = line.replace(">", "");
                        sequentie = "";
                        count++;

                    } //Wanneer hij niet begint met > is dit de sequentie.
                    //De sequentie regels worden achter elkaar geplakt.
                    else {
                        sequentie += line;
                    }
                }
                //Wanneer er meer dan 2 sequenties in het bestand staan wordt er een exception opgeroepen.
                //Je kan dat zien doordat er dan meerdere > in voor komen.
                if (count > 1) {
                    JOptionPane.showMessageDialog(null, "Er zitten meerdere sequenties in dit bestand: " + count + ".\nDit is niet de bedoeling.", "Meerdere sequenties", JOptionPane.ERROR_MESSAGE);
                }
                //Roept de functie aan waarbij de sequentie wordt gecontroleerd.
                ControleDNA control = new ControleDNA();
                control.controleer(sequentie);

                VoorspellerORF voorspeller = new VoorspellerORF();
                ManipulatieSequentie manipulatie = new ManipulatieSequentie();
                manipulatie.makeHashMap(); //vullen van de hashmap
                voorspeller.zoeken(sequentie, header);
                manipulatie.complementair(sequentie);
                String complem = manipulatie.getComplem();
                voorspeller.zoeken(complem, header, "complem");

            } catch (FileNotFoundException ex) {
                Logger.getLogger(LeesBestand.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Dit bestand is helaas niet gevonden.", "niet gevonden", JOptionPane.WARNING_MESSAGE);
            } catch (IOException ex) {
                Logger.getLogger(LeesBestand.class.getName()).log(Level.SEVERE, null, ex);
            } catch (GeenDNA ex) {
                Logger.getLogger(LeesBestand.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BadLocationException ex) {
                Logger.getLogger(LeesBestand.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *
     * @return header
     * De header van het ingevoerde bestand.
     */
    public String getHeader() {
        return header;
    }

    /**
     *
     * @return sequentie
     * De sequentie van het ingevoerde bestand.
     */
    public String getSequentie() {
        return sequentie;
    }
}
