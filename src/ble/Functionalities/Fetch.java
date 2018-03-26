/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ble.Functionalities;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Sean Cadigal
 */
public class Fetch {
    private static final String rgx = "fetch\\(\\s*([\"'])(get|post)\\1\\s*,\\s*\\1.*\\1\\s*\\)";
    private String[] parameters;
    
    /**
     * Check if the line is a fetch statement
     * @return 
     */
    public static final boolean check(String line){
        Pattern p = Pattern.compile(rgx);
        Matcher m = p.matcher(line);
        
        return m.find();
    }
    
    private String[] getParams(String line){
        String[] params = null;
        String paramRgx = "\\(\\s*([\"'])(get|post)\\1\\s*,\\s*\\1.*\\1\\s*\\)";
        Pattern p = Pattern.compile(paramRgx);
        Matcher m = p.matcher(line);
        
        if (m.find()) {
            String uncleanParams = m.group();
            uncleanParams = uncleanParams.replaceAll("\\(|\\)", "");
            params = uncleanParams.split(",");
            for (int i = 0; i < params.length; i++){
                params[i] = params[i].trim();
            }
        }
        
        return params;
    }
    
    public Fetch(String line){
        this.parameters = getParams(line);
    }
    
    public String fetchExecute(Map<String, Object> memory){
        return null;
    }
}
