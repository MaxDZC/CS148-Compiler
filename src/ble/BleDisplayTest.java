/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ble;

import ble.injector.Display;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Hourani
 */
public class BleDisplayTest {
    public static void main(String[] args) {
        try{
            String bleCode = new String(Files.readAllBytes(Paths.get("bledocs/testing.ble")), StandardCharsets.UTF_8);
            String[] results = new String[2];
            //Output is hardcoded as to make up for missing mechanism of passing processed code from extractor...
            results[0] = "varX = 10";
            results[1] = "varY = 20";
            
            Display displaySample = new Display(bleCode, results);
            displaySample.display();
            
            System.out.println(displaySample.getHtmlCode());
            
        }catch(Exception e){
            System.err.println(e.fillInStackTrace());
        }
       
        
    }
}
