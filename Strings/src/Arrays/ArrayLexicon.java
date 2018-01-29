/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arrays;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author christian
 */
public class ArrayLexicon {
    private ArrayList vars;
    private ArrayFunctions functions;
    private Map<String, ArrayBle> memory;
    public ArrayLexicon(){
        vars = new ArrayList();
        functions = new ArrayFunctions();
        memory = new HashMap<>();
    }
    public void readSource(String filename){
        BufferedReader br;
        Pattern p;
        Matcher m;
        try {
            
            br = new BufferedReader(new FileReader(filename));
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
                            vars.add(m.group());
                            ArrayBle array = new ArrayBle();
                            memory.put(m.group(), array);
                            System.out.println(m.group());                        }
                    }
                    
                }else if (functions.getFunction(line)) {
                	System.out.println(functions.findFunction(memory));
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
