package ble.SyntaxAnalyzer;

/**
 *
 * @author Max
 */
public class MDAS {
    public static String getMDASPattern(){
        return "\\s*((\\-|\\+|\\s*)\\s*(\\d+(\\.\\d+)?|[a-zA-Z]\\w*)(\\s*([+-[*]/%]|\\*\\*)\\s*(\\s+\\-|\\s+\\+|\\s*)\\s*(\\d+(\\.\\d+)?|[a-zA-Z]\\w*))+)*\\s*";
    }
}
