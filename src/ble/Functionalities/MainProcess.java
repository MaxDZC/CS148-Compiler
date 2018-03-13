package ble.Functionalities;

import ble.SyntaxAnalyzer.Data;
import ble.SyntaxAnalyzer.Queue_stack;
import static ble.SyntaxAnalyzer.SyntaxAnalyzer.ARRAYS;
import static ble.SyntaxAnalyzer.SyntaxAnalyzer.CONDITIONAL;
import static ble.SyntaxAnalyzer.SyntaxAnalyzer.CUSTOM_DT;
import static ble.SyntaxAnalyzer.SyntaxAnalyzer.COOKIES;
import static ble.SyntaxAnalyzer.SyntaxAnalyzer.NUMBERS;
import static ble.SyntaxAnalyzer.SyntaxAnalyzer.DB;
import static ble.SyntaxAnalyzer.SyntaxAnalyzer.DISPLAY;
import static ble.SyntaxAnalyzer.SyntaxAnalyzer.FORLOOP;
import static ble.SyntaxAnalyzer.SyntaxAnalyzer.REPEAT;
import static ble.SyntaxAnalyzer.SyntaxAnalyzer.TIME_LIB;
import static ble.SyntaxAnalyzer.SyntaxAnalyzer.TRIGO;
import static ble.SyntaxAnalyzer.SyntaxAnalyzer.VALIDATION;
import java.io.IOException;
import java.util.Queue;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.script.ScriptException;

/**
 *
 * @author Max
 */
public class MainProcess {
    static Pattern p;
    static Matcher m;
    static Queue<Integer> qu;
    static Stack<Integer> st;
    
    public static String process(String line, ble.SyntaxAnalyzer.DataTypes data) throws ScriptException, IOException, Exception {
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
        
        p = Pattern.compile(CONDITIONAL);
        m = p.matcher(line);
        
        if(m.find()) {
            Condi cond = new Condi();
            cond.evalStatement(line);
        }
        
        p = Pattern.compile(NUMBERS);
        m = p.matcher(line);
        
        if(m.find()) {
           String ret= RecognizeNumbers.recognizeNumbers(line);
            System.out.println(ret);
        }
        
        p = Pattern.compile(TRIGO);
        m = p.matcher(line);
        
        if(m.find()) {
            TrigoFunctions.trigoFuncs(line);
        }
        
        p = Pattern.compile(DB);
        m = p.matcher(line);
        
        if(m.find()) {
            Database.test(line);
        }
        
        p = Pattern.compile(TIME_LIB);
        m = p.matcher(line);
        
        if(m.find()) {
            TimeLibraryModel t1 = new TimeLibraryModel();
            t1.checkTimeFunctionality(line);
        }
        
        p = Pattern.compile(Queue_stack.getQueue());
        m = p.matcher(line);
        
        if(m.find()) {
            Queue1.declareQueue(qu, line);
        }  
        
        p = Pattern.compile(Queue_stack.getStack());
        m = p.matcher(line);
        
        if(m.find()) {
            Stack1.declareStack(st, line);
        }
        
        p = Pattern.compile(ARRAYS);
        m = p.matcher(line);
        
        if(m.find()) {
        //    ArraysFunct.arrayStuff(line);
       //     ArraysFunct.findFunction(data.allVars);
        }
        
        return status;
    }
    
    public static String customDataType(String code) throws Exception{
        CustomDataTypes c = new CustomDataTypes();
        String stats = "Fail";
        
        p = Pattern.compile(CUSTOM_DT);
        m = p.matcher(code);
 
        if(m.find()) {
            c.run(code);
            stats = "Ok";
        }
        return stats;
    } 
}
