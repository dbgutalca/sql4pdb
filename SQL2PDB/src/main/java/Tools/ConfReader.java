/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Reads the configuration file
 * @author Diego
 */
public class ConfReader {
    private String dbURL;    
    private String[] PDBs;
    private String user;
    private String pass;
    private String localDirectoryPDB;
    private static String sep = (System.getProperty("os.name").contains("Windows")) ? "\\" : "/";
    private int size;

    public ConfReader() {
        read();
    }   
    
    private void read(){
        File file = null;
        FileReader fr = null;
        BufferedReader br = null;        
        try {
            file = new File (System.getProperty("user.dir")+sep+"configuration.conf");
            fr = new FileReader (file);
            br = new BufferedReader(fr);            
            this.dbURL = br.readLine().split("=")[1].trim();
            this.user = br.readLine().split("=")[1].trim();
            this.pass = br.readLine().split("=")[1].trim();            
            this.PDBs = readGivenId(br); 
            this.localDirectoryPDB = br.readLine().split("=")[1].trim();
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
        }
    }
    
    private String[] readGivenId(BufferedReader br) throws IOException{
        String[] IDS = br.readLine().split("=")[1].trim().replace("[", "").replace("]", "").split(",");
            for (int i = 0; i < IDS.length; i++) {
                IDS[i] = IDS[i].trim().toUpperCase();                
            }
        return IDS;
    }
    
    public String getDbURL() {
        return dbURL;
    }


    public String[] getPDBs() {
        return PDBs;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public int getSize() {
        return size;
    }

    public String getLocalDirectoryPDB() {
        return localDirectoryPDB;
    }
    
}
