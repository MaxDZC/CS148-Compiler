package ble.Functionalities;

import static ble.SyntaxAnalyzer.SyntaxAnalyzer.CONDITION;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import ble.Functionalities.MainProcess;
import java.io.IOException;

/**
 *
 * @author Max
 */
public class Loops {
    static ScriptEngineManager sem;
    static ScriptEngine se;
    static Object obj;
    static Pattern p;
    static Matcher m;
    static int n;
    

    static void repeat(String line, String[] lines, int idx) throws ScriptException, IOException {
        
        ble.SyntaxAnalyzer.DataTypes data = new ble.SyntaxAnalyzer.DataTypes();
        p = Pattern.compile(CONDITION);
        m = p.matcher(line);
        line = line.split("\\(")[1];
        line = line.split("\\)")[0];
        if(m.find()) {
            sem = new ScriptEngineManager();
            se = sem.getEngineByName("js");
            obj = se.eval(line);
            
            while(obj.toString().equals("true")) {
                if(lines.length > idx + 1){
                    System.out.println(lines[idx+1]);
                }
            }
        } else {
            p = Pattern.compile("\\d+");
            m = p.matcher(line);
            if(m.find()){
                
                n = Integer.parseInt(m.group());
              
                while(n-- > 0) {
                    if(lines.length > idx + 1){
                        //automatically assusmes the next line as within it's scope due to tab detection
                        System.out.println("loop works! but with a problem cannot detect tab from next line due to all the lines had all their spaces removed");
                    }
                }
            }
        }
    }
    
    static void forLoop(String line) {
        // Unable to access variables as of yet
    }
}

