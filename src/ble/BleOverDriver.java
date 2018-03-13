/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ble;

import ble.Functionalities.MDASFunc;
import ble.Functionalities.MainProcess;
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
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.script.ScriptException;

/**
 *
 * @author addcarl
 */
public class BleOverDriver {
    
    private static final BleExtractor extractor = new BleExtractor();
    static SyntaxAnalyzer sa = new SyntaxAnalyzer();
    
    public void driver(String code) throws IOException, Exception
    {
        System.out.println("Trace:2");
        SyntaxAnalyzer syn = new SyntaxAnalyzer();
        ble.SyntaxAnalyzer.DataTypes data = new DataTypes();
        String status;
        
        if(syn.analyze(code)) {
        
            status = MainProcess.customDataType(code);
            System.out.println(status);

            String[] lines1 = code.split("\n");
            for (String line : lines1) {
                    if(!line.trim().equals("")) {
                        status = MainProcess.process(line, data);
                        System.out.println(status);
                    }
            }
        }
    }
}
