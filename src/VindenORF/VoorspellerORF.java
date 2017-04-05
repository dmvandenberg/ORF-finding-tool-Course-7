package VindenORF;

import java.util.*;
import java.util.regex.*;

/**
 *
 * @author Anne van Ewijk (bi2c) datum: 26-03-2017 In deze class worden de ORFs
 * voorspeld van de ingevoerde sequentie en gemaakte complementaire streng. Ook
 * is in deze class gebruik gemaakt van overloading.
 */
public class VoorspellerORF extends Feature{

    private static ArrayList openReadingFrameLijst = new ArrayList();
    private static ArrayList infoORF = new ArrayList();
    private static ArrayList<Integer> idsORF = new ArrayList();
    private static ArrayList sequentieDNA_ORF = new ArrayList();
    private static ArrayList sequentieEiwitORF = new ArrayList();
    private static ArrayList readingFrameORF = new ArrayList();
    private static ArrayList startStopORF = new ArrayList();
    private static int idORF;
    private int start, stop;
    private String aminozuurSeq;
    private String startcodon;
    private String stopcodon;

    /**
     *
     * @param sequentie
     * @param header Deze class zoekt de ORFs voor de normale sequentie.
     * (bijbehorende reading frames: 1, 2, 3.
     */
    public void zoeken(String sequentie, String header) {
        ManipulatieSequentie mani = new ManipulatieSequentie();
        // De regex zoekt naar ATG (startcodon) en vanaf daar zoek hij steeds per 3 nucleotiden naar een van de stopcodons
        Pattern patroon = Pattern.compile("(ATG)(.{3})*?(TAG|TGA|TAA)", Pattern.CASE_INSENSITIVE);
        int readingFrame = 0;        
        Matcher seq = patroon.matcher(sequentie);
        
        while (seq.find()) {
            aminozuurSeq = mani.toProtein(seq.group()); //seq.group is de sequentie van het ORF
            start = seq.start(); // Positie van het startcodon
            stop = seq.end();   // Positie van het stopcodon
            startcodon(start, sequentie);
            stopcodon (stop, sequentie);
            idORF++;
            
            if ((seq.start() % 3) == 0) {
                readingFrame = +1;
            } else if ((seq.start() % 3) == 1) {
                readingFrame = +2;
            } else if ((seq.start() % 3) == 2) {
                readingFrame = +3;
            } else {
                System.out.println("Er is geen ORF gevonden");
            }
            
            sequentieEiwitORF.add(aminozuurSeq);
            ORF genObject = maakORF(start, stop, readingFrame, idORF, seq.group(), aminozuurSeq);
            openReadingFrameLijst.add(genObject);
            idsORF.add(idORF);
            sequentieDNA_ORF.add(seq.group());
            readingFrameORF.add(readingFrame);
            startStopORF.add(start + "/" + stop);
            infoORF.add(genObject.makeString());

        }

    }

    /**
     *
     * @param sequentie
     * @param header
     * @param bevestiging Deze class zoekt de ORFs voor de complementaire
     * streng. (bijbehorende reading frames: -1, -2, -3.)
     */
    public void zoeken(String sequentie, String header, String bevestiging) {
        ManipulatieSequentie mani = new ManipulatieSequentie();
        //
        Pattern patroon = Pattern.compile("(ATG)(.{3})*?(TAG|TGA|TAA)", Pattern.CASE_INSENSITIVE);
        int readingFrame = 0;
        Matcher seq = patroon.matcher(sequentie);
     
        while (seq.find()) {
            aminozuurSeq = mani.toProtein(seq.group());//seq.group is de sequentie van het ORF
            start = sequentie.length() - seq.start(); // Positie van het startcodon op de orginele sequentie
            startcodon = sequentie.substring(start , start + 3);
            stop = sequentie.length() - seq.end();   // Positie van het stopcodon op de orginele sequentie
            idORF++;
     
            
            if ((seq.start() % 3) == 0) {
                readingFrame = -1;
            } else if ((seq.start() % 3) == 1) {
                readingFrame = -2;
            } else if ((seq.start() % 3) == 2) {
                readingFrame = -3;
            } else {
                System.out.println("Er is geen ORF gevonden");
            }
            
            sequentieEiwitORF.add(aminozuurSeq);
            ORF genObject = maakORF(start, stop, readingFrame, idORF, seq.group(), aminozuurSeq);
            openReadingFrameLijst.add(genObject);
            idsORF.add(idORF);
            sequentieDNA_ORF.add(seq.group());
            readingFrameORF.add(readingFrame);
            startStopORF.add(start + "/" + stop);

            infoORF.add(genObject.makeString());
        }
    }

    /**
     *
     * @param startPositie
     * @param stopPositie
     * @param readingFrame
     * @param idORF
     * @param seqDNA
     * @param seqEiwit
     * @return object
     *
     * De verschillende variabele worden als ORF Object opgeslagen.
     */
    public static ORF maakORF(int startPositie, int stopPositie, int readingFrame, int idORF, String seqDNA, String seqEiwit) {
        ORF object = new ORF(startPositie, stopPositie, readingFrame, idORF, seqDNA, seqEiwit);
        return object;
    }

    /**
     *
     * @return openReadingFrameLijst
     * ArrayList met genobjecten.
     */
    public ArrayList getOpenReadingFrameLijst() {
        return openReadingFrameLijst;
    }

    /**
     *
     * @return idsORF
     * ArrayList met alle ORFs IDs
     */
    public ArrayList getIdsORF() {
        return idsORF;
    }

    /**
     *
     * @return sequentieDNA_ORF
     * ArrayList met de DNA sequentie van elk gevonden ORF
     */
    public ArrayList getSequentieDNA_ORF() {
        return sequentieDNA_ORF;
    }

    /**
     *
     * @return sequentieEiwitORF
     * ArrayList met de Eiwit sequentie van elk gevonden ORF
     */
    public static ArrayList getSequentieEiwitORF() {
        return sequentieEiwitORF;
    }

    /**
     *
     * @return readingFrameORF
     * ArrayList met van elke ORF het reading frame
     */
    public ArrayList getReadingFrameORF() {
        return readingFrameORF;
    }

    /**
     *
     * @return startStopORF
     * ArrayList met van elk ORF de start/stop positie
     */
    public ArrayList getStartStopORF() {
        return startStopORF;
    }

    /**
     *
     * @return infoORF
     * ArrayList met alle info over het ORF netjes weergegeven, zodat het makkelijk
     * te plaatsen is in een TextArea.
     */
    public ArrayList getInfoORF() {
        return infoORF;
    }

}
