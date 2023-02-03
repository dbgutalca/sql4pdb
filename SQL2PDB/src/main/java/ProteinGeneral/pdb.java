/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProteinGeneral;
import SecondaryStructure.Helix;
import SecondaryStructure.SecondarySubChain;
import SecondaryStructure.Sheet;
import SecondaryStructure.Strand;
import PrimaryStructure.AtomER;
import PrimaryStructure.ChainER;
import PrimaryStructure.HetAtm;
import PrimaryStructure.Heterogen;
import PrimaryStructure.ResidueInstance;
import Tools.Downloader;
import Tools.PostgreSQL;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.biojava.nbio.structure.Atom;
import org.biojava.nbio.structure.Chain;
import org.biojava.nbio.structure.Group;
import org.biojava.nbio.structure.PDBHeader;
import org.biojava.nbio.structure.Structure;
import org.biojava.nbio.structure.io.PDBFileReader;
import java.sql.*;
import org.biojava.nbio.structure.StructureException;
import org.biojava.nbio.structure.StructureIO;
import org.biojava.nbio.structure.align.util.AtomCache;
import org.biojava.nbio.structure.io.FileParsingParameters;
import org.biojava.nbio.structure.secstruc.DSSPParser;
import org.biojava.nbio.structure.secstruc.SecStrucInfo;



/**
 *
 * @author Diego
 */
public class pdb {    
    private String URL ;
    private String entryName;
    private ArrayList<ChainER> chains = new ArrayList<ChainER>();
    private PostgreSQL postgres;
    private Writer writer = new Writer();
    private GeneralReader reader = new GeneralReader();
    private String chainInsert = "";
    private String residueInsert= "";
    private String atomInsert = "";
    private String hetInsert = "";
    private String hetAtomInsert = "";
    private String helixInsert = "";
    private String proteinInsert = "";
    private String ssBondInsert = "";
    private String sheetInsert = "";
    private String strandInsert = "";
    private String helixSubChainInsert = "";
    private String strandSubChainInsert = "";
    private String keyWordInsert = "";
    private String sourceInsert = "";
    private ArrayList<Helix> helixes = new ArrayList();
    private ArrayList<SecondarySubChain> helixResSubChain = new ArrayList();
    private ArrayList<SecondarySubChain> strandResSubChain = new ArrayList();
    private ArrayList<Sheet> sheets = new ArrayList();
    private ArrayList<String> sources = new ArrayList();
    private ArrayList<Source> sourceSQL = new ArrayList();
    private ArrayList<SSBond> ssBonds = new ArrayList();
    private ArrayList<KeyWord> keyWords = new ArrayList();    
    private Protein protein;
    private boolean hasError = false;

    public pdb (PostgreSQL p){
        this.postgres = p;
    }
    
    public pdb(String URL, PostgreSQL p) {
        this.URL = URL;
        this.postgres = p;
    }
                
    public int createOmitedList(){
        PDBFileReader pdbReader = new PDBFileReader();
        try {
            Structure pdb = pdbReader.getStructure(URL);
            String classification = pdb.getPDBHeader().getClassification();            
            String expData = pdb.getPDBHeader().getExperimentalTechniques().toString();
            if (classification.contains("RNA") || classification.contains("DNA") 
                    || expData.contains("NMR") || expData.contains("CRYSTALLOGRAPHY")) {
                System.out.println("Omiting "+pdb.getPdbId());
                Log.addOmited(URL+"\n");
                Log.addText(pdb.getPdbId()+ " Omited\n");
                return 1; 
            }else{
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
    }
    
    public void leerPDB(){
        PDBFileReader lector = new PDBFileReader();          
        try {                     
            Structure pdb = lector.getStructure(URL);                            
            this.entryName = pdb.getPdbId().getId();              
            fill(pdb);
            if (!hasError) {
                convertIntoSQL();                        
            }else{                
                Log.addOmited(URL+"\n");
            }          
        } catch (IOException ex) {  
            System.out.println(ex);
        }                  
    }
    
    public void readGivenPDB(String pdbName) throws StructureException{
        PDBFileReader pdbReader = new PDBFileReader();      
        Downloader d = new Downloader();
        try {                                           
            Structure pdb = pdbReader.getStructure(this.URL = d.downloadPDB(pdbName));   
            this.entryName = pdb.getPdbId().getId();              
            fill(pdb);
            if (!hasError) {
                convertIntoSQL();                        
            }else{                
                Log.addOmited(URL+"\n");
            }          
        } catch (IOException ex) {  
            System.out.println(ex);
        }        
    }
            
    private void fill(Structure pdb){
        fillProtein(pdb);            
        reader.fillSecondaryStructure(helixes, sheets,sources, entryName, ssBonds, URL); 
        fillSource(sources);
        fillStrands();
        fillResidue(pdb);
        fillHeterogen(pdb);
        fillMaster(protein);            
        fillHelixSubchains();
        fillStrandSubChains();
    }
    
    private void convertIntoSQL(){
        try {
            chainToSQL();
            proteinToSQL();
            residueToSQL();
            if (!hasError) {
                keyWordToSQL();            
                atomToSQL();            
                hetToSQL();            
                hetAtmToSQL();
                helixToSQL();
                sheetToSQL();
                ssBondToSQL();
                helixResSubChainToSQL();
                strandResSubChainToSQL();
                sourceToSQL();
                toSQL();
            }
            
        } catch (Exception e) {
            System.out.println("Error in: "+entryName);
            System.out.println(e);
        }
        
    }
    
    private void printChains(){
        for (ChainER c : chains) {
            c.printChain();
            c.printHet();
        }
    }
    
    private void fillStrandSubChains(){
        for (Sheet s : sheets) {
            for (Strand st : s.getStrands()) {
                String subChain = "";            
                for (ChainER c : chains) {                  
                    subChain = c.getSubChain(st.getChain(), st.getInitSeqResNum(), st.getEndSeqResNum(),subChain);
                    if (subChain.equals("ERROR")) {
                        hasError = true;
                    }
                }
                st.setSeqRes(subChain);    
                ArrayList <String> stResChain = st.idResidueSubChain(chains);
                for (String resID : stResChain) {                    
                    this.strandResSubChain.add(new SecondarySubChain(st.getID(),resID));
                }
            }
        }
    }
    
    private void fillStrands(){
        for (Sheet s : sheets) {
            s.createStrands();
        }        
    }
    
    private void fillProtein(Structure pdb){
        PDBHeader header = pdb.getPDBHeader();
        List<String> keywords = header.getKeywords();
        fillKeyWords(keywords);
        String clasification = header.getClassification();
        String depDate = header.getDepDate().toString();
        String title = header.getTitle();       
        this.protein = new Protein(entryName, title, depDate, clasification);                
    }
    
    private void fillKeyWords(List<String> keywords){
        for (String s : keywords) {
            this.keyWords.add(new KeyWord(s, entryName));
        }
    }
    
    private void fillSource(List<String> sources){        
        for (String s: sources) {            
            this.sourceSQL.add(new Source(s,this.entryName));
        }
    }
    
    private void fillHelixSubchains(){        
        for (Helix h : helixes) {
            String subChain = "";            
            for (ChainER c : chains) {                  
                subChain = c.getSubChain(h.getHelixChain(), h.getInitRes(), h.getEndRes(),subChain); 
                if (subChain.equals("ERROR")) {
                        hasError = true;
                }
            }
            ArrayList <String> hResChain = h.idResidueSubChain(chains);
            for (String s : hResChain) {                
                this.helixResSubChain.add(new SecondarySubChain(h.getID(),s));
            }
            h.setSeqRes(subChain);            
        }                
    }
    
    private void fillMaster(Protein protein){
        protein.setNumSheet(sheets.size());
        protein.setNumCoord(getCoord());
        protein.setNumHelix(helixes.size());
        protein.setNumSeqRes(getSeqRes());
        protein.setNumHet(getHets());        
        proteinInsert = protein.toSQL();
    }
    
    private int getCoord(){
        int coord = 0;
        for (ChainER c : chains) {      
            for (ResidueInstance r : c.getResidues()) {
                coord = coord + r.getAtoms().size();
            }
            for (Heterogen h: c.getHeterogens()) {
                coord = coord + h.getHetAtms().size();
            }            
        }
        return coord;
    }
    
    private int getSeqRes(){
        int res = 0;
        for (ChainER c : chains) {
            res = res + c.getResidues().size();                
        }
        return res;
    }
    
    private int getHets(){
        int hets = 0;
        for (ChainER c : chains) {
            hets = hets + c.getHeterogens().size();
        }
        return hets;
    }
            
            
    
    private ArrayList<HetAtm> fillHetAtoms(Group group, String chainID, int numSeqRes){
        ArrayList<HetAtm> hetAtms = new ArrayList<>();             
        for (Atom a : group.getAtoms()) {            
            String name = a.getName();
            char altLoc = a.getAltLoc();
            String element = a.getElement().name();
            short charge = a.getCharge();
            int serNum = a.getPDBserial();
            double x = a.getX();
            double y = a.getY();
            double z = a.getZ();
            float ocupancy = a.getOccupancy();
            float tempFactor = a.getTempFactor();
            
            HetAtm atom = new HetAtm(entryName, chainID, numSeqRes, serNum, name, altLoc, x, y, z, ocupancy, tempFactor, element, charge);
            hetAtomInsert = hetAtomInsert.concat(atom.toSQL());
            hetAtms.add(atom);
        }                
        return hetAtms;
    }
    
    private ArrayList<AtomER> fillAtoms(Group group, String chainID, int numSeqRes){                
        ArrayList<AtomER> atoms = new ArrayList<>();     
        
        for (Atom a : group.getAtoms()) {
            
            String name = a.getName();
            char altLoc = a.getAltLoc();
            String element = a.getElement().name();
            short charge = a.getCharge();
            int serNum = a.getPDBserial();
            
            double x = a.getX();
            double y = a.getY();
            double z = a.getZ();
            float ocupancy = a.getOccupancy();
            float tempFactor = a.getTempFactor();
            
            AtomER atom = new AtomER(entryName, chainID, numSeqRes, name, altLoc, element, charge, serNum, x, y, z, ocupancy, tempFactor);
            atomInsert = atomInsert.concat(atom.toSQL());
            atoms.add(atom);            
        }                     
        return atoms;
    }
    
    private void fillHeterogen(Structure pdb){
        List<Chain> pdbChains;
        if (!pdb.getNonPolyChains().isEmpty()) {
            pdbChains = pdb.getNonPolyChains();  
            
        }else{
            pdbChains = pdb.getWaterChains();              
        }
        
        ArrayList<String> ids = new ArrayList();
        for (Chain c: pdbChains) {            
            List<Group> groups = c.getAtomGroups(); 
            ArrayList<Heterogen> hets = new ArrayList<>();
            for (Group g: groups) {
                
                String name = g.getChemComp().getName();                
                String syn = g.getChemComp().getPdbxSynonyms();
                String formul = g.getChemComp().getFormula();
                String name3 = g.getPDBName();
                int seqNum = g.getResidueNumber().getSeqNum();     
                Heterogen het = new Heterogen(name, syn, formul, entryName, c.getName(),seqNum ,name3, fillHetAtoms(g, c.getName(), seqNum));
                hets.add(het);                 
                hetInsert = hetInsert.concat(het.toSQL());
            }                        
            if (!ids.contains(c.getName())) {
                ids.add(c.getName());
                ChainER chain = new ChainER(c.getName(), entryName); 
                chain.setHeterogen(hets);
                chains.add(chain);
            }else{    
                chains.get(chains.size()-1).addArrayList(hets);
            }
            
        }
        
    }
    
    private void fillResidue(Structure pdb){
        
        List<Chain> pdbChains  = pdb.getPolyChains();
            for (Chain c: pdbChains) {                    
                ArrayList<ResidueInstance> residue = new ArrayList<>();
                List<Group> groups = c.getSeqResGroups();   
                int resNum;
                int resNumAux = -1;
                for (int i = 0; i<groups.size(); i++) {                                     
                    boolean undefined = false;
                    if (groups.get(i).getResidueNumber()!= null) {
                        resNum = groups.get(i).getResidueNumber().getSeqNum();                                                
                    }else{        
                        undefined = true;
                        resNum = resNumAux;
                        resNumAux = resNumAux - 1;
                    }                                       
                   ResidueInstance r = new ResidueInstance(entryName, c.getName(), groups.get(i).getPDBName(), resNum, fillAtoms(groups.get(i), c.getName(), resNum),undefined);
                    if (!r.getAmino().getName3().equals("ERROR")) {
                        residueInsert = residueInsert.concat(r.toSQL());
                        residue.add(r);
                    }else{
                        hasError = true;
                    }
                   
                   
                }                                
                chains.add(new ChainER(entryName, c.getName(), residue,entryName));                   
            }
        if (!hasRes()) {
            hasError = true;
            Log.addText("Error in: "+entryName + "\n");            
            Log.addText("BioJava couldn't find any primary structure\n");
        }    
    }
    
    private void chainToSQL(){ 
        for (ChainER c : chains) {
            chainInsert = chainInsert.concat(c.toSQL());  
        }
        chainInsert = chainInsert.substring(0, chainInsert.length()-3).concat("\n");
    }
    
    private void helixResSubChainToSQL(){
        if (!helixResSubChain.isEmpty()) {
            for (SecondarySubChain s: helixResSubChain) {
                helixSubChainInsert = helixSubChainInsert.concat(s.toSQL());
            }
            helixSubChainInsert = helixSubChainInsert.substring(0, helixSubChainInsert.length()-3).concat("\n");
        }
        
    }
    
    private void strandResSubChainToSQL(){
        if (!strandResSubChain.isEmpty()) {            
            for (SecondarySubChain s: strandResSubChain) {
                strandSubChainInsert = strandSubChainInsert.concat(s.toSQL());
            }
            strandSubChainInsert = strandSubChainInsert.substring(0, strandSubChainInsert.length()-3).concat("\n");
        }        
    }
    
    private void keyWordToSQL(){
        if (!keyWords.isEmpty()) {
            for (KeyWord k: keyWords) {
                keyWordInsert = keyWordInsert.concat(k.toSQL());
            }
            keyWordInsert = keyWordInsert.substring(0, keyWordInsert.length()-3).concat("\n");
        }
    }
    
    private void residueToSQL(){        
        residueInsert = residueInsert.substring(0, residueInsert.length()-3).concat("\n");                
    }
    
    private void atomToSQL(){
        atomInsert = atomInsert.substring(0, atomInsert.length()-3).concat("\n");
    }
    
    private void hetToSQL(){    
        if (hasHet()) {
            hetInsert = hetInsert.substring(0, hetInsert.length()-3).concat("\n");                        
        }

    }
    
    private void hetAtmToSQL(){
        if (hasHet()) {            
            hetAtomInsert = hetAtomInsert.substring(0, hetAtomInsert.length()-3).concat("\n");
        }
        
    }
       

    
    private void sheetToSQL(){
        if (!sheets.isEmpty()) {            
            for (Sheet s : sheets) {
                sheetInsert = sheetInsert.concat(s.toSQL());
                for (Strand st: s.getStrands()) {
                    strandInsert = strandInsert.concat(st.toSQL());
                }
            }
            strandInsert = strandInsert.substring(0,strandInsert.length()-3).concat("\n");
            sheetInsert = sheetInsert.substring(0,sheetInsert.length()-3).concat("\n");
        }
    }
    private void helixToSQL(){
        if (!helixes.isEmpty()) {
            for (Helix h : helixes) {
                helixInsert = helixInsert.concat(h.toSQL());
            }
            helixInsert = helixInsert.substring(0,helixInsert.length()-3).concat("\n");
        }        
    }
    
    private void sourceToSQL(){
        if (!sourceSQL.isEmpty()) {
            for (Source s : sourceSQL) {
                sourceInsert = sourceInsert.concat(s.toSQL());
            }
            sourceInsert = sourceInsert.substring(0,sourceInsert.length()-3).concat("\n");
        }
    }
            
    
    private void ssBondToSQL(){
        if (!ssBonds.isEmpty()) {
            for (SSBond s : ssBonds) {            
                ssBondInsert = ssBondInsert.concat(s.toSQL());
            }
            ssBondInsert = ssBondInsert.substring(0,ssBondInsert.length()-3).concat("\n");
        }
        
    }
            
    private void toSQL(){        
        writer.setPostgres(postgres);
        writer.setChain_data(chainInsert);
        writer.setProtein_data(proteinInsert);
        writer.setResidue_data(residueInsert);
        writer.setAtom_data(atomInsert);
        if (hasHet()) {
            writer.setHet_data(hetInsert);
            writer.setHetAtom_data(hetAtomInsert);
        }                
        if (!helixes.isEmpty()) {
            writer.setHelix_data(helixInsert);
            writer.setHelixResSubChain_data(helixSubChainInsert);
        }
        if (!sheets.isEmpty()) {
            writer.setSheet_data(sheetInsert);
            writer.setStrand_data(strandInsert);
            writer.setStrandResSubChain_data(strandSubChainInsert);
        }        
        if (!ssBonds.isEmpty()) {
            writer.setSSBond_data(ssBondInsert);
        }        
        if (!keyWords.isEmpty()) {
            writer.setKeyWord_data(keyWordInsert);
        }
        if (!sourceSQL.isEmpty()) {
            writer.setSource_data(sourceInsert);
        }
        writer.writeTxt(URL);
    }

    
    public void proteinToSQL(){
        proteinInsert = proteinInsert.substring(0,proteinInsert.length()-3).concat("\n");
    }
    
    private boolean hasHet(){
        boolean hasHet = false;
        for (ChainER c : chains) {
            if (c.hasHet()) {
                hasHet = true;
            }
        }                
        return hasHet;
    }
    
    private boolean hasRes(){
        boolean hasRes = false;
        for (ChainER c : chains) {
            if (c.hasRes()) {
                hasRes = true;
            }
        }
        return hasRes;
    }
}
