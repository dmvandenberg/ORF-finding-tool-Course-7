package DatabaseConnectie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 * @version 1.0
 * @date 03/30/2017
 * 
 * Deze Class is voor het leggen van een connectie met de MySQL database en het ophalen van informatie hieruit. Doormiddel
 * van het invoeren van de accessie code van een sequentie uit de database kan een gebruiker bepaalde ORF's ophalen. 
 * 
 * Op het moment zijn er geen bekende bugs
 * 
 */
public class Import {
    
    private String database;
    private String gebruiker;
    private String wachtwoord;
    
    /**
     *
     * @param database
     * @param gebruiker
     * @param wachtwoord
     * 
     * constructor
     */
    public Import(String database, String gebruiker, String wachtwoord){
        this.database = database;
        this.gebruiker = gebruiker;
        this.wachtwoord = wachtwoord;
    }
    
    /**
     *
     * @param acc_code
     * @return ArrayList containing strings of all the information about an ORF in the database.
     * 
     * Deze method doet alles wat te maken heeft met het importen. Wanneer de ingevoerde accessie code niet overeenkomt met een 
     * van de headers in de database word een Arraylist met de String "No matches found.." geretourneerd.
     * 
     */
    public ArrayList<String> run(String acc_code){     
        try {
            ArrayList<String> orfs = new ArrayList<>();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(database, gebruiker, wachtwoord);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM input_info");
            boolean check_val = rs.next();
            if (check_val == false) {
                return orfs;
            }
            while (check_val) {
                if (rs.getString("header_seq").contains(acc_code)){
                    String right_id = rs.getString("id_seq");
                    String full_seq = rs.getString("full_seq");
                    rs.close();
                    ResultSet rs2 = stmt.executeQuery("SELECT * FROM orf_info WHERE input_info_id_seq LIKE "+right_id);
                    boolean check_val2 = rs2.next();
                    if (check_val2 == false) {
                        return orfs;
                        }
                    while (check_val2){
                        int id = rs2.getInt("id_orf");
                        int strtpos = rs2.getInt("orf_startpos");
                        int stoppos = rs2.getInt("orf_stoppos");
                        String rframe = rs2.getString("orf_rframe");
                        
                        if (!rframe.contains("-")){
                            String orf_info = "ORF ID:\t" + Integer.toString(id) + "\nReading frame:\t\t" + rframe + "\nStart positie:\t\t" + Integer.toString(strtpos) + "\nStop positie:\t\t" + Integer.toString(stoppos) + "\nDNA sequentie:\t\t" + full_seq.substring(strtpos, stoppos)+"\n";
                            orfs.add(orf_info);
                        }
                        else{
                            String orf_seq = new StringBuilder(full_seq).reverse().substring(stoppos, strtpos);
                            String orf_info = "ORF ID:\t" + Integer.toString(id) + "\nReading frame:\t\t" + rframe + "\nStart positie:\t\t" + Integer.toString(strtpos) + "\nStop positie:\t\t" + Integer.toString(stoppos) + "\nDNA sequentie:\t\t" +orf_seq+"\n";
                            orfs.add(orf_info);
                        }
                        check_val2 = rs2.next();
                    }
                    
                    return orfs;
                }
                else{
                    check_val = rs.next();
                }
            }
            
            orfs.add("No matches found..");
            rs.close();
            stmt.close();
            con.close();
            return orfs;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Export.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<String> empty = new ArrayList<>();
        return empty;
    }
}
