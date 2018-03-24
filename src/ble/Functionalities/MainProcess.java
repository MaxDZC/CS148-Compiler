package ble.Functionalities;

import ble.SyntaxAnalyzer.Data;
import ble.SyntaxAnalyzer.Queue_stack;
import static ble.SyntaxAnalyzer.SyntaxAnalyzer.ARRAYS;
import static ble.SyntaxAnalyzer.SyntaxAnalyzer.CONDITIONAL;
import static ble.SyntaxAnalyzer.SyntaxAnalyzer.COOKIES;
import static ble.SyntaxAnalyzer.SyntaxAnalyzer.DB;
import static ble.SyntaxAnalyzer.SyntaxAnalyzer.DISPLAY;
import static ble.SyntaxAnalyzer.SyntaxAnalyzer.FORLOOP;
import static ble.SyntaxAnalyzer.SyntaxAnalyzer.REPEAT;
import static ble.SyntaxAnalyzer.SyntaxAnalyzer.TIME_LIB;
import static ble.SyntaxAnalyzer.SyntaxAnalyzer.VALIDATION;
import static ble.SyntaxAnalyzer.SyntaxAnalyzer.DATATYPES;
import java.io.IOException;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.script.ScriptException;
import ble.SyntaxAnalyzer.DataTypes; 

/**
 *
 * @author Max
 */
public class MainProcess {
    static Pattern p;
    static Matcher m;
    static Queue<Integer> qu;
    static Stack<Integer> st;
    
    public static String process(String[] line, int idx, ble.SyntaxAnalyzer.DataTypes data) throws ScriptException, IOException {
        String status;

        status = "Ok";
        
        p = Pattern.compile(DATATYPES);
        m = p.matcher(line[idx]);
        if(m.find()){
            DataTypes dTypes = new DataTypes(); 
            dTypes.storeVar(line[idx]);
            System.out.println("DECLARED MAYBE");
            dTypes.printAllVars(); 
        
        }
        
        p = Pattern.compile(REPEAT);
        m = p.matcher(line[idx]);

        if(m.find()) {
            Loops.repeat(line[idx], line, idx);
        }else if (!m.find()){
            System.out.println("Syntax Error on repeat loop function");
        }

        p = Pattern.compile(FORLOOP);
        m = p.matcher(line[idx]);

        if(m.find()) {
            Loops.forLoop(line[idx]);
        }else if (!m.find()){
            System.out.println("Syntax Error on for loop function");
        }

        p = Pattern.compile(COOKIES);
        m = p.matcher(line[idx]);

        if(m.find()) {
            status = Cookies.cookies(line[idx]);
        }else if (!m.find()){
            System.out.println("Syntax Error on cookies");
        }

        p = Pattern.compile(DISPLAY);
        m = p.matcher(line[idx]);

        if(m.find()) {
          //  Display.run(line[idx]);
        }else if (!m.find()){
            System.out.println("Syntax Error on display function");
        }

        p = Pattern.compile(VALIDATION);
        m = p.matcher(line[idx]);

        if(m.find()) {
            ValidationFunct.run(line[idx]);
        }else if (!m.find()){
            System.out.println("Syntax Error on validation");
        }

        p = Pattern.compile(CONDITIONAL);
        m = p.matcher(line[idx]);

        if(m.find()) {
            Condi cond = new Condi();
            cond.evalStatement(line[idx]);
        }else if (!m.find()){
            System.out.println("Syntax Error on conditional statements");
        }

        p = Pattern.compile(DB);
        m = p.matcher(line[idx]);

        if(m.find()) {
            Database.test(line[idx]);
        }else if (!m.find()){
            System.out.println("Syntax Error on database syntax");
        }

        p = Pattern.compile(TIME_LIB);
        m = p.matcher(line[idx]);

        if(m.find()) {
            TimeLibraryModel t1 = new TimeLibraryModel();
            t1.checkTimeFunctionality(line[idx]);
        }else if (!m.find()){
            System.out.println("Syntax Error on time library");
        }

        p = Pattern.compile(Queue_stack.getQueue());
        m = p.matcher(line[idx]);

        if(m.find()) {
            Queue1.declareQueue(qu, line[idx]);
        }else if (!m.find()){
            System.out.println("Syntax Error on queue functions");
        }
        
        p = Pattern.compile(Queue_stack.getStack());
        m = p.matcher(line[idx]);

        if(m.find()) {
            Stack1.declareStack(st, line[idx]);
        }else if (!m.find()){
            System.out.println("Syntax Error on stack functions");
        }

        p = Pattern.compile(ARRAYS);
        m = p.matcher(line[idx]);

        if(m.find()) {
            ArraysFunct.arrayStuff(line[idx], data.vars);
            ArraysFunct.findFunction(data.vars);
        }
        
        return status;
    }       
}
