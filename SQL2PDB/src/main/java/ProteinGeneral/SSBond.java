/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProteinGeneral;

/**
 *
 * @author Diego
 */
public class SSBond {
    private String ID;
    private String res1;
    private String res2;
    private int sym1;
    private int sym2;
    private double length;
    
    public SSBond(String entryName, String chainID1, int res1, String chainID2, int res2, int sym1, int sym2, double length, int seqNum){
        this.ID = entryName+"_"+seqNum;
        this.res1 = entryName+"_"+chainID1+"_"+res1;
        this.res2 = entryName+"_"+chainID2+"_"+res2;
        this.sym1 = sym1;
        this.sym2 = sym2;      
        this.length = length;     
        
    }
    
    public String toSQL(){
        return "('"+ID+"','"+res1+"','"+res2+"','"+sym1+"','"+sym2+"','"+length+"'),\n ";
    }
    
    public void print(){
        System.out.println("ID: "+ID);
        System.out.println("Res1: "+res1);
        System.out.println("Res2: "+res2);
        System.out.println("sym1: "+sym1);
        System.out.println("sym2: "+sym2);
        System.out.println("length: "+length);
    }
}
