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
public class ResidueFactory {
    public static Amino getResiduo(String id){
        switch (id) {
            case "ALA" : return new Amino("Ala", "A", "Alanine", "Aliphatic", "Nonpolar", "Neutral", 1.8, 89.094,8.76 ,"GCN");
            case "ARG" : return new Amino("Arg", "R", "Arginine", "Fixed ", "Basic Polar","Positive", -4.5, 174.203, 5.78, "MGR, CGY");
            case "ASN" : return new Amino("Asn", "N","Asparagine", "Amide", "Polar", "Neutral", -3.5, 132.119, 3.93, "AAY");
            case "ASP" : return new Amino("Asp", "D", "Aspartate", "Anion", "Bronsted Base", "Negative", -3.5, 133.104, 5.49, "GAY");
            case "CYS" : return new Amino("Cys", "C", "Cysteine", "Thiol", "Bronsted Acid", "Neutral", 2.5, 121.154, 1.38, "UGY");
            case "GLN" : return new Amino("Gln", "Q", "Glutamine", "Amide", "Polar", "Neutral", -3.5, 146.146, 3.9, "CAR");
            case "GLU" : return new Amino("Glu", "E", "Glutamate", "Anion", "Bronsted Acid", "Negative", -3.5, 147.131, 6.32, "GAR");
            case "GLY" : return new Amino("Gly", "G", "Glycine", "Alipathic", "Nonpolar","Neutral",4.5,75.067, 5.49, "AUH" );
            case "HIS" : return new Amino("His", "H", "Histidine", "Aromatic Cation", "Bronsted acid and base", "Positive 10%, Neutral 90%", -3.2, 155.156, 2.25, "CAY");
            case "ILE" : return new Amino("Ile", "I", "Isoleucine", "Alipathic", "Nonpolar", "Neutral", 4.5, 131.175, 5.49, "AUH");
            case "LEU" : return new Amino("Leu", "L", "Leucine", "Alipathic", "Nonpolar", "Neutral", 3.8, 131.175, 9.68, "YUR CUY");
            case "LYS" : return new Amino("Lys", "K", "Lysine", "Cation", "Bronsted Acid", "Positive", -3.9, 146.189, 5.19,"AAR" );
            case "MET" : return new Amino("Met", "M", "Methionine", "Thioether", "Nonpolar", "Neutral", 1.9, 149.208, 2.32, "AUG");
            case "PHE" : return new Amino("Phe", "F", "Phenylalanine", "Aromatic", "Nonpolar", "Neutral", 2.8, 165.192, 3.87, "UUY");
            case "PRO" : return new Amino("Pro", "P", "Proline", "Cyclic", "Nonpolar", "Neutral", -1.6, 115.132, 5.02, "CCN");
            case "SER" : return new Amino("Ser", "S", "Serine", "Hydroxylic", "Polar", "Neutral", -0.8, 105.093, 7.14, "UCN AGY");
            case "THR" : return new Amino("Thr", "Y", "Tyrosine", "Aromatic", "Bronsted Acid", "Neutral", -1.3, 181.191, 2.91, "ACN");
            case "TRP" : return new Amino("Trp", "W", "Tryptophan", "Aromatic", "Nonpolar", "Neutral", -0.9, 204.228, 1.25, "UGG");
            case "TYR" : return new Amino("Tyr", "Y", "Tyrosine", "Aromatic", "Bronsted Acid", "Neutral", -1.3, 181.191, 2.91, "UAY");
            case "VAL" : return new Amino("Val", "V", "Valine", "Alipathic", "Nonpolar", "Neutral", 4.2, 117.148, 6.73, "GUN");
            case "SEC" : return new Amino("Sec", "U", "Selenocysteine", "", "", "", 0, 168.064,0,"");
            case "PYL" : return new Amino("Pyl", "O", "Pyrrolysine", "", "", "" ,0, 255.313, 0, "");
            case "A" : return new Amino("Ade", "A", "Adenine");
            case "C" : return new Amino("Cyt", "C", "Cytosine");
            case "G" : return new Amino("Gua", "G", "Guanine");
            case "T" : return new Amino("Thy", "T", "Thymine");
            case "U" : return new Amino("Ura", "U", "Uracil");
            case "DC" : return new Amino("DC ", "DC", " ");
            case "DG" : return new Amino("DG ", "DG", " ");
            case "DT" : return new Amino("DT ", "DT", " ");
            case "DA" : return new Amino("DA ", "DA", " ");             
        };        
        return new Amino(id);
    }
}
