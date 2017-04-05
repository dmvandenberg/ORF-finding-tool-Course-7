/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseConnectie;

import VindenORF.VoorspellerORF;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 * @version 1.0
 * @date 03/28/2017
 * 
 * Deze Class is verantwoordelijk voor het connecteren met de database en het erin zetten van gegevens uit deze tool. 
 * 
 * Bekende bugs: Doordat steeds het laatste ID wordt opgehaald uit een tabel en er niet per ORF wordt gecheckt of deze al in de database staat,
 * is het mogelijk om steeds met een volgend ID hetzelfde ORF te exporten.
 */
public class Export {

    private static ArrayList lijstMetORFs = new ArrayList();
    private String database;
    private String gebruiker;
    private String wachtwoord;
    
    /**
     *
     * @param database name of the database that is used
     * @param gebruiker username entered to access the database
     * @param wachtwoord password entered to access the database
     * 
     * constructor
     */
    public Export(String database, String gebruiker, String wachtwoord){
        this.database = database;
        this.gebruiker = gebruiker;
        this.wachtwoord = wachtwoord;
    }
    /**
     * 
     * @param id_type the type of id that is used in the table that's chosen
     * @param table_name name of the table in the MySQL database
     * @return int highest_id, an integer that indicates the last id put into the database.
     * 
     * Deze method haalt het laatste id uit een tabel uit de database zodat er nieuwe id's aan toegevoegd kunnen worden op ieder moment.
     * 
     */
    private int getHighestID(String id_type, String table_name) {
        int highest_id = 0;
        ArrayList<Integer> id_list = new ArrayList<Integer>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(database, gebruiker, wachtwoord);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from " + table_name);
            boolean check_val = rs.next();
            if (check_val == false) {
                return 0;
            }
            while (check_val) {
                id_list.add(rs.getInt(id_type));
                check_val = rs.next();
            }

            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Export.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.max(id_list);
    }

    /**
     *
     * @param seq the entire sequence loaded from the user given file
     * @param head the header of the user given sequence
     * @return boolean that tells the GUI whether the exporting process is done or not.
     * 
     * Deze method exporteerd de informatie uit deze tool naar de MySQL database. Als het goed is gegaan retourneerd deze method true
     */
    public boolean ORF(String seq, String head) {
        VoorspellerORF lijst = new VoorspellerORF();
        lijstMetORFs = lijst.getOpenReadingFrameLijst();
        int id_seq = getHighestID("id_seq", "input_info") + 1;
        int id_orf = getHighestID("id_orf", "orf_info");

        String input_orf_stmt = "INSERT INTO input_info VALUES (" + id_seq + ",'" + head + "','" + seq + "')";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(database, gebruiker, wachtwoord);
            Statement stmt = con.createStatement();
            stmt.executeUpdate(input_orf_stmt);

            for (Object temp : lijstMetORFs) {
                id_orf++;
                String[] orf = temp.toString().split("/");
                stmt.executeUpdate("INSERT INTO orf_info VALUES (" + id_orf + "," + orf[1] + "," + orf[2] + "," + orf[3] + "," + id_seq + ")");
            }

            con.close();
            return true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Export.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Export.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
