/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ble.Functionalities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Max
 */
public class ArraysFunct {
    static Pattern p;
    static Matcher m;
    
    public static void arrayStuff(String line)
    {
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
    }
}
