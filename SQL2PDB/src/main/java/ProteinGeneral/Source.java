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
public class Source {
    private String source;
    private String entryID;

    public Source(String source, String entryID) {
        if (source.contains("'")) {
            source = source.replaceAll("'", "''");
        }
        this.source = source;
        this.entryID = entryID;
    }
    
    public String toSQL(){
        return "('"+source+"','"+entryID+"'),\n ";
    }
}
