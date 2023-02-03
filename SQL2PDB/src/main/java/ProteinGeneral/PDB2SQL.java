/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProteinGeneral;

import Tools.ConfReader;
import Tools.Downloader;
import Tools.OptionManager;
import Tools.PostgreSQL;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Scanner;
import org.biojava.nbio.structure.StructureException;

/**
 *
 * @author Diego
 */
public class PDB2SQL {
    public static void main(String[] args) throws StructureException, MalformedURLException, IOException {              
        Scanner scan = new Scanner(System.in);           
        OptionManager opt = new OptionManager();        
        opt.execute(menu(scan));
    }
    
    
    public static int menu(Scanner scan){
        int valid = 0;
        do {
            System.out.println("Select an option: ");
            System.out.println("1.- Create a new PDB database.");
            System.out.println("2.- To start PDB - SQL conversion. ");
            System.out.println("3.- Fill database by a given list. ");
            System.out.println("4.- Create a sample database.");
            
            valid = scan.nextInt();
        } while (valid < 1 || valid > 4);
        return valid;
    }
    
    public static void firstTimeExecution(ArrayList<String> URLs){                 
        int i = 0;
        int omited = 0;
        for (String s : URLs) {            
                System.out.println("Creating omited list: ");
                System.out.println("Count: "+i+"/"+URLs.size());
                //pdb pdb = new pdb(s, p);
                //omited = pdb.createOmitedList() + omited;
                i++;                
                System.out.print("\033[H\033[2J");
                System.out.flush();                
           }  
        //Log.addText("Total of "+omited+ " Entries omited");
        Log.write();       
    }
}
