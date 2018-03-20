package ble;

// import ble.Http.Server;
import ble.SyntaxAnalyzer.*;
import ble.injector.Display;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    
    
    
    public static void main(String[] args) throws IOException, ScriptException, Exception {
        BleDriver driver = new BleDriver();
        
           Server server = null;
        
        server = driver.network().createServer(8080);
        server.start();
        
        server = driver.network().createServer(9999);
        server.start();
        
        server = driver.network().createServer(7777);
        server.start();
        
        
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
        
        //Assume output processed from special task no. 2...
        //Testing Display...
        /*
            bleCode = new String(Files.readAllBytes(Paths.get("bledocs/9999/testing.ble")), StandardCharsets.UTF_8);
            String[] results = new String[2];
            
            results[0] = "varX = 10";
            results[1] = "varY = 20";
        
            Display displaySample = new Display(bleCode, results);
            displaySample.display();
            
        return;
        */
        //Assume files come from http request or socket...
        /* int ctr = 8080;
        for (File file : CONSTANT_LISTOFFILES) {
                if (file.isFile()) {
                    System.out.println("...Preparing files for browser upload");
                    bleCode = new String(Files.readAllBytes(Paths.get("bledocs/"+file.getName())), StandardCharsets.UTF_8);
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
        } */
    }
    
    public BleDriver() {
        this.network = new Network();
    }
    
    public Network network() {
        return this.network;
    }
}
