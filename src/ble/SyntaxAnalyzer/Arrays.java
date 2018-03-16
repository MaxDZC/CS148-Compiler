package ble.SyntaxAnalyzer;

/**
 *
 * @author Max
 */
public class Arrays {
    public static String getArrayPattern(){
        return "let\\s*[a-zA-z0-9]*(\\s*)?=(\\s*)?(Array|Array2D)\\(.*\\)"
                + "|(\\s*)?insert\\(.*\\)"
                + "|let\\s*[a-zA-z]*(\\s*)?=(\\s*)?(\\s*)?search\\(.*\\)"
                + "|(\\s*)?delete\\(.*\\)"
                + "|let\\s*[a-zA-z]*(\\s*)?=(\\s*)?(\\s*)?size\\(.*\\)"
                + "|(\\s*)?stringToArray\\(.*\\)"
                + "|let\\s*[a-zA-z]*(\\s*)?=(\\s*)?(\\s*)?retrieve\\(.*\\)"
                + "|(\\s*)?[a-zA-Z0-9]*(\\[.*\\])+(\\s*)?=(\\s*)?[a-zA-Z0-9]+";
    }
}
