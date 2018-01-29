/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package array;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author randelgimenez
 */
public class Array {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        BufferedReader br;
        Pattern p;
        Matcher m;
        try {
            
            br = new BufferedReader(new FileReader("C:\\Users\\randelgimenez\\Documents\\CS Files\\Compiler\\array\\src\\sample.ble"));
            String line = br.readLine();
            ///StringBuilder sb = new StringBuilder();
            do{
                //System.out.println(line);
                String regex = "Let\\s*[a-zA-z]*(\\s*)?=(\\s*)?Array\\(\\)";
                p = Pattern.compile(regex);
                m = p.matcher(line);
                if(m.find()){
                    //System.out.println("Naa");
                    String pat = "\\s[a-zA-z]*(\\s*)?=";
                    p = Pattern.compile(pat);
                    m = p.matcher(line);
                    if(m.find()){
                        String patt = "[a-zA-z]+";
                        p = Pattern.compile(patt);
                        m = p.matcher(m.group());
                        if(m.find()){
                            System.out.println(m.group());
                            
                        }
                        //System.out.println(m.find()?m.group():"None");
                    }
                }else{
                    System.out.println("Wala");
                }
                line = br.readLine();
            }while (line != null);
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
