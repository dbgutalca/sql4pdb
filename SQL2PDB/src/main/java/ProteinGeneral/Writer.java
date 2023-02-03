/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProteinGeneral;

import Tools.PostgreSQL;
import PrimaryStructure.ResidueFactory;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author Diego
 */
public class Writer {
    private PostgreSQL p;
    private String data = "";
    private String protein_data;
    private String chain_data;
    private String residue_data;
    private String source_data;
    private String atom_data;
    private String het_data;
    private String hetAtom_data;
    private String helix_data;
    private String ssBond_data;
    private String sheet_data;
    private String strand_data;
    private String helixResSubChain_data;
    private String strandResSubChain_data;
    private String keyWord_data;

    public void setPostgres(PostgreSQL p){
        this.p = p;
    }
    
    public void setKeyWord_data(String keyWord_data) {
        this.keyWord_data = keyWord_data;
    }

    public void setSource_data(String source_data) {
        this.source_data = source_data;
    }
    

    public void setHelixResSubChain_data(String helixResSubChain_data) {
        this.helixResSubChain_data = helixResSubChain_data;
    }

    public void setStrandResSubChain_data(String strandResSubChain_data) {
        this.strandResSubChain_data = strandResSubChain_data;
    }
        

    public void setSheet_data(String sheet_data) {
        this.sheet_data = sheet_data;
    }

    public void setStrand_data(String strand_data) {
        this.strand_data = strand_data;
    }
    

    public void setHetAtom_data(String hetAtom_data) {
        this.hetAtom_data = hetAtom_data;
    }

    public void setHet_data(String het_data) {
        this.het_data = het_data;
    }

    public void setHelix_data(String helix_data) {
        this.helix_data = helix_data;
    }
    
    public void setSSBond_data(String ssBond_data){
        this.ssBond_data = ssBond_data;
    }
    

    public void setAtom_data(String atom_data) {
        this.atom_data = atom_data;
    }

    public void setProtein_data(String protein_data) {
        this.protein_data = protein_data;
    }

    public void setChain_data(String chain_data) {
        this.chain_data = chain_data;
    }

    public void setResidue_data(String residue_data) {
        this.residue_data = residue_data;
    }
    
    public void writeTxt(String URL){        
        try
        {            
            insertTableProtein();
            insertTableKeyWord();
            insertTableSource();
            //insertTableStandardAmino(pw);            
            insertTableChain();
            insertTableResidue();
            insertTableAtom();
            insertTableHet();
            insertTableHetAtom();
            insertTableHelix();
            insertTableSSBond();
            insertTableSheet();
            insertTableStrand();
            insertTableHelixResSubChain();
            insertTableStrandResSubChain();
            p.makeInsert(data, URL);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
    
    private void insertTableSheet(){
        if (sheet_data!=null) {
            data = data.concat("INSERT INTO sheet(sheet_id, num_Strands, prot_fk) \n");
            data = data.concat("VALUES"+sheet_data+";\n");
        }        
    }
    
    private void insertTableSource(){
        if (source_data!=null) {
            data = data.concat("INSERT INTO prot_source(prot_source, protein_id) \n");            
            data = data.concat("VALUES"+source_data+";\n");
        }
    }
        
    private void insertTableStrand(){
        if (strand_data!=null) {
            data = data.concat("INSERT INTO strand(strand_id, sheet_FK, initRes_FK, endRes_FK,sense, currRes_FK,prevRes_FK,currAtom,prevAtom, seqResChain) \n");
            data = data.concat("VALUES"+strand_data+";\n");
        }
        
    }
    private void insertTableSSBond(){
        if (ssBond_data!=null) {
            data = data.concat("INSERT INTO ssBond(ssBond_id,res1_fk,res2_fk,sym1,sym2,ssBond_length) \n");            
            data = data.concat("VALUES"+ssBond_data+";\n");
        }        
    }
            
    private void insertTableProtein(){
        data = data.concat("INSERT INTO protein(prot_id,title,depDate,classification,num_heterogen,num_helix,num_coord,num_seqRes,num_sheet,insDate) \n");       
        data = data.concat("VALUES"+protein_data+";\n");
    }
    
    private void insertTableKeyWord(){
        if (keyWord_data != null) {
            data = data.concat("INSERT INTO keyword(protein_id, keyword)\n");
            data = data.concat("VALUES"+keyWord_data+";\n");
        }                
    }
    
    private void insertTableChain(){
        data = data.concat("INSERT INTO prot_chain(chain_id, prot_fk,chain_name, chain_length) \n");
        data = data.concat("VALUES"+chain_data+";\n");
    }
    
    private void insertTableStandardAmino(PrintWriter pw){
        pw.print("INSERT INTO standard_amino(name3,amino_name,amino_symbol,genetic_coding,amino_hydropathy,amino_mass,amino_abundance,amino_charge,amino_polarity,amino_class)\n ");
        pw.print("VALUES"+ResidueFactory.getResiduo("ALA").toSQL()+", ");
        pw.print(ResidueFactory.getResiduo("ARG").toSQL()+", ");
        pw.print(ResidueFactory.getResiduo("ASN").toSQL()+", ");
        pw.print(ResidueFactory.getResiduo("ASP").toSQL()+", ");
        pw.print(ResidueFactory.getResiduo("CYS").toSQL()+", ");
        pw.print(ResidueFactory.getResiduo("GLN").toSQL()+", ");
        pw.print(ResidueFactory.getResiduo("GLU").toSQL()+", ");
        pw.print(ResidueFactory.getResiduo("GLY").toSQL()+", ");
        pw.print(ResidueFactory.getResiduo("HIS").toSQL()+", ");
        pw.print(ResidueFactory.getResiduo("ILE").toSQL()+", ");
        pw.print(ResidueFactory.getResiduo("LEU").toSQL()+", ");
        pw.print(ResidueFactory.getResiduo("LYS").toSQL()+", ");
        pw.print(ResidueFactory.getResiduo("MET").toSQL()+", ");
        pw.print(ResidueFactory.getResiduo("PHE").toSQL()+", ");
        pw.print(ResidueFactory.getResiduo("PRO").toSQL()+", ");
        pw.print(ResidueFactory.getResiduo("SER").toSQL()+", ");
        pw.print(ResidueFactory.getResiduo("THR").toSQL()+", ");
        pw.print(ResidueFactory.getResiduo("TRP").toSQL()+", ");
        pw.print(ResidueFactory.getResiduo("TYR").toSQL()+", ");
        pw.print(ResidueFactory.getResiduo("VAL").toSQL()+", ");
        pw.print(ResidueFactory.getResiduo("SEC").toSQL()+", ");
        pw.print(ResidueFactory.getResiduo("PYL").toSQL()+", ");
        pw.print(ResidueFactory.getResiduo("A").toSQL()+", ");
        pw.print(ResidueFactory.getResiduo("C").toSQL()+", ");
        pw.print(ResidueFactory.getResiduo("G").toSQL()+", ");
        pw.print(ResidueFactory.getResiduo("T").toSQL()+", ");
        pw.print(ResidueFactory.getResiduo("U").toSQL()+", ");
        pw.print(ResidueFactory.getResiduo("DC").toSQL()+", ");
        pw.print(ResidueFactory.getResiduo("DG").toSQL()+", ");
        pw.print(ResidueFactory.getResiduo("DT").toSQL()+", ");
        pw.print(ResidueFactory.getResiduo("DA").toSQL()+";\n ");
        
        
    }
    
    private void insertTableResidue(){
        data = data.concat("INSERT INTO residue(residue_id,residue_seqNum,standardAmino_fk,chain_fk)\n");
        data = data.concat("VALUES"+residue_data+";\n ");
    }
    
    private void insertTableAtom(){
        
        data = data.concat("INSERT INTO atom(atom_id,model,atom_name,altLoc, X_coord,Y_coord,Z_coord,tempFactor,occupancy,charge,serial_number,residue_FK)\n");
        data = data.concat("VALUES"+atom_data+";\n ");
    }
    
    
    private void insertTableHet(){
        if (het_data != null) {
            data = data.concat("INSERT INTO heterogen(het_id,het_name,het_formul,het_seqNum,het_name3,het_synonims,chain_FK)\n");
            data = data.concat("VALUES"+het_data+";\n");
        }
        
    }
   
    private void insertTableHetAtom(){
        if (hetAtom_data != null) {
            data = data.concat("INSERT INTO hetAtom(hetAtom_id,model,hetAtom_name,altLoc, X_coord,Y_coord,Z_coord,tempFactor,occupancy,charge,serial_number,heterogen_FK)\n");
            data = data.concat("VALUES"+hetAtom_data+";\n");
        }
        
    }
    
    private void insertTableHelix(){
        if (helix_data!=null) {
            data = data.concat("INSERT INTO Helix(helix_id,helix_comment,helix_class,helix_length,initRes_fk,endRes_fk,seqResChain,prot_fk)\n");
            data = data.concat("VALUES"+helix_data+";\n");
        }
        
    }
    
    private void insertTableHelixResSubChain(){
        if (helixResSubChain_data!= null) {
            data = data.concat("INSERT INTO Helix_SubChain(helix_id,Res_id)\n");
            data = data.concat("VALUES"+helixResSubChain_data+";\n");
        }
    }
    
    private void insertTableStrandResSubChain(){        
        if (strandResSubChain_data!= null) {
            data = data.concat("INSERT INTO Strand_SubChain(strand_id,Res_id)\n");
            data = data.concat("VALUES"+strandResSubChain_data+";\n");
        }
    }
}
