package VindenORF;

import java.util.*;

/**
 *
 * @author Anne van Ewijk (b12c) datum: 26-03-2017 In deze class worden ORF
 * Objecten aangemaakt.
 */
public class ORF implements Comparable {

    private Integer start_orgineel;
    private Integer stop_orgineel;
    private Integer readingFrame;
    private Integer id;
    private String seqDNA;
    private String seqEiwit;

    /**
     *
     * @param startPositie
     * @param stopPositie
     * @param readingFrame
     * @param idORF
     * @param seqDNA
     * @param seqEiwit
     *
     * constructor
     */
    public ORF(Integer startPositie, Integer stopPositie, Integer readingFrame, Integer idORF, String seqDNA, String seqEiwit) {
        
        this.id = idORF;
        this.start_orgineel = startPositie;
        this.stop_orgineel = stopPositie;
        this.readingFrame = readingFrame;
        this.seqDNA = seqDNA;
        this.seqEiwit = seqEiwit;
    }

    /**
     *
     * @return ID
     */
    public Integer getID() {
        return id;
    }

    /**
     *
     * @param ID
     */
    public void setID(Integer ID) {
        this.id = ID;
    }

    /**
     *
     * @return start_orgineel
     */
    public Integer getStartPositie() {
        return start_orgineel;
    }

    /**
     *
     * @param startPositie
     */
    public void setStartPositie(Integer startPositie) {
        this.start_orgineel = startPositie;
    }

    /**
     *
     * @return stop_orgineel
     */
    public Integer getStopPositie() {
        return stop_orgineel;
    }

    /**
     *
     * @param stopPositie
     */
    public void setStopPositie(Integer stopPositie) {
        this.stop_orgineel = stopPositie;
    }

    /**
     *
     * @return readingFrame
     */
    public Integer getReadingFrame() {
        return readingFrame;
    }

    /**
     *
     * @param readingFrame
     */
    public void setReadingFrame(Integer readingFrame) {
        this.readingFrame = readingFrame;
    }

    /**
     *
     * @return seqDNA
     */
    public String getSeqDNA() {
        return seqDNA;
    }

    /**
     *
     * @param seqDNA
     */
    public void setSeqDNA(String seqDNA) {
        this.seqDNA = seqDNA;
    }

    /**
     *
     * @return seqEiwit
     */
    public String getSeqEiwit() {
        return seqEiwit;
    }

    /**
     *
     * @param seqEiwit
     */
    public void setSeqEiwit(String seqEiwit) {
        this.seqEiwit = seqEiwit;
    }
    

    /**
     *
     * @return een String hoe het in de textArea in de GUI wordt gezet
     */
    public String makeString() {
        return "ORF ID:\t" + id + "\nReading frame:\t\t" + readingFrame + "\nStart positie:\t\t" + start_orgineel + "\nStop positie:\t\t" + stop_orgineel + "\nDNA sequentie:\t\t" + seqDNA + "\nEiwit sequentie:\t\t" + seqEiwit + "\n";
    }

    /**
     *
     * @return hoe jij wilt dat het object wordt geprint Wanneer je het object
     * print, print hij nu niet de locatie, maar hij print het op de manier hoe
     * jij het daar hebt aangegeven.
     */
    @Override
    public String toString() {
        return id + "/" + start_orgineel + "/" + stop_orgineel + "/" + readingFrame + "/" + seqDNA;
    }

    /**
     *
     * @param t
     * @return 0 wanneer readingFrame gelijk zijn negatief getal wanneer het
     * vergeleken readingFrame kleiner is positief getal wanneer het vergelijken
     * readingFrame groter is CompareTo wordt gebruikt om te sorteren op
     * readingFrame.
     *
     */
    @Override
    public int compareTo(Object t) {
        ORF anderORF = (ORF) t;
        return this.readingFrame.compareTo(anderORF.readingFrame);
    }
}
