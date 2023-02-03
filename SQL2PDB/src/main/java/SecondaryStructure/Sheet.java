/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SecondaryStructure;

import java.util.ArrayList;

/**
 *
 * @author Diego
 */
public class Sheet {
    private String ID;
    private int numStrands;
    private String entryName;
    private ArrayList<Strand> strands = new ArrayList();    
    private ArrayList<String> strandInstruction = new ArrayList();
    
    public Sheet(String ID, int numStrands, String entryName) {
        this.ID = entryName+"_"+ID;
        this.numStrands = numStrands;
        this.entryName = entryName;
    }    

    public void addStrandInstruction(String instruction){
        this.strandInstruction.add(instruction);
    }
    
    public void printIns(){
        for (String s : strandInstruction) {
            System.out.println(s);
        }
    }
    
    public void createStrands(){
        for (String s : strandInstruction) {            
            int sense = 0;
            int numStrand = Integer.parseInt(s.substring(7, 10).trim()); 
            String initResName = s.substring(17, 20);
            String ChainID = String.valueOf(s.charAt(21));
            int initSeqRes = Integer.parseInt(s.substring(22, 26).trim());    
            String endResName = s.substring(28,31);
            int endSeqRes = Integer.parseInt(s.substring(33, 37).trim());    
            if (!"".equals(s.substring(38, 40).trim())) {
                sense = Integer.parseInt(s.substring(38, 40).trim());  
            }
            
            if (!"".equals(String.valueOf(s.substring(50, 54).trim()))) {                                
                String currAtom = s.substring(41,45);
                String currResName = s.substring(45, 48);                
                int currSeqRes = Integer.parseInt(s.substring(50, 54).trim());    
                String prevAtom = s.substring(56, 60);
                String prevResName = s.substring(60,63 );
                int prevSeqRes = Integer.parseInt(s.substring(65, 69).trim());
                strands.add(new Strand(ID, numStrand, entryName, ChainID, 
                    initSeqRes, endSeqRes, sense, currSeqRes, prevSeqRes, currAtom,
                    prevAtom, initResName, endResName, currResName, prevResName));
            }else{
                strands.add(new Strand(ID, numStrand, entryName, ChainID, initSeqRes, endSeqRes, sense, initResName, endResName));
            }                            
        }
    }

    public ArrayList<Strand> getStrands() {
        return strands;
    }
    
    
    
    public void printStrands(){
        for (Strand s : strands) {
            s.printStrand();
        }
    }
    
    public String toSQL(){
        return "('"+ID+"','"+numStrands+"','"+entryName+"'),\n ";
    }
    
    
}
