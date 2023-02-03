/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import ProteinGeneral.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Diego
 */
public class PostgreSQL {
    private String DbURL;
    private String user;
    private String pass;

    public PostgreSQL(String DbURL, String user, String pass) {
        this.DbURL = DbURL;
        this.user = user;
        this.pass = pass;
    }
    
    public void makeInsert(String s, String URL){        
        Connection DB = null;
        Statement st = null;                
        try {            
            DB = DriverManager.getConnection("jdbc:postgresql://"+this.DbURL, this.user, this.pass);
            st = DB.createStatement();                
            st.execute(s);   
            System.out.println("Added");
            Log.addEntry(URL+"\n");
        } catch (Exception e) { 
            Log.addOmited(URL+"\n");
            System.out.println(e);
        }
    }
    
    
    public ArrayList<String> getAdded(){
        ArrayList<String> added = new ArrayList<>();
        Connection DB = null;
        Statement st = null;                
        try {            
            DB = DriverManager.getConnection("jdbc:postgresql://"+this.DbURL, this.user, this.pass);
            st = DB.createStatement();           
            System.out.println("Searching for added entries...");
            ResultSet result = st.executeQuery("SELECT * FROM protein");
            while(result.next()){
                added.add(result.getString("prot_id").toLowerCase());
            }
            System.out.println("Done.");
        } catch (Exception e) {             
            System.out.println(e);
        }
        return added;
    }
    /*
    public void reCalculateSize(){
        ArrayList<String> added = new ArrayList<>();
        Connection DB = null;
        Statement st = null;                
        try{
            DB = DriverManager.getConnection("jdbc:postgresql://"+this.DbURL, this.user, this.pass);
            st = DB.createStatement();  
            System.out.println("Recalculating table size...");
            
            ResultSet result = st.executeQuery("SELECT count(*) FROM protein");
            result.next();
            System.out.println(result.getString("count"));
        }catch(Exception e){
            System.out.println(e);
        }                
    }*/
    
    public void createNewDatabase(){
        Connection DB = null;        
        System.out.println("Creating new database.");
        try {
            DB = DriverManager.getConnection("jdbc:postgresql://"+this.DbURL, this.user, this.pass);            
            DBCreator newDB = new DBCreator(DB, DbURL, user, pass);
            newDB.startCreation();
        } catch (Exception e) {
            System.out.println("Error: "+e);
        }
        
        
    }
}
