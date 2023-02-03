/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SecondaryStructure;

import PrimaryStructure.ChainER;
import PrimaryStructure.ResidueInstance;
import java.util.ArrayList;

/**
 *
 * @author Diego
 */
public class Helix {
    private final String ID;
    private final String helixClass;
    private final String resInit;    
    private final String resEnd;
    private final int initResSeqNum;
    private final int endResSeqNum;    
    private final String comment;
    private final String chainID;
    private final String entryName;
    private String seqRes;
    private final int length;    

    public Helix(String entryName, int initResSeq,int endResSeq,String initChainID,String endChainID, String ID, int helixClass, String comment, int length, int serNum) {
        this.ID = entryName+"_"+ID+"_"+serNum;
        this.helixClass = getHelixClass(helixClass);
        this.resInit = entryName+"_"+initChainID+"_"+initResSeq;
        this.resEnd = entryName+"_"+endChainID+"_"+endResSeq;
        this.initResSeqNum = initResSeq;
        this.endResSeqNum = endResSeq;
        this.comment = comment;
        this.length = length;      
        this.chainID = initChainID;
        this.entryName = entryName;        
    }
    
    public void printAll(){
        System.out.println("ID: "+ ID);        
        System.out.println("Initial Residue: "+resInit );
        System.out.println("End Residue: "+resEnd);
        System.out.println("Comment: "+comment);
        System.out.println("Length: "+length);
        System.out.println("HelixClass: "+ helixClass);
        System.out.println("Chain ID: "+chainID);
        System.out.println("-------------------------------------\n");
    }
    
    private String getHelixClass(int helixClass){
        switch (helixClass) {
            case 1 :
                return "Right-handed alpha";
            case 2 :
                return "Right-handed omega";
            case 3 :
                return "Right-handed pi";
            case 4 :
                return "Right-handed gamma";
            case 5 :
                return "Right-handed 3 - 10";
            case 6 :
                return "Left-handed alpha";
            case 7 :
                return "Left-handed omega";
            case 8 :
                return "Left-handed gamma";
            case 9 :
                return "2 - 7 ribbon/helix";
            case 10 :
                return "Polyproline";
            default :
                return "Right-handed alpha";
        }        
    }
    
    public String getHelixChain(){        
        return this.chainID;
    }
    
    public int getInitRes(){
        return this.initResSeqNum;
    }
    
    public int getEndRes(){
        return this.endResSeqNum;
    }
        
    public String toSQL(){
        return "('"+ID+"','"+comment+"','"+helixClass+"','"+length+"','"+resInit+"','"+resEnd+"','"+seqRes+"','"+entryName+"'),\n ";
    }
            
    public void setSeqRes(String seqRes){
        this.seqRes = seqRes;                
    }
    
    public String getID(){
        return this.ID;
    }
    
    public ArrayList<String> idResidueSubChain(ArrayList<ChainER> chains){        
        ArrayList<String> residueSubChain = new ArrayList();   
        
        for (ChainER c : chains) {            
            if (c.getChainID().equals(chainID) && !c.hasHet()) {
                for ( int i = initResSeqNum; i<endResSeqNum ; i++) {     
                    if (!c.residueIsMissing(i)) {
                        residueSubChain.add(c.searchRes(i).getID());            
                    }
                    
                }
            }
        }
        
        return residueSubChain;
    }
}
