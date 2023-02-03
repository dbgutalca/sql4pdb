/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrimaryStructure;

import ProteinGeneral.Log;
import java.util.ArrayList;

/**
 *
 * @author Diego
 */
public class ChainER {
    private String ID;
    private String chainID;
    private ArrayList<ResidueInstance> residues = new ArrayList<>();
    private ArrayList<Heterogen> heterogens = new ArrayList<>();
    private String entryName;
    
    
    public ChainER(String entryID, String ChainID, ArrayList<ResidueInstance> residues, String entryName){
        this.ID = entryID+"_"+ChainID;
        this.chainID = ChainID;
        this.entryName = entryName;
        this.residues = residues;        
        
    }
    
    public ChainER(String ChainID, String entryID ){
        this.ID = entryID+"_"+ChainID;
        this.entryName = entryID;
        this.chainID = ChainID;
    }
    
    public String getID(){
        return this.ID;
    }
    
    public ResidueInstance searchRes(int seqNum){
        for (ResidueInstance r : residues) {
            if (r.getPosition() == seqNum) {
                return r;
            }
        }
        return null;
    }
    
    public boolean residueIsMissing(int seqNum){
        for (ResidueInstance r : residues) {
            if (r.getPosition() == seqNum) {
                return false;
            }
        }
        return true;
    }
    public void printChain(){
        if (!residues.isEmpty()) {
            System.out.println("Chain ID: " +ID);
            int j = 1;
            for (int i = 0; i < residues.size(); i++) {            
                System.out.print(residues.get(i).getAmino().getName3()+ " ");
                if (i == 13*j) {
                    j++;
                    System.out.println("");
                }                                    
            }  
            System.out.println("");
        }        
    }
    
    public void printHet(){
        if (!heterogens.isEmpty()) {
            System.out.println("Chain ID: "+ID);
            for (Heterogen h : heterogens) {
                h.print();
            }
        }               
    }
    
    public boolean hasHet(){        
        return !heterogens.isEmpty();
    }
    
    public boolean hasRes(){
        return !residues.isEmpty();
    }
    
    public void printResidue(int index){
        if (residues.isEmpty()) {
            heterogens.get(index).print();
        }else{
            residues.get(index).print();
        }        
    }
    
    
    public void setHeterogen(ArrayList<Heterogen> het){
        this.heterogens = het;
        this.ID = ID.concat("_HET");
    }
    
    public String getChainID(){
        return this.chainID;
    }
    public ResidueInstance getAmino(int index){
        return residues.get(index);
    }

    public ArrayList<ResidueInstance> getResidues() {
        return residues;
    }

    public ArrayList<Heterogen> getHeterogens() {
        return heterogens;
    }
    
    private int getLength(){
        if (!residues.isEmpty()) {            
            return residues.size();
        }
        return heterogens.size();
    }
    
    public void addArrayList(ArrayList<Heterogen> hets){
        for (Heterogen h : hets) {
            heterogens.add(h);
        }
    }
    
    public String toSQL(){
        return "('"+ID+"','"+entryName+"','"+chainID+"','"+getLength()+"'),\n ";
    }
    
    public String getSubChain(String chainID, int initSeqNum, int endSeqNum, String subChain){                 
        try {
            if (this.chainID.equals(chainID) && this.heterogens.isEmpty()) {                                                       
                    for (int i = initSeqNum ; i < endSeqNum; i++) {      
                        if (!residueIsMissing(i)) {
                            subChain = subChain.concat(searchRes(i).getAmino().getName3() + "-");                                                
                        }
                            
                    }                                
            }        
        } catch (Exception e) {
            subChain = "ERROR";
            System.out.println(e);
            Log.addText("Error in: "+entryName + "\n");            
            Log.addText(e.getMessage()+"\n");
            Log.addText("An error with residue number has ocurred\n");
            return subChain;
        }
        
        return subChain;
    }
    
}
