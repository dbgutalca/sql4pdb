/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Diego
 */
public class Downloader {
    private String sep = (System.getProperty("os.name").contains("Windows")) ? "\\" : "/";
    
    public String downloadPDB(String pdbName) throws FileNotFoundException, MalformedURLException, IOException{
        URL u = new URL("https://files.rcsb.org/download/"+pdbName+".pdb.gz");
        String Dir = System.getProperty("user.dir")+sep+"PDB_Downloads"+sep+pdbName+".ent.gz";
        InputStream in = u.openStream();
        byte [] buffer = new byte[4024];
        int n;        
        FileOutputStream out = new FileOutputStream(Dir);    
        System.out.println("Downloading PDB file "+pdbName+" in directory "+Dir);
        while ( (n = in.read(buffer)) > 0) {        
            out.write(buffer, 0, n);
         }
        out.close();
        in.close();
        System.out.println("Finished");
        return Dir;
    }
}
