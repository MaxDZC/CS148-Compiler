package ble;

import ble.Functionalities.MainProcess;
import ble.SyntaxAnalyzer.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.script.ScriptException;

/**
 *
 * @author Max
 */
public class BleDriver {
    static SyntaxAnalyzer sa = new SyntaxAnalyzer();
    
    public static void main(String[] args) throws IOException, ScriptException {
        String bleCode, temp;
        String[] lines;
        int i;
       
        bleCode = new String(Files.readAllBytes(Paths.get("helloworld.ble")), StandardCharsets.UTF_8);
        
        if(sa.analyze(bleCode)) {
            System.out.println("Correct Syntax!");
            // Getting rid of Comments
            bleCode = Comments.comments(bleCode);
            
            // Check the code line-by-line and check what function to use :(
            lines = bleCode.split(System.getProperty("line.separator"));
            
            for(i = 0; i < lines.length; i++) {
                MainProcess.process(lines[i]);
            }
            
        } else {
            System.out.println("Syntax Error");
        }
        
        
    }
    
}
