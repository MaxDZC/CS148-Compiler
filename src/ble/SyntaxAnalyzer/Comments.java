package ble.SyntaxAnalyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Max
 */
public class Comments {
    
    public static String comments(String file) throws IOException {
        String patternComment = "(/\\*([^*]|[\\r\\n]|(\\*+([^*/]|[\\r\\n])))*\\*+/)|(//.*)";
        String c = file;
        
        String replaceAll = c.replaceAll(patternComment, "");
        
        return replaceAll;
    } 
}
