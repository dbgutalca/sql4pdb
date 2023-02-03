/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SecondaryStructure;

import PrimaryStructure.ChainER;
import java.util.ArrayList;

/**
 *
 * @author Diego
 */
public class Strand {
    private String entryName;
    private String ID;
    private String initRes;
    private String initResName;
    private int initSeqResNum;   
    private String endRes;
    private String endResName;
    private int endSeqResNum;
    private int sense;
    private String currRes = "";
    private String currResName = "";
    private String prevRes = "";    
    private String prevResName = "";
    private String currAtom = "";
    private String prevAtom = "";
    private String sheetID;
    private String seqRes;
    private String chainID;
    
    public Strand(String ID, int numStrand, String entryName, String chainID,
            int initSeqRes, int endSeqRes, int sense, String initResName, String endResName){
       this.ID = ID+"_"+numStrand;
       this.initRes = entryName+"_"+chainID+"_"+initSeqRes;
       this.initResName = initResName;
       this.endRes = entryName+"_"+chainID+"_"+endSeqRes;
       this.endResName = endResName;
       this.sense = sense;
       this.sheetID = ID;
       this.initSeqResNum = initSeqRes;
       this.endSeqResNum = endSeqRes;
       this.chainID = chainID;
       this.entryName = entryName;
    }
    
    public Strand(String ID, int numStrand, String entryName, String chainID,
            int initSeqRes, int endSeqRes, int sense,int currSeqRes, int prevSeqRes, 
            String currAtom, String prevAtom, String initResName, String endResName, 
            String currResName, String prevResName){
       this.ID = ID+"_"+numStrand;
       this.initRes = entryName+"_"+chainID+"_"+initSeqRes;
       this.initResName = initResName;
       this.endRes = entryName+"_"+chainID+"_"+endSeqRes;
       this.endResName = endResName;
       this.sense = sense;
       this.currRes = entryName+"_"+chainID+"_"+currSeqRes;
       this.currResName = currResName;
       this.prevRes = entryName+"_"+chainID+"_"+prevSeqRes;
       this.prevResName = prevResName;
       this.currAtom = currAtom;
       this.prevAtom = prevAtom;  
       this.sheetID = ID;
       this.initSeqResNum = initSeqRes;
       this.endSeqResNum = endSeqRes;
       this.chainID = chainID;
       this.entryName = entryName;
    }
    
    public void printStrand(){
        System.out.println("ID: "+ID);
        System.out.println("Init Res: "+initRes);
        System.out.println("Name: "+initResName);
        System.out.println("End Res: "+endRes);
        System.out.println("Name: "+endResName);
        System.out.println("Curr Res: "+currRes);
        System.out.println("Curr Atom: "+currAtom);
        System.out.println("Prev Res: "+prevRes);
        System.out.println("Prev Atom: "+prevAtom);
    }

    public void setSeqRes(String seqRes) {
        this.seqRes = seqRes;
    }
           
    public String getChain(){
        return chainID;
    }
    
    public String toSQL(){
        return "('"+ID+"','"+sheetID+"','"+initRes+"','"+endRes+"','"+sense+"','"+currRes+"','"+prevRes+"','"+currAtom+"','"+prevAtom+"','"+seqRes+"'),\n ";
    }

    public int getInitSeqResNum() {
        return initSeqResNum;
    }

    public int getEndSeqResNum() {
        return endSeqResNum;
    }
    
    public String getID(){
        return this.ID;
    }
   
    
    public ArrayList<String> idResidueSubChain(ArrayList<ChainER> chains){   
        ArrayList<String> residueSubChain = new ArrayList();   
        for (ChainER c : chains) {            
            if (c.getChainID().equals(chainID) && !c.hasHet()) {                                  
                for (int i = initSeqResNum; i<endSeqResNum ; i++) {   
                    if (!c.residueIsMissing(i)) {
                        residueSubChain.add(c.searchRes(i).getID());            
                    }
                    
                }
            }
        }
        
        return residueSubChain;     
    }
    
}
