/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrimaryStructure;

import java.util.ArrayList;

/**
 *
 * @author Diego
 */
public final class ResidueInstance {
    private Amino residue;    
    private final String ID;
    private final int seqPosition;
    private ArrayList<AtomER> atoms = new ArrayList<AtomER>();
    private String entryName;
    private String chainID;
    
    public ResidueInstance(String entryID, String chainID ,String name, int seqPosition, ArrayList<AtomER> atoms, boolean undefined){        
        this.atoms = atoms;
        setAmino(name);
        this.seqPosition = seqPosition;
        if (undefined) {
            this.ID = entryID+"_"+chainID+"_"+"UNDEFINED_"+seqPosition;        
        }else{
            this.ID = entryID+"_"+chainID+"_"+seqPosition;        
        }                    
        this.entryName = entryID;
        this.chainID = entryID+"_"+chainID;        
    }
               
    public void setAmino(String id){        
        this.residue = ResidueFactory.getResiduo(id);
    }   
    
    public Amino getAmino(){
        return residue;
    }
    
    
    public void print(){
        System.out.println(ID);
        System.out.println(residue.getAll());
    }
    
    
    public String getID(){
        return this.ID;
    }
            
    public int getPosition(){
        return seqPosition;
    }
            
    
    public ArrayList<AtomER> getAtoms(){
        return atoms;
    }
    
    public void printAtomSize(){
        System.out.println(atoms.size());
    }
    
    public AtomER getAtom(int index){
        return atoms.get(index);
    }
    
    public boolean hasAtom(){        
        return !atoms.isEmpty();
    }
    
    private String getAminoID(){
        return residue.getName3();
    }
    
    public String toSQL(){
        return "('"+ID+"','"+seqPosition+"','"+getAminoID()+"','"+chainID+"'),\n ";
    }
    
}
