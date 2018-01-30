package ble.Functionalities;

import ble.SyntaxAnalyzer.DisplayRegex;
import static ble.SyntaxAnalyzer.SyntaxAnalyzer.CONDITION;
import static ble.SyntaxAnalyzer.SyntaxAnalyzer.COOKIES;
import static ble.SyntaxAnalyzer.SyntaxAnalyzer.DISPLAY;
import static ble.SyntaxAnalyzer.SyntaxAnalyzer.FORLOOP;
import static ble.SyntaxAnalyzer.SyntaxAnalyzer.REPEAT;
import static ble.SyntaxAnalyzer.SyntaxAnalyzer.VALIDATION;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author Max
 */
public class MainProcess {
    static Pattern p;
    static Matcher m;
    
    public static String process(String line) throws ScriptException, IOException {
        String status;
        
        status = "Ok";
        
        p = Pattern.compile(REPEAT);
        m = p.matcher(line);

        if(m.find()) {
            Loops.repeat(line);
        }
        
        p = Pattern.compile(FORLOOP);
        m = p.matcher(line);
        
        if(m.find()) {
            Loops.forLoop(line);
        }

        p = Pattern.compile(COOKIES);
        m = p.matcher(line);

        if(m.find()) {
            status = Cookies.cookies(line);
        }
        
        p = Pattern.compile(DISPLAY);
        m = p.matcher(line);
        
        if(m.find()) {
            Display.run(line);
        }
        
        p = Pattern.compile(VALIDATION);
        m = p.matcher(line);
        
        if(m.find()) {
            ValidationFunct.run(line);
        }
        
        
        
        return status;
    }       
}
