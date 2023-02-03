/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Diego
 */
public class DBCreator {
    private Connection DB;
    private final String NAME = "pdb_database";
    private String URL;
    private String user;
    private String pass;

    public DBCreator(Connection DB, String URL, String user, String pass) {
        this.DB = DB;
        this.URL = URL;
        this.user = user;
        this.pass = pass;
    }
    
    public void startCreation() throws SQLException{
         createDatabase(DB);
         createTables(DB);
         System.out.println("Database created, use the following URL in configuration.conf:" +URL+NAME);
    }
    
    private void createDatabase(Connection DB) throws SQLException{
        try {
            Statement st = DB.createStatement();
            st.execute("CREATE DATABASE "+NAME);
            System.out.println("Database "+NAME+" created.");
        } catch (Exception e) {
            System.out.println("Error: " +e);
        }                
    }
    
    private void createTables(Connection DB){
        try{
            Connection pdbDB = DriverManager.getConnection("jdbc:postgresql://"+this.URL+NAME, this.user, this.pass);  
            Statement st = pdbDB.createStatement();
            st.execute(SQLtables.getProtTable());
            st.execute(SQLtables.getKeywords());
            st.execute(SQLtables.getProtSource());
            st.execute(SQLtables.getProtChain());
            st.execute(SQLtables.getStandardAmino());
            st.execute(SQLtables.getResidue());
            st.execute(SQLtables.getAtom());
            st.execute(SQLtables.getHet());
            st.execute(SQLtables.getHetAtm());
            st.execute(SQLtables.getHelix());
            st.execute(SQLtables.getHelixSubChain());
            st.execute(SQLtables.getSheet());
            st.execute(SQLtables.getStrand());
            st.execute(SQLtables.getStrandSubChain());
            st.execute(SQLtables.getSSBond());
            st.execute(SQLtables.fillStandardAmino());
            System.out.println("Tables created.");
        }catch(Exception e){
            System.out.println("Error:" +e);
        }
    }
}
