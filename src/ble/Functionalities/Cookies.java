package ble.Functionalities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Max
 */
public class Cookies {
    static BufferedWriter bw;
    static File cookiestxt;
    static FileReader cookieReader;
    static BufferedReader cr;
    static String s;
    
    static String cookies(String line) throws FileNotFoundException, IOException {
        String ret;
        
        ret = "";
        if(line.contains("set")) {
            line = line.split("\\(")[1];
            line = line.split("\\)")[0];
            bw = null;
            
            try {
                bw = new BufferedWriter(new FileWriter("cookies.txt", true));
                bw.write(line.split(",")[0] + " " + line.split(",")[1]);
            } catch(IOException e) {
                System.err.println(e.getMessage());
            } finally {
                if(bw != null) try {
                    bw.close();
                } catch(IOException ex) {

                }
            }
        } else {
            line = line.split("\\(")[1];
            line = line.split("\\)")[0];
            cookiestxt = new File("cookies.txt");
            cookieReader = new FileReader(cookiestxt);
            cr = new BufferedReader(cookieReader);
            while((s = cr.readLine()) != null && !s.startsWith(line)) {}
            if(s.startsWith(line)) {
                ret = s.split(" ")[1];
            }
        }
        
        return ret;
    }
    
}
