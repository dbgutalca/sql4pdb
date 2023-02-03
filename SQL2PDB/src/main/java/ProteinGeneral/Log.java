/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProteinGeneral;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.GZIPInputStream;

/**
 *
 * @author Diego
 */
public class Log {    
    private static String logText = "";
    private static String added = "";
    private static String omited = "";
    private static String sep = (System.getProperty("os.name").contains("Windows")) ? "\\" : "/";
    private static String homeDir = System.getProperty("user.dir")+sep+"Logs";
    
    public static void addText(String s){
        logText = logText.concat(s);
    }
    
    public static void addEntry(String s){
        added = added.concat(s);
    }
    
    public static void addOmited(String s){
        omited = omited.concat(s);
    }
    
    public static void write(){
        writeLog();
        writeAdded();
        writeOmited();
    }
    
    public static void writeClean(){
        writeAdded();
        writeOmited();
        added = "";
        omited = "";
        System.out.println("Writing...");
    }
            
            
    private static void writeLog(){        
        FileWriter file = null;
        PrintWriter pw = null;
        try
        {
            file = new FileWriter(homeDir+sep+"Error Log.txt", true);
            pw = new PrintWriter(file);    
            pw.println("Execution started at "+LocalDateTime.now());
            if (logText.equals("")) {
                pw.println("There is no error");
                pw.println("---------------------------\n");
            }else{
                pw.println(logText); 
                pw.println("---------------------------\n");
            }  
            pw.println("Execution ended at: "+LocalDateTime.now());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {           
           if (null != file)
              file.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }    
    }
    
    
    private static void writeAdded(){
        FileWriter file = null;
        PrintWriter pw = null;
        
        try
        {
            file = new FileWriter(homeDir+sep+"EntriesAdded.txt",true);
            pw = new PrintWriter(file);    
            pw.println(added);                     
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {           
           if (null != file)
              file.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
    
    private static void writeOmited(){
        FileWriter file = null;
        PrintWriter pw = null;
        try
        {
            file = new FileWriter(homeDir+sep+"EntriesOmited.txt",true);
            pw = new PrintWriter(file);    
            pw.println(omited);                                 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {           
           if (null != file)
              file.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
    
    public static ArrayList<String> getAdded(){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        ArrayList<String> added = new ArrayList();
      try {         
         archivo = new File (homeDir+sep+"EntriesAdded.txt");
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
         
         String linea;
         while((linea=br.readLine())!=null){
            added.add(linea);
         }
         return added;
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{         
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
        return null;
    }
    
    
    public static ArrayList<String> getOmited(){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        ArrayList<String> omited = new ArrayList();
      try {         
         archivo = new File (homeDir+sep+"EntriesOmited.txt");
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
         
         String linea;
         while((linea=br.readLine())!=null){
             if (!linea.equals("\n")) {
                 omited.add(linea);
             }            
         }
         return omited;
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{         
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
        return null;
    }
            
}
