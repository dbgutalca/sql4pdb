/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import ProteinGeneral.GeneralReader;
import ProteinGeneral.Log;
import java.util.ArrayList;
import ProteinGeneral.pdb;
import org.biojava.nbio.structure.StructureException;

/**
 * 
 * @author Diego
 */
public class OptionManager {
    private final ConfReader conf = new ConfReader();
    private final PostgreSQL postgres = new PostgreSQL(conf.getDbURL(), conf.getUser(), conf.getPass());
    
    public void execute(int option) throws StructureException{                
        switch (option) {
            case 1:
                createPDBDatabase();
                break;
            case 2:
                executeParser();
                break;
            case 3:
                createDatabaseList();
                break;
            case 4:
                createSampleDatabase();
                break;            
        }        
    }
    
    /**
     * Normal execution of PDB2SQL.
     */
    private void executeParser(){
        ArrayList<String> URLs = new ArrayList();
        ArrayList<String> added = postgres.getAdded();
        ArrayList<String> omited = Log.getOmited();
        GeneralReader.fillDirectory(URLs, added, conf.getLocalDirectoryPDB()); 
        int i = 0;
        int mult = 1;
        for (String s : URLs) {
            if (!isOmited(s, omited)) {
                System.out.println("Parsing: "+ s);
                System.out.println("Count: "+i+"/"+URLs.size());
                pdb pdb = new pdb(s, postgres);
                pdb.leerPDB();
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }   
            if (i == mult*500) {
                Log.writeClean();
                mult++;
               }
            i++;
        }    
        //postgres.reCalculateSize();
        Log.write();    
    }
    
    private void createOmitedList(){
        ArrayList<String> URLs = new ArrayList();
        ArrayList<String> added = postgres.getAdded();
        GeneralReader.fillDirectory(URLs, added, conf.getLocalDirectoryPDB()); 
        int i = 0;
        int omited = 0;
        for (String s : URLs) {            
                System.out.println("Creating omited list: ");
                System.out.println("Count: "+i+"/"+URLs.size());
                pdb pdb = new pdb(s, postgres);
                omited = pdb.createOmitedList() + omited;
                i++;                
                System.out.print("\033[H\033[2J");
                System.out.flush();                
           }  
        Log.addText("Total of "+omited+ " Entries omited");
        Log.write();      
    }

    /**
     * Parse PDB by a given list.
     */
    private void createDatabaseList() throws StructureException{
        try{
            for (int i = 0; i < conf.getPDBs().length; i++) {            
                pdb pdb = new pdb(postgres);
                pdb.readGivenPDB(conf.getPDBs()[i]);
                System.out.print("\033[H\033[2J");
                System.out.flush();      
            }
            Log.write();    
            System.out.println("Proteins added succesfully!.");
        }catch(Exception e){
            System.out.println(e);
        }    
        
        
    }
    
   /**
    * Creates a sample data base with 11 PDB entries.
    * @throws StructureException 
    */
    
    private void createSampleDatabase() throws StructureException{
        String[] sample = {"7ENG", "7EHG", "1AFR", "2XP0", "1XP3", "1AQ2", "1HPM", "1GZG", "1BUP", "1SVT", "1QM4"};
        try{
            for (int i = 0; i < sample.length; i++) {
                pdb pdb = new pdb(postgres);
                pdb.readGivenPDB(sample[i]);
                System.out.print("\033[H\033[2J");
                System.out.flush();                  
                Log.write();            
            }
            System.out.println("Sample database correctly created with the followings proteins:");
            System.out.println("7ENG, EHG, 1AFR, 2XP0, 1XP3, 1AQ2, 1HPM, 1GZG, 1BUP, 1SVT, 1QM4");
        }catch(Exception e){
            System.out.println(e);
            System.out.println("Probably the sample entries already exists in the database.");
        }
        
        
        
    }
    
    private void createPDBDatabase(){
        postgres.createNewDatabase();
    }
    
    
    private boolean isAdded(String URL, ArrayList<String> added){
        return added.contains(URL);
    }
    
    private boolean isOmited(String URL, ArrayList<String> omited){
        return omited.contains(URL);
    }
    

}
