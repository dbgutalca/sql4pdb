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
public class Amino  {
    private String name3;
    private String symbol = " ";
    private String name = " ";
    private String Class = " ";
    private String polarity = " ";
    private String charge = " ";
    private double hydropathy= 0;
    private double mass = 0;
    private double abundance = 0;
    private String geneticCoding = " ";

    public Amino(String name3, String symbol, String name, String Class, String polarity, String charge, double hydropathy, double mass, double abundance, String geneticCoding) {
        this.name3 = name3;
        this.symbol = symbol;
        this.name = name;
        this.Class = Class;
        this.polarity = polarity;
        this.charge = charge;
        this.hydropathy = hydropathy;
        this.mass = mass;
        this.abundance = abundance;
        this.geneticCoding = geneticCoding;
    }
    
    public Amino(String id){
        this.name3 = "ERROR";
    }
    
    public Amino(String name3, String symbol, String name){
        this.name3 = name3;
        this.symbol = symbol;
        this.name = name;
    }
    
    public String getName3() {
        return name3;
    }

    public String getSymbol() {
        return symbol;
    }

    
   
    public String getName() {
        return name;
    }

    public String getclass() {
        return Class;
    }

    public String getPolarity() {
        return polarity;
    }

    public String getCharge() {
        return charge;
    }

    public double getHydropathy() {
        return hydropathy;
    }

    public double getMass() {
        return mass;
    }

    public double getAbundance() {
        return abundance;
    }

    public String getGeneticCoding() {
        return geneticCoding;
    }
    
    
    public String getAll(){
        return name+ ", " + name3+ ", " + symbol;
    }
    
    public String getNonAmino(){
        if (name == null) {
            return name3;
        }
        return " ";
    }      
    
    public String toSQL(){
        return "('"+name3+"','"+name+"','"+symbol+"','"+geneticCoding+"','"+hydropathy+"','"+mass+"','"+abundance+"','"+charge+"','"+polarity+"','"+Class+"') \n";
    }
}
