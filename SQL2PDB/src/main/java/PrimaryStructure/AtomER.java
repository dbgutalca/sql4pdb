/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrimaryStructure;

/**
 *
 * @author Diego
 */
public class AtomER {
    private String ID;
    private String name;
    private char altLoc;
    private String element;
    private short charge;
    private int model;
    private int serNumber;
    private double x;
    private double y;
    private double z;
    private float occupancy;
    private float tempFactor;
    private String resID;

    public AtomER(String entryID ,String ChainID, int seqNumRes, String name, char altLoc, String element, short charge, int model, int serNumber, double x, double y, double z, float occupancy, float tempFactor) {
        this.ID = entryID+"_"+ChainID+"_"+seqNumRes+"_"+model+"_"+serNumber;
        this.name = name;
        this.altLoc = altLoc;
        this.element = element;
        this.charge = charge;
        this.model = model;
        this.serNumber = serNumber;
        this.x = x;
        this.y = y;
        this.z = z;
        this.occupancy = occupancy;
        this.tempFactor = tempFactor;
        this.resID = entryID+"_"+ChainID+"_"+seqNumRes;
    }
    
    public AtomER(String entryID ,String ChainID, int seqNumRes, String name, char altLoc, String element, short charge, int serNumber, double x, double y, double z, float occupancy, float tempFactor) {
        this.model = 1;
        this.ID = entryID+"_"+ChainID+"_"+seqNumRes+"_"+model+"_"+serNumber;
        if (name.contains("'")) {
            name = name.replaceAll("'", "''");
        }
        this.name = name;
        this.altLoc = altLoc;
        this.element = element;
        this.charge = charge;
        this.resID = entryID+"_"+ChainID+"_"+seqNumRes;
        this.serNumber = serNumber;
        this.x = x;
        this.y = y;
        this.z = z;
        this.occupancy = occupancy;
        this.tempFactor = tempFactor;
    }
    
    public void printAtom(){
        System.out.println("ID: "+ID);
        System.out.println("Name: "+ name);
        System.out.println("AltLoc: "+altLoc);
        System.out.println("Coordinates: ");
        System.out.println("X: "+x+" Y: "+y+" Z: "+z);
        System.out.println("Serial Number: "+serNumber);
    }
    
    public int getSerNum(){
        return serNumber;
    }
    
    public int getModel(){
        return model;
    }
    
    public String toSQL(){
        return "('"+ID+"','"+model+"','"+name+"','"+altLoc+"','"+x+"','"+y+"','"+z+"','"+tempFactor+"','"+occupancy+"','"+charge+"','"+serNumber+"','"+resID+"'),\n ";
    }
            
}
