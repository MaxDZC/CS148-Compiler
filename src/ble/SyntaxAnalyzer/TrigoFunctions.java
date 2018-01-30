package ble.SyntaxAnalyzer;

/**
 *
 * @author Max
 */
public class TrigoFunctions {
    public static String getTrigoFunctions(){
        return "\"*\\s*(((arc)?(sin|cos|tan|csc|cot|sec)|toRadians|toDegrees)\\s*\\((\\s+)*((-)\\d+|\\w+)(\\s+)*\\))\\s*\"*";
    }
}
