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
public class HetAtm {
    private String ID;
    private int serNum;
    private String name;
    private char altLoc;
    private double x;
    private double y;
    private double z;
    private float occupancy;
    private float tempFactor;
    private String element;
    private short charge;
    private int model;
    private String hetID;

    public HetAtm(String entryID ,String ChainID, int seqNumRes, int serNum, String name, char altLoc, double x, double y, double z, float occupancy, float tempFactor, String element, short charge, int model) {
        this.ID = entryID+"_"+ChainID+"_"+seqNumRes+"_"+model+"_"+serNum;
        this.serNum = serNum;
        this.name = name;
        this.altLoc = altLoc;
        this.x = x;
        this.y = y;
        this.z = z;
        this.occupancy = occupancy;
        this.tempFactor = tempFactor;
        this.element = element;
        this.charge = charge;
        this.model = model;
        this.hetID = entryID+"_"+ChainID+"_"+seqNumRes;
    }
    
    public HetAtm(String entryID ,String ChainID, int seqNumRes, int serNum, String name, char altLoc, double x, double y, double z, float occupancy, float tempFactor, String element, short charge) {
        this.model = 1;
        this.ID = entryID+"_"+ChainID+"_"+seqNumRes+"_"+model+"_"+serNum;
        this.serNum = serNum;
        if (name.contains("'")) {
            name = name.replaceAll("'", "''");
        }
        this.name = name;
        this.altLoc = altLoc;
        this.x = x;
        this.y = y;
        this.z = z;
        this.occupancy = occupancy;
        this.tempFactor = tempFactor;
        this.element = element;
        this.charge = charge;
        this.hetID = entryID+"_"+ChainID+"_"+seqNumRes;
    }   
    
    public String toSQL(){
        return "('"+ID+"','"+model+"','"+name+"','"+altLoc+"','"+x+"','"+y+"','"+z+"','"+tempFactor+"','"+occupancy+"','"+charge+"','"+serNum+"','"+hetID+"'),\n ";
    }
}
