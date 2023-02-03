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
public class KeyWord {
    private String keyWord;
    private String entryID;

    public KeyWord(String keyWord, String entryID) {
        if (keyWord.contains("'")) {
            keyWord = keyWord.replaceAll("'", "''");
        }
        this.keyWord = keyWord;
        this.entryID = entryID;
    }

    
    public String toSQL(){
        return "('"+entryID+"','"+keyWord+"'),\n ";
    }
}
