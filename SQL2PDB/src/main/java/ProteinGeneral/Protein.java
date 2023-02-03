/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProteinGeneral;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Diego
 */
public class Protein {
    private String ID;
    private String title;
    private String depDate;
    private String classification;
    private List<String> keywords;
    private List<String> source;
    private LocalDate insDate = LocalDate.now();
    private int numHet;
    private int numHelix;
    private int numSheet;
    private int numCoord;
    private int numSeqRes;

    public Protein(String ID, String title, String depDate, String classification) {
        this.ID = ID;
        if (title.contains("'")) {
            title = title.replaceAll("'", "''");
        }
        this.title = title;
        this.depDate = depDate;
        if (classification.contains("'")) {
            classification = classification.replaceAll("'", "''");
        }
        this.classification = classification;
                     
    }

    public void setNumHet(int numHet) {
        this.numHet = numHet;
    }

    public void setNumHelix(int numHelix) {
        this.numHelix = numHelix;
    }

    public void setNumSheet(int numSheet) {
        this.numSheet = numSheet;
    }

    public void setNumCoord(int numCoord) {
        this.numCoord = numCoord;
    }

    public void setNumSeqRes(int numSeqRes) {
        this.numSeqRes = numSeqRes;
    }
    
    public void printMaster(){
        System.out.println("ID: "+ID);
        System.out.println("Title: "+title);
        System.out.println("Residues: "+numSeqRes);
        System.out.println("Heterogen: "+numHet);
        System.out.println("Coords: "+numCoord);
        System.out.println("Helix: "+numHelix);
        System.out.println("Sheet: "+numSheet);
        System.out.println("Source: "+source);
        System.out.println("Keywords: "+keywords);
    }
    
    public String toSQL(){        
        return "('"+ID+"','"+title+"','"+depDate+"','"+classification+"','"+numHet
                +"','"+numHelix+"','"+numCoord+"','"+numSeqRes+"','"+numSheet+"','"+insDate+"'),\n ";
    }
    
}
