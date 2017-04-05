package BestandLezen;

import java.io.*;
import javax.swing.*;

/**
 *
 * @author Anne van Ewijk (bi2c) datum:26-03-2017 In deze class wordt een popup
 * schermpje gemaakt voor de gebruiker, zodat de gebruiker makkelijk een bestand
 * kan kiezen.
 */
public class BestandKiezen {

    private JFileChooser fileChooser;
    private String path;

    /**
     * Als er op de select button geklikt wordt, dan wordt deze methode
     * aangeroepen waardoor er gebladerd (d.m.v een popup) kan worden in de
     * bestanden van de gebruiker.
     */
    public void kiezen() {

        File selectedFile;
        setFileChooser(new JFileChooser());
        // Zorgt dat de gebruiker kan bladeren in zijn/haar bestanden.
        int keuze = getFileChooser().showOpenDialog(null);
        // Zorgt dat de gebruiker zijn/haar geselecteerde file kan openen.
        selectedFile = getFileChooser().getSelectedFile();

        setPath(selectedFile.getAbsolutePath());
    }

    /**
     * @return the fileChooser
     */
    public JFileChooser getFileChooser() {
        return fileChooser; //pop up bestand kiezen
    }

    /**
     * @param fileChooser the fileChooser to set
     */
    public void setFileChooser(JFileChooser fileChooser) {
        this.fileChooser = fileChooser;
    }

    /**
     *
     * @return path, path wordt gereturn omdat je path nodig hebt in een andere
     * class
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }
}
