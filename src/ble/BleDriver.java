
package ble;

<<<<<<< HEAD

import ble.Functionalities.MDASFunc;
import ble.Functionalities.MainProcess;
import ble.Http.Server;
import ble.Storage.BleStorage;
import ble.SyntaxAnalyzer.*;
import ble.injector.ImbedHtml;
import ble.objects.ArrayBle;
import java.util.Arrays;
import java.io.BufferedReader;
=======
// import ble.Http.Server;
import ble.SyntaxAnalyzer.*;
import ble.injector.Display;
>>>>>>> origin/announcements
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

import Ble.Network.Network;
import Ble.Network.Http.Server;

/**
 *
 * @author Max
 */


public class BleDriver {
    
    protected Network network = null;
    
    static SyntaxAnalyzer sa = new SyntaxAnalyzer();
    //Temporary for reading every .ble file for http upload to browser
    private static final File CONSTANT_FOLDER = new File("bledocs/");
    private static final File[] CONSTANT_LISTOFFILES = CONSTANT_FOLDER.listFiles();
    private static final BleExtractor extractor = new BleExtractor();
    
    public BleDriver() {
    }
    
    
    
    public static void main(String[] args) throws IOException, ScriptException, Exception {
<<<<<<< HEAD
    	ble.SyntaxAnalyzer.DataTypes data = new DataTypes();
        //data.allVars.
    	SyntaxAnalyzer syn = new SyntaxAnalyzer();
    	MainProcess mp = new MainProcess();
    	
    	String bleCode, temp;
=======
        BleDriver driver = new BleDriver();
        
           Server server = null;
        
        server = driver.network().createServer(8080);
        server.start();
        
        server = driver.network().createServer(9999);
        server.start();
        
        server = driver.network().createServer(7777);
        server.start();
        
        
        String bleCode, temp;
>>>>>>> origin/announcements
        String[] lines;
        int i;
       
        /*
        bleCode = new String(Files.readAllBytes(Paths.get("helloworld.ble")), StandardCharsets.UTF_8);
        
        if(sa.analyze(bleCode)) {
            System.out.println("Correct Syntax!");
            // Getting rid of Comments
            bleCode = Comments.comments(bleCode);
            
            // Check the code line-by-line and check what function to use 😞
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
<<<<<<< HEAD
=======
        //Testing Display...
        /*
            bleCode = new String(Files.readAllBytes(Paths.get("bledocs/9999/testing.ble")), StandardCharsets.UTF_8);
            String[] results = new String[2];
            
            results[0] = "varX = 10";
            results[1] = "varY = 20";
>>>>>>> origin/announcements
        
            Display displaySample = new Display(bleCode, results);
            displaySample.display();
            
        return;
        */
        //Assume files come from http request or socket...
<<<<<<< HEAD
        int ctr = 80;
        String status;
        
=======
        /* int ctr = 8080;
>>>>>>> origin/announcements
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
                        
                        for(int i1 = 0 ; i1 < lines1.length; i1++) {
                            String line = lines1[i1];
                        	if(!line.trim().equals("")) {
                        		resultGet.add(line);
                                System.out.println("Line :"+line);
                                resultGet.add(line);
                                status = MainProcess.process(lines1,i1, data);
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
<<<<<<< HEAD
        
        }
        
        Data<ArrayBle> arr = (Data<ArrayBle>) data.vars.get("arr");
        ArrayBle array = (ArrayBle) arr.getValue();
        //System.out.println("Array arr = "+array);
        for(i=0; i<array.size(); i++){
            System.out.println("Index "+i+": "+array.get(i));
        }
        System.out.println("Index key: "+array.get("key2"));
        Data<Object> x = (Data<Object>)data.vars.get("x");
        System.out.println("X value: "+x.getValue());
        Data<Object> stat = (Data<Object>)data.vars.get("stat");
        System.out.println("Stat value: "+stat.getValue());
                        
    }

}
=======
        } */
    }
    
    public BleDriver() {
        this.network = new Network();
    }
    
    public Network network() {
        return this.network;
    }
}
>>>>>>> origin/announcements
