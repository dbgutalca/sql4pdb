/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProteinGeneral;
import SecondaryStructure.Helix;
import SecondaryStructure.Sheet;
    import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;



/**
 *
 * @author Diego
 */



public class GeneralReader {  
    
    private Sheet actualSheet = null;
    private ArrayList<Sheet> sheets = new ArrayList();
    
    
   public void fillSecondaryStructure(ArrayList<Helix> helixes, ArrayList<Sheet> sheet,ArrayList<String> sources, String entryName, ArrayList<SSBond> ssBonds, String url) {
       File archivo = null;
       FileReader fr = null;
       BufferedReader br = null;  

      try {
          System.out.println(url);
         FileInputStream fin = new FileInputStream(url);
         GZIPInputStream gzis = new GZIPInputStream(fin);                  
         archivo = new File (url);
         fr = new FileReader (archivo);
         br = new BufferedReader(new InputStreamReader(gzis));                           
         String line;
         
         while((line=br.readLine())!=null){
             String record = line.substring(0, 6);
             if (record.contains("HELIX")) {
                helixes.add(createHelix(line, entryName));
             }else if(record.contains("SHEET")){
                createSheet(line, entryName,sheet);
             }else if(line.contains("ORGANISM_SCIENTIFIC")){
                sources.add(getSource(line));
             }else if(record.contains("SSBOND")){
                ssBonds.add(createSSBond(line, entryName));
             }
         }          
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
   }
   
   
   private Helix createHelix(String line, String entryName){
       int serNum = Integer.parseInt(line.substring(7, 10).trim());    
       String ID = line.substring(11, 14);       
       String initChainID = String.valueOf(line.charAt(19));       
       int initSeqRes = Integer.parseInt(line.substring(21, 25).trim());    
       String endChainID = String.valueOf(line.charAt(31));
       int endSeqRes = Integer.parseInt(line.substring(33, 37).trim());    
       int helixClass = Integer.parseInt(line.substring(38, 40).trim());    
       String comment = line.substring(40, 70).trim();
       int length = Integer.parseInt(line.substring(71, 76).trim());       
       return new Helix(entryName, initSeqRes, endSeqRes, initChainID, endChainID, ID, helixClass, comment, length,serNum);
   }

   
   private void createSheet(String line, String entryName, ArrayList<Sheet> sheet){
       int strandNum = Integer.parseInt(line.substring(7,10).trim());       
       String ID = line.substring(11, 14);
       int strands = Integer.parseInt(line.substring(14, 16).trim());
       if (strandNum == 1) {
           if (actualSheet != null) {                     
               sheet.add(actualSheet);               
           }
           actualSheet = new Sheet(ID, strands, entryName);
           actualSheet.addStrandInstruction(line);
       }else{
           actualSheet.addStrandInstruction(line);
       }
                  
              
   }
   
   private SSBond createSSBond(String line, String entryName){
       String chainID1 = String.valueOf(line.charAt(15));
       int seqRes1 = Integer.parseInt(line.substring(17, 21).trim());
       String chainID2 = String.valueOf(line.charAt(29));
       int seqRes2 = Integer.parseInt(line.substring(31, 35).trim());
       int seqNum = Integer.parseInt(line.substring(7, 10).trim());
       int sym1 = Integer.parseInt(line.substring(59, 65).trim());
       int sym2 = Integer.parseInt(line.substring(66, 72).trim());
       double length = Double.parseDouble(line.substring(73,78));
       return new SSBond(entryName, chainID1, seqRes1, chainID2, seqRes2, sym1, sym2, length, seqNum);
   }
   
   private String getSource(String line){
       String source = line.substring(31,79).trim(); 
       source = source.replaceAll(";", "");
       return source;
   }
   
   public static void fillDirectory(ArrayList<String> URLs, ArrayList<String> added, String DIR){
       String URL = DIR;       
       File dir = new File(URL);
       String[] dirList = dir.list();       
       String sep = (System.getProperty("os.name").contains("Windows")) ? "\\" : "/";
       for (int i = 0; i < dirList.length; i++) {            
                File subDir = new File(URL+sep+dirList[i]);
                String[] subDirList = subDir.list();                
                for (int j = 0; j < subDirList.length; j++) {
                    if (!added.contains(subDirList[j].substring(3, 7))) {                        
                        URLs.add(URL+sep+dirList[i]+sep+subDirList[j]);
                    }                    
                }   
            
                   
       }       
   }
   
   

}
