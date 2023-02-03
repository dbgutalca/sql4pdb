/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SecondaryStructure;

import java.util.ArrayList;

/**
 *
 * @author Diego
 */
public class SecondarySubChain {
    private final String SecondaryStructureID;
    private final String residueID;

    public SecondarySubChain(String SecondaryStructureID, String residueID) {
        this.SecondaryStructureID = SecondaryStructureID;
        this.residueID = residueID;
    }
    
    public String toSQL(){
        return "('"+SecondaryStructureID+"','"+residueID+"'),\n "; 
    }        
}
