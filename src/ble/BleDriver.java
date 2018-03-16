package ble;


import ble.Functionalities.MDASFunc;
import ble.Functionalities.MainProcess;
import ble.Http.Server;
import ble.Storage.BleStorage;
import ble.SyntaxAnalyzer.*;
import ble.injector.ImbedHtml;
import ble.objects.ArrayBle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
    	ble.SyntaxAnalyzer.DataTypes data = new DataTypes();
        //data.allVars.
    	SyntaxAnalyzer syn = new SyntaxAnalyzer();
    	MainProcess mp = new MainProcess();
    	
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
        
        File testFile = new File("public/array.ble");
        File evaluateFile = new File("bledocs/"+extractor.extractBle(testFile));
        System.out.println("FILE EXTRACTED NAME: "+evaluateFile.getName());
        
        
        //Assume output processed from special task no. 2...
        
        //Assume files come from http request or socket...
        int ctr = 80;
        String status;
        
        for (File file : CONSTANT_LISTOFFILES) {
                if (file.isFile()) {
                    System.out.println("...Preparing files for browser upload");
                    bleCode = new String(Files.readAllBytes(Paths.get("bledocs/"+file.getName())), StandardCharsets.UTF_8);
                    ArrayList<String> resultGet = new ArrayList<>();
                    String[] results;
                    String result = extractor.extractBle(file);
                    
                    System.out.println(result);
                    
                    if(syn.analyze(result)) {
                        System.out.println("Analyzed!!!");
                        String[] lines1 = result.split("\\n");
                        
                        for (String line : lines1) {
                                if(!line.trim().equals("")) {
                                    System.out.println("Line :"+line);
                                    resultGet.add(line);
                                    status = MainProcess.process(line, data);
                                }
                        }
	                    //results[0] = "varX = "+" "+MDASFunc.evalExp(result);
	                 
                        
                        results = resultGet.toArray(new String[0]);

                        ImbedHtml injectResult = new ImbedHtml(bleCode, results);
                        Server server = new Server();

                        System.out.println("File: "+file.getName());
                        injectResult.imbedResults();
                        server.setContext(file.getName());
                        server.setResponse(injectResult.getHtmlCode());
                        server.setSocketNo(82);
                        System.out.println("Located at: localhost:"+server.getSocketNo()+server.getContext());

                        //Server.server(null);
                        ctr++;
	                }
                }
        
        }
        
        Data<ArrayBle> arr = (Data<ArrayBle>) data.allVars.get("arr");
        ArrayBle array = (ArrayBle) arr.getValue();
        //System.out.println("Array arr = "+array);
        for(i=0; i<array.size(); i++){
            System.out.println("Index "+i+": "+array.get(i));
        }
        System.out.println("Index key: "+array.get("key2"));
        Data<Object> x = (Data<Object>)data.allVars.get("x");
        System.out.println("X value: "+x.getValue());
        Data<Object> stat = (Data<Object>)data.allVars.get("stat");
        System.out.println("Stat value: "+stat.getValue());
                        
    }

}
