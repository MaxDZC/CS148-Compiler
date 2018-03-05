package ble;


import ble.Functionalities.MDASFunc;
import ble.Http.Server;
import ble.Storage.BleStorage;
import ble.SyntaxAnalyzer.*;
import ble.injector.ImbedHtml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
    //Temporary for reading every .ble file for http upload to browser
    private static final File CONSTANT_FOLDER = new File("bledocs/");
    private static final File[] CONSTANT_LISTOFFILES = CONSTANT_FOLDER.listFiles();
    private static final BleExtractor extractor = new BleExtractor();
    
    public BleDriver() {
    }
    
    public static void main(String[] args) throws IOException, ScriptException, Exception {
    	BleStorage storage = new BleStorage();
        BleSyntaxChecker syntax = new BleSyntaxChecker();
        
    	String bleCode, temp;
        String[] lines;
        int i;
       
        /*
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
        */
        
        // Testing of extractor in BLE
        /*
        File testFile = new File("public/array.ble");
        File evaluateFile = new File("bledocs/"+extractor.extractBle(testFile));
        System.out.println("FILE EXTRACTED NAME: "+evaluateFile.getName());
        
        // Checks syntax of evaluate file
        BufferedReader reader = new BufferedReader(new FileReader(evaluateFile));
        String line = null;
        while((line = reader.readLine()) != null) {
        	if(syntax.syntaxCheck(line, storage)) {
        		System.out.println("Syntax Correct");
        	} else {
        		System.err.println("Error Syntax");
        	}
        }
        */
        //Assume output processed from special task no. 2...
        
        //Assume files come from http request or socket...
        int ctr = 80;
        for (File file : CONSTANT_LISTOFFILES) {
                if (file.isFile()) {
                    System.out.println("...Preparing files for browser upload");
                    bleCode = new String(Files.readAllBytes(Paths.get("bledocs/"+file.getName())), StandardCharsets.UTF_8);
                    
                    String result =extractor.extractBle(file);
                    
                    result = result.replaceAll("[a-zA-Z]", "");
                    result = result.replaceAll("\\s*=\\s*", "");
                    
                    String[] results = new String[1];
                    
                    results[0] = "varX = "+" "+MDASFunc.evalExp(result);
                    ImbedHtml injectResult = new ImbedHtml(bleCode, results);
                    Server server = new Server();
                    
                    System.out.println("File: "+file.getName());
                    injectResult.imbedResults();
                    server.setContext(file.getName());
                    server.setResponse(injectResult.getHtmlCode());
                    server.setSocketNo(ctr);
                    System.out.println("Located at: localhost:"+server.getSocketNo()+server.getContext());
                    
                    Server.server(null);
                    ctr++;
                }
        }
    }

}
