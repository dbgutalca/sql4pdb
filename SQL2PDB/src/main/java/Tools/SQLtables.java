/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

/**
 *
 * @author Diego
 */
public class SQLtables {
    
    public static String getProtTable(){
        return "CREATE TABLE IF NOT EXISTS protein (\n" +
"    prot_id VARCHAR (6) PRIMARY KEY,\n" +
"    title VARCHAR,\n" +
"    depDate VARCHAR,\n" +
"    classification VARCHAR,  \n" +
"    insDate VARCHAR,  \n" +
"    num_heterogen INTEGER,\n" +
"    num_helix INTEGER,\n" +
"    num_coord INTEGER,\n" +
"    num_SeqRes INTEGER,\n" +
"    num_Sheet INTEGER\n" +
");";
    }
    
    public static String getKeywords(){
        return "CREATE TABLE IF NOT EXISTS keyword(\n" +
"	protein_id VARCHAR(6),\n" +
"	keyword VARCHAR,\n" +
"	FOREIGN KEY (protein_id) REFERENCES protein(prot_id)\n" +
");";             
    }
    
    public static String getProtSource(){
        return "CREATE TABLE IF NOT EXISTS prot_source(\n" +
"	protein_id VARCHAR(6),\n" +
"	prot_source VARCHAR,\n" +
"	FOREIGN KEY (protein_id) REFERENCES protein(prot_id)\n" +
");";
    }
    
    public static String getProtChain(){
        return "CREATE TABLE IF NOT EXISTS prot_chain(\n" +
"    chain_id VARCHAR PRIMARY KEY,\n" +
"    prot_fk VARCHAR (6) NOT NULL,\n" +
"    chain_name VARCHAR,\n" +
"    chain_length INTEGER,\n" +
"    FOREIGN KEY(prot_fk) REFERENCES protein (prot_id)\n" +
");";
    }
    
    
    public static String getStandardAmino(){
        return "CREATE TABLE IF NOT EXISTS standard_amino(\n" +
"    name3 VARCHAR(3) PRIMARY KEY,\n" +
"    amino_name varchar,\n" +
"    amino_symbol varchar,\n" +
"    genetic_coding varchar,\n" +
"    amino_hydropathy double precision,\n" +
"    amino_mass double precision,\n" +
"    amino_abundance double precision,\n" +
"    amino_charge varchar,\n" +
"    amino_polarity varchar,\n" +
"    amino_class varchar\n" +
");";
    }
    
    public static String getResidue(){
        return "CREATE TABLE IF NOT EXISTS residue(\n" +
"    residue_id VARCHAR PRIMARY KEY,\n" +
"    residue_seqNum INTEGER,\n" +
"    standardAmino_fk VARCHAR(3) NOT NULL,\n" +
"    chain_fk VARCHAR,\n" +
"    FOREIGN KEY(chain_fk) REFERENCES prot_chain(chain_id),\n" +
"    FOREIGN KEY(standardAmino_fk) REFERENCES standard_amino(name3)\n" +
");";
    }
    
    public static String getAtom(){
        return "CREATE TABLE IF NOT EXISTS atom(\n" +
"    atom_id VARCHAR PRIMARY KEY,\n" +
"    model INTEGER,\n" +
"    atom_name VARCHAR,\n" +
"    altLoc CHAR,\n" +
"    X_coord DOUBLE PRECISION,\n" +
"    Y_coord DOUBLE PRECISION,\n" +
"    Z_coord DOUBLE PRECISION,\n" +
"    tempFactor DOUBLE PRECISION,\n" +
"    occupancy DOUBLE PRECISION,\n" +
"    charge DOUBLE PRECISION,\n" +
"    serial_number INTEGER,\n" +
"    residue_FK VARCHAR,\n" +
"    FOREIGN KEY(residue_FK) REFERENCES residue(residue_id)\n" +
");";
    }
    
    public static String getHet(){
        return "CREATE TABLE IF NOT EXISTS heterogen (\n" +
"    het_id VARCHAR PRIMARY KEY,\n" +
"    het_name VARCHAR,\n" +
"    het_formul VARCHAR,\n" +
"    het_seqNum INTEGER,\n" +
"    het_name3 VARCHAR,\n" +
"    het_synonims VARCHAR,\n" +
"    chain_FK VARCHAR,\n" +
"    FOREIGN KEY (chain_FK) REFERENCES prot_chain(chain_id)\n" +
");";
    }
    
    public static String getHetAtm(){
        return "CREATE TABLE IF NOT EXISTS hetAtom(\n" +
"    hetAtom_id VARCHAR PRIMARY KEY,\n" +
"    model INTEGER,\n" +
"    hetAtom_name VARCHAR,\n" +
"    altLoc CHAR,\n" +
"    X_coord DOUBLE PRECISION,\n" +
"    Y_coord DOUBLE PRECISION,\n" +
"    Z_coord DOUBLE PRECISION,	\n" +
"    tempFactor DOUBLE PRECISION,\n" +
"    occupancy DOUBLE PRECISION,\n" +
"    charge DOUBLE PRECISION,\n" +
"    serial_number INTEGER,\n" +
"    heterogen_FK VARCHAR,\n" +
"    FOREIGN KEY(heterogen_FK) REFERENCES heterogen(het_id)\n" +
");";
    }
    
    public static String getHelix(){
        return "CREATE TABLE IF NOT EXISTS Helix(\n" +
"    helix_id VARCHAR PRIMARY KEY,\n" +
"    prot_fk VARCHAR(6),\n" +
"    helix_comment VARCHAR,\n" +
"    helix_class VARCHAR,\n" +
"    helix_length INTEGER,\n" +
"    initRes_fk VARCHAR,\n" +
"    endRes_fk VARCHAR,\n" +
"    seqResChain VARCHAR,\n" +
"    FOREIGN KEY(prot_fk) REFERENCES protein(prot_id),\n" +
"    FOREIGN KEY(initRes_fk) REFERENCES residue (residue_id),\n" +
"    FOREIGN KEY(endRes_fk) REFERENCES residue(residue_id)\n" +
");";
    }
    
    public static String getHelixSubChain(){
        return "CREATE TABLE IF NOT EXISTS Helix_subchain(\n" +
"	helix_id VARCHAR,\n" +
"	res_id VARCHAR,\n" +
"	FOREIGN KEY (helix_id) REFERENCES helix(helix_id),\n" +
"	FOREIGN KEY (res_id) REFERENCES residue(residue_id)\n" +
");";
    }
    
    public static String getSSBond(){
        return "CREATE TABLE IF NOT EXISTS ssBond(\n" +
"    ssBond_id VARCHAR PRIMARY KEY,\n" +
"    sym1 INTEGER,\n" +
"    sym2 INTEGER,\n" +
"    ssBond_length DOUBLE PRECISION,\n" +
"    res1_fk VARCHAR,\n" +
"    res2_fk VARCHAR,\n" +
"    FOREIGN KEY (res1_fk) REFERENCES residue(residue_id),\n" +
"    FOREIGN KEY (res2_fk) REFERENCES residue(residue_id)    \n" +
");";
    }
    
    public static String getSheet(){
        return "CREATE TABLE IF NOT EXISTS Sheet(\n" +
"    sheet_id VARCHAR PRIMARY KEY,\n" +
"    num_Strands INTEGER,\n" +
"    prot_fk VARCHAR(6),\n" +
"    FOREIGN KEY(prot_fk) REFERENCES protein(prot_id) \n" +
");";
    }
    
    public static String getStrand(){
        return "CREATE TABLE IF NOT EXISTS Strand(\n" +
"    strand_id VARCHAR PRIMARY KEY,\n" +
"    sheet_FK VARCHAR,\n" +
"    initRes_FK VARCHAR,\n" +
"    endRes_FK VARCHAR,\n" +
"    sense INTEGER,\n" +
"    currRes_FK VARCHAR,\n" +
"    prevRes_FK VARCHAR,\n" +
"    currAtom VARCHAR,\n" +
"    prevAtom VARCHAR,\n" +
"    seqResChain VARCHAR,\n" +
"    FOREIGN KEY(sheet_FK) REFERENCES Sheet(sheet_id),\n" +
"    FOREIGN KEY(initRes_FK) REFERENCES residue(residue_id),\n" +
"    FOREIGN KEY(endRes_FK) REFERENCES residue(residue_id)    \n" +
");";
    }
    
    public static String getStrandSubChain(){
        return "CREATE TABLE IF NOT EXISTS Strand_subchain(\n" +
"	strand_id VARCHAR,\n" +
"	res_id VARCHAR,\n" +
"	FOREIGN KEY (strand_id) REFERENCES strand(strand_id),\n" +
"	FOREIGN KEY (res_id) REFERENCES residue(residue_id)\n" +
");";
    }
    
    public static String fillStandardAmino(){
        return "INSERT INTO standard_amino(name3,amino_name,amino_symbol,genetic_coding,amino_hydropathy,amino_mass,amino_abundance,amino_charge,amino_polarity,amino_class)\n" +
" VALUES('Ala','Alanine','A','GCN','1.8','89.094','8.76','Neutral','Nonpolar','Aliphatic') \n" +
", ('Arg','Arginine','R','MGR, CGY','-4.5','174.203','5.78','Positive','Basic Polar','Fixed ') \n" +
", ('Asn','Asparagine','N','AAY','-3.5','132.119','3.93','Neutral','Polar','Amide') \n" +
", ('Asp','Aspartate','D','GAY','-3.5','133.104','5.49','Negative','Bronsted Base','Anion') \n" +
", ('Cys','Cysteine','C','UGY','2.5','121.154','1.38','Neutral','Bronsted Acid','Thiol') \n" +
", ('Gln','Glutamine','Q','CAR','-3.5','146.146','3.9','Neutral','Polar','Amide') \n" +
", ('Glu','Glutamate','E','GAR','-3.5','147.131','6.32','Negative','Bronsted Acid','Anion') \n" +
", ('Gly','Glycine','G','AUH','4.5','75.067','5.49','Neutral','Nonpolar','Alipathic') \n" +
", ('His','Histidine','H','CAY','-3.2','155.156','2.25','Positive 10%, Neutral 90%','Bronsted acid and base','Aromatic Cation') \n" +
", ('Ile','Isoleucine','I','AUH','4.5','131.175','5.49','Neutral','Nonpolar','Alipathic') \n" +
", ('Leu','Leucine','L','YUR CUY','3.8','131.175','9.68','Neutral','Nonpolar','Alipathic') \n" +
", ('Lys','Lysine','K','AAR','-3.9','146.189','5.19','Positive','Bronsted Acid','Cation') \n" +
", ('Met','Methionine','M','AUG','1.9','149.208','2.32','Neutral','Nonpolar','Thioether') \n" +
", ('Phe','Phenylalanine','F','UUY','2.8','165.192','3.87','Neutral','Nonpolar','Aromatic') \n" +
", ('Pro','Proline','P','CCN','-1.6','115.132','5.02','Neutral','Nonpolar','Cyclic') \n" +
", ('Ser','Serine','S','UCN AGY','-0.8','105.093','7.14','Neutral','Polar','Hydroxylic') \n" +
", ('Thr','Tyrosine','Y','ACN','-1.3','181.191','2.91','Neutral','Bronsted Acid','Aromatic') \n" +
", ('Trp','Tryptophan','W','UGG','-0.9','204.228','1.25','Neutral','Nonpolar','Aromatic') \n" +
", ('Tyr','Tyrosine','Y','UAY','-1.3','181.191','2.91','Neutral','Bronsted Acid','Aromatic') \n" +
", ('Val','Valine','V','GUN','4.2','117.148','6.73','Neutral','Nonpolar','Alipathic') \n" +
", ('Sec','Selenocysteine','U','','0.0','168.064','0.0','','','') \n" +
", ('Pyl','Pyrrolysine','O','','0.0','255.313','0.0','','','') \n" +
", ('Ade','Adenine','A',' ','0.0','0.0','0.0',' ',' ',' ') \n" +
", ('Cyt','Cytosine','C',' ','0.0','0.0','0.0',' ',' ',' ') \n" +
", ('Gua','Guanine','G',' ','0.0','0.0','0.0',' ',' ',' ') \n" +
", ('Thy','Thymine','T',' ','0.0','0.0','0.0',' ',' ',' ') \n" +
", ('Ura','Uracil','U',' ','0.0','0.0','0.0',' ',' ',' ') \n" +
", ('DC ',' ','DC',' ','0.0','0.0','0.0',' ',' ',' ') \n" +
", ('DG ',' ','DG',' ','0.0','0.0','0.0',' ',' ',' ') \n" +
", ('DT ',' ','DT',' ','0.0','0.0','0.0',' ',' ',' ') \n" +
", ('DA ',' ','DA',' ','0.0','0.0','0.0',' ',' ',' ') \n" +
";";
    }
            
}

