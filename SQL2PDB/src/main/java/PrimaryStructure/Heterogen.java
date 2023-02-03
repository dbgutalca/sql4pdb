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
public class Heterogen{
    private String name3;
    private String name;
    private String synonims;
    private String formul;
    private String ID;
    private int seqNum;
    private String chainID;
    private ArrayList<HetAtm> hetAtms = new ArrayList();

    public Heterogen(String name, String synonims, String formul, String entryID, String ChainID,  int seqNum, String name3, ArrayList<HetAtm> hetAtms) {
        if (name.contains("'")) {
            name = name.replaceAll("'", "''");
        }
        this.name = name;
        if (synonims.contains("'")) {
            synonims = synonims.replaceAll("'", "''");
        }
        this.synonims = synonims;
        this.formul = formul;
        this.ID = entryID+"_"+ChainID+"_"+seqNum;
        this.seqNum = seqNum;
        this.name3 = name3;
        this.hetAtms = hetAtms;
        chainID = entryID+"_"+ChainID+"_HET";
    }

    public String getName3() {
        return name3;
    }

    public String getName() {
        return name;
    }

    public String getSynonims() {
        return synonims;
    }

    public String getFormul() {
        return formul;
    }

    public String getID() {
        return ID;
    }
    
    public void print(){
        System.out.println("3 letter name: "+ name3);
        System.out.println("Name: "+name);
        System.out.println("Synonims: "+synonims);
        System.out.println("Formul: "+formul);
        System.out.println("ID: "+ID);
    }

    public ArrayList<HetAtm> getHetAtms() {
        return hetAtms;
    }
    
    public String toSQL(){
        return "('"+ID+"','"+name+"','"+formul+"','"+seqNum+"','"+name3+"','"+synonims+"','"+chainID+"'),\n ";
    }
    
}

    