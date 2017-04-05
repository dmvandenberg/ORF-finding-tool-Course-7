package VindenORF;

import java.util.ArrayList;

/**
 * Deze class zorgt voor de overerving in het script en zorgt ervoor dat alle data opgeslagen is
 * @author merijn
 */
public class Feature {
    private String startcodon;
    private String stopcodon;
    private Integer lengte;
    private static ArrayList<String> startCodon = new ArrayList();
    private static ArrayList<String> stopCodon = new ArrayList();
    private static ArrayList<Integer> lengteArray = new ArrayList();
    
    /**
     * deze methode zorgt ervoor dat het start codon wordt verkregen en wordt opgeslagen in een ArrayList
     * @param start Dit is een Integer met de index waarop de ORF start
     * @param sequentie Dit is de gehele sequentie waarmee het codon wordt verkregen
     * @return
     */
    public String startcodon(Integer start, String sequentie){
        startcodon = sequentie.substring(start , start + 3);
        startCodon.add(startcodon);
        return null;
    }
    
    /**
     * deze methode zorgt ervoor dat het stop codon wordt verkregen en wordt opgeslagen in een ArrayList
     * @param stop Dit is een Integer met de index waarop de ORF stopt
     * @param sequentie Dit is de gehele sequentie waarmee het codon wordt verkregen
     * @return
     */
    public String stopcodon(Integer stop, String sequentie){
        stopcodon = sequentie.substring(stop - 3 , stop);
        stopCodon.add(stopcodon);
        return null;
    }
    
    /**
     * deze methode berekend de lengte van het reading Frame en slaat deze op in een ArrayList
     * @param start Dit is een Integer met de index waarop de ORF start
     * @param stop Dit is een Integer met de index waarop de ORF stopt
     * @return retourneert de lengte van de ORF
     */
    public Integer getLength(Integer Start, Integer Stop){
        lengte  = Stop- Start;
        lengteArray.add(lengte);
        return lengte;
    }
}
