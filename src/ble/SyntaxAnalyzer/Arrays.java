package ble.SyntaxAnalyzer;

/**
 *
 * @author Max
 */
public class Arrays {
    public static String getArrayPattern(){
        return "let\\s*[a-zA-z]*(\\s*)?=(\\s*)?Array\\(\\)";
    }
}
