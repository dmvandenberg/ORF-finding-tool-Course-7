package VindenORF;

import java.util.*;
import java.util.regex.*;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Anne van Ewijk (bi2c) datum: 26-03-2017 In deze class wordt op
 * verschillende manieren de ingeladen sequentie gemanipuleerd(bewerkt).
 */
public class ManipulatieSequentie {

    private String complem;
    private static String templateComple;
    private static String readingFrame1, readingFrame2, readingFrame3, readingFrame_1, readingFrame_2, readingFrame_3;
    private static String aminozuurReadingFrame1, aminozuurReadingFrame2, aminozuurReadingFrame3, aminozuurReadingFrame_1, aminozuurReadingFrame_2, aminozuurReadingFrame_3;

    private static String codon;
    private static HashMap<String, String> codonAminozuur = new HashMap();

    /**
     *
     * @param sequentie
     * @throws BadLocationException
     *
     * In deze class wordt de complementaire streng gemaakt.
     */
    public void complementair(String sequentie) throws BadLocationException {
        //eerst de letter in elkaar veranderen
        String seq = sequentie.replace("A", "t").replace("T", "a").replace("G", "c").replace("C", "g");
        templateComple = sequentie + "\n" + seq.toUpperCase();
        //dan opdraaien zodat het makkelijker is om dadelijk te gebruiken
        complem = new StringBuilder(seq.toUpperCase()).reverse().toString();
        openReadingFrame(sequentie);
    }

    /**
     *
     * @param sequentie
     * @throws BadLocationException De sequentie van de verschillende reading
     * frames worden hier gemaakt.
     */
    public void openReadingFrame(String sequentie) throws BadLocationException {
        //reading frames van de + strand
        readingFrame1 = sequentie.substring(0, sequentie.length());
        readingFrame2 = sequentie.substring(1, sequentie.length());
        readingFrame3 = sequentie.substring(2, sequentie.length());
        //reading frames van de - strand
        readingFrame_1 = complem.toUpperCase().substring(0, complem.length());
        readingFrame_2 = complem.toUpperCase().substring(1, complem.length());
        readingFrame_3 = complem.toUpperCase().substring(2, complem.length());
        
        //Van alle reading frames wordt een protein sequentie gemaakt.
        aminozuurReadingFrame1 = toProtein(readingFrame1);
        aminozuurReadingFrame2 = toProtein(readingFrame2);
        aminozuurReadingFrame3 = toProtein(readingFrame3);
        aminozuurReadingFrame_1 = toProtein(readingFrame_1);
        aminozuurReadingFrame_2 = toProtein(readingFrame_2);
        aminozuurReadingFrame_3 = toProtein(readingFrame_3);
    }

    /**
     * Hier wordt een HashMap gemaakt met als key een codon en als value het
     * bijbehorende aminozuur.
     */
    public void makeHashMap() {
        codonAminozuur.put("TTT", "F");
        codonAminozuur.put("TTC", "F");
        codonAminozuur.put("TTA", "L");
        codonAminozuur.put("TTG", "L");
        codonAminozuur.put("TCT", "S");
        codonAminozuur.put("TCC", "S");
        codonAminozuur.put("TCA", "S");
        codonAminozuur.put("TCG", "S");
        codonAminozuur.put("TAT", "Y");
        codonAminozuur.put("TAC", "Y");
        codonAminozuur.put("TAA", "*");
        codonAminozuur.put("TAG", "*");
        codonAminozuur.put("TGA", "*");
        codonAminozuur.put("TGT", "C");
        codonAminozuur.put("TGC", "C");
        codonAminozuur.put("TGG", "W");
        codonAminozuur.put("CTT", "L");
        codonAminozuur.put("CTC", "L");
        codonAminozuur.put("CTA", "L");
        codonAminozuur.put("CTG", "L");
        codonAminozuur.put("CCT", "P");
        codonAminozuur.put("CCC", "P");
        codonAminozuur.put("CCA", "P");
        codonAminozuur.put("CCG", "P");
        codonAminozuur.put("CAT", "H");
        codonAminozuur.put("CAC", "H");
        codonAminozuur.put("CAA", "Q");
        codonAminozuur.put("CAG", "Q");
        codonAminozuur.put("CGT", "R");
        codonAminozuur.put("CGC", "R");
        codonAminozuur.put("CGA", "R");
        codonAminozuur.put("CGG", "R");
        codonAminozuur.put("ATT", "I");
        codonAminozuur.put("ATC", "I");
        codonAminozuur.put("ATA", "I");
        codonAminozuur.put("ATG", "M");
        codonAminozuur.put("ACT", "T");
        codonAminozuur.put("ACC", "T");
        codonAminozuur.put("ACA", "T");
        codonAminozuur.put("ACG", "T");
        codonAminozuur.put("AAT", "N");
        codonAminozuur.put("AAC", "N");
        codonAminozuur.put("AAA", "K");
        codonAminozuur.put("AAG", "K");
        codonAminozuur.put("AGT", "S");
        codonAminozuur.put("AGC", "S");
        codonAminozuur.put("AGA", "R");
        codonAminozuur.put("AGG", "R");
        codonAminozuur.put("GTT", "V");
        codonAminozuur.put("GTC", "V");
        codonAminozuur.put("GTA", "V");
        codonAminozuur.put("GTG", "V");
        codonAminozuur.put("GCT", "A");
        codonAminozuur.put("GCC", "A");
        codonAminozuur.put("GCA", "A");
        codonAminozuur.put("GCG", "A");
        codonAminozuur.put("GAT", "D");
        codonAminozuur.put("GAC", "D");
        codonAminozuur.put("GAA", "E");
        codonAminozuur.put("GAG", "E");
        codonAminozuur.put("GGT", "G");
        codonAminozuur.put("GGC", "G");
        codonAminozuur.put("GGA", "G");
        codonAminozuur.put("GGG", "G");

    }

    /**
     * 
     * @param dnaSeq
     * @return aa, de aminozuursequentie
     * 
     * Van de verschillende reading frame sequenties wordt een protein sequentie
     * gemaakt.
     */
    public String toProtein(String dnaSeq) {
        //Het patroon waaraan het moet voldoen.        
        Pattern patroon = Pattern.compile("[ATGC]{3}");
        Matcher m = patroon.matcher(dnaSeq);
        Matcher match = (Matcher) m;
        String aa = "";
        while (match.find()) {            
            codon = match.group();
            aa += codonAminozuur.get(codon);
        }
        return aa;
    }

    /**
     *
     * @return complem
     * De complementaire strand van de sequentie.
     */
    public String getComplem() {
        return complem;
    }

    /**
     *
     * @return aminozuurReadingFrame1
     * Een aminozuur sequentie van reading frame 1
     */
    public static String getAminozuurReadingFrame1() {
        return aminozuurReadingFrame1;
    }

    /**
     *
     * @return aminozuurReadingFrame2
     * Een aminozuur sequentie van reading frame 2
     */
    public static String getAminozuurReadingFrame2() {
        return aminozuurReadingFrame2;
    }

    /**
     *
     * @return aminozuurReadingFrame3
     * Een aminozuur sequentie van reading frame 3
     */
    public static String getAminozuurReadingFrame3() {
        return aminozuurReadingFrame3;
    }

    /**
     *
     * @return aminozuurReadingFrame_1
     * Een aminozuur sequentie van reading frame -1
     */
    public static String getAminozuurReadingFrame_1() {
        return aminozuurReadingFrame_1;
    }

    /**
     *
     * @return aminozuurReadingFrame_2
     * Een aminozuur sequentie van reading frame -2
     */
    public static String getAminozuurReadingFrame_2() {
        return aminozuurReadingFrame_2;
    }

    /**
     *
     * @return aminozuurReadingFrame_3
     * Een aminozuur sequentie van reading frame -3
     */
    public static String getAminozuurReadingFrame_3() {
        return aminozuurReadingFrame_3;
    }

    /**
     *
     * @return templateComple
     * Een string waarin zowel de sequentie als de complementaire strand zitten.
     */
    public static String getTemplateComple() {
        return templateComple;
    }
}
