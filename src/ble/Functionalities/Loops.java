package ble.Functionalities;

import static ble.SyntaxAnalyzer.SyntaxAnalyzer.CONDITION;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

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

    static void repeat(String line) throws ScriptException {
        p = Pattern.compile(CONDITION);
        m = p.matcher(line);
        line = line.split("\\(")[1];
        line = line.split("\\)")[0];
        
        if(m.find()) {
            sem = new ScriptEngineManager();
            se = sem.getEngineByName("js");
            obj = se.eval(line);
            
            while(obj.toString().equals("true")) {
                // Do More loop stuff
            }
            
        } else {
            n = Integer.parseInt(line);
            while(n-- > 0) {
                // Loop stuff
            }
        }
    }
    
    static void forLoop(String line) {
        // Unable to access variables as of yet
    }
}

