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
import java.io.IOException;
import java.util.Queue;
import java.util.Stack;
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
    
    public static String process(String[] line, int idx, ble.SyntaxAnalyzer.DataTypes data) throws ScriptException, IOException {
        String status;
<<<<<<< HEAD

        status = "Ok";

=======
        
        status = "Syntax Error on this Line!!!";
        
>>>>>>> origin/announcements
        p = Pattern.compile(REPEAT);
        m = p.matcher(line[idx]);

        if(m.find()) {
<<<<<<< HEAD
            Loops.repeat(line[idx], line, idx);
        }else if (!m.find()){
            System.out.println("Syntax Error on repeat loop function");
=======
            Loops.repeat(line);
            status = "Syntax Correct";
>>>>>>> origin/announcements
        }

        p = Pattern.compile(FORLOOP);
        m = p.matcher(line[idx]);

        if(m.find()) {
<<<<<<< HEAD
            Loops.forLoop(line[idx]);
        }else if (!m.find()){
            System.out.println("Syntax Error on for loop function");
=======
            Loops.forLoop(line);
            status = "Syntax Correct";
>>>>>>> origin/announcements
        }

        p = Pattern.compile(COOKIES);
        m = p.matcher(line[idx]);

        if(m.find()) {
<<<<<<< HEAD
            status = Cookies.cookies(line[idx]);
        }else if (!m.find()){
            System.out.println("Syntax Error on cookies");
=======
            status = Cookies.cookies(line);
            status = "Syntax Correct";
>>>>>>> origin/announcements
        }

        p = Pattern.compile(DISPLAY);
        m = p.matcher(line[idx]);

        if(m.find()) {
<<<<<<< HEAD
            Display.run(line[idx]);
        }else if (!m.find()){
            System.out.println("Syntax Error on display function");
=======
            Display.run(line);
            status = "Syntax Correct";
>>>>>>> origin/announcements
        }

        p = Pattern.compile(VALIDATION);
        m = p.matcher(line[idx]);

        if(m.find()) {
<<<<<<< HEAD
            ValidationFunct.run(line[idx]);
        }else if (!m.find()){
            System.out.println("Syntax Error on validation");
=======
            ValidationFunct.run(line);
            status = "Syntax Correct";
>>>>>>> origin/announcements
        }

        p = Pattern.compile(CONDITIONAL);
        m = p.matcher(line[idx]);

        if(m.find()) {
            Condi cond = new Condi();
<<<<<<< HEAD
            cond.evalStatement(line[idx]);
        }else if (!m.find()){
            System.out.println("Syntax Error on conditional statements");
=======
            cond.evalStatement(line);
            status = "Syntax Correct";
>>>>>>> origin/announcements
        }

        p = Pattern.compile(DB);
        m = p.matcher(line[idx]);

        if(m.find()) {
<<<<<<< HEAD
            Database.test(line[idx]);
        }else if (!m.find()){
            System.out.println("Syntax Error on database syntax");
=======
            Database.test(line);
            status = "Syntax Correct";
>>>>>>> origin/announcements
        }

        p = Pattern.compile(TIME_LIB);
        m = p.matcher(line[idx]);

        if(m.find()) {
            TimeLibraryModel t1 = new TimeLibraryModel();
<<<<<<< HEAD
            t1.checkTimeFunctionality(line[idx]);
        }else if (!m.find()){
            System.out.println("Syntax Error on time library");
=======
            t1.checkTimeFunctionality(line);
            status = "Syntax Correct";
>>>>>>> origin/announcements
        }

        p = Pattern.compile(Queue_stack.getQueue());
        m = p.matcher(line[idx]);

        if(m.find()) {
<<<<<<< HEAD
            Queue1.declareQueue(qu, line[idx]);
        }else if (!m.find()){
            System.out.println("Syntax Error on queue functions");
        }
=======
            Queue1.declareQueue(qu, line);
            status = "Syntax Correct";
        }  
>>>>>>> origin/announcements
        
        p = Pattern.compile(Queue_stack.getStack());
        m = p.matcher(line[idx]);

        if(m.find()) {
<<<<<<< HEAD
            Stack1.declareStack(st, line[idx]);
        }else if (!m.find()){
            System.out.println("Syntax Error on stack functions");
=======
            Stack1.declareStack(st, line);
            status = "Syntax Correct";
>>>>>>> origin/announcements
        }

        p = Pattern.compile(ARRAYS);
        m = p.matcher(line[idx]);

        if(m.find()) {
<<<<<<< HEAD
            ArraysFunct.arrayStuff(line[idx], data.vars);
            ArraysFunct.findFunction(data.vars);
=======
            ArraysFunct.arrayStuff(line);
            status = "Syntax Correct";
>>>>>>> origin/announcements
        }
        
        return status;
    }       
}
