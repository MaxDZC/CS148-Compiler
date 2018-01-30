package ble.SyntaxAnalyzer;

/**
 *
 * @author Max
 */
public class CustomDatatypes {
    public static String design = ("design(\\s)+(\\w)+");
    public static String dataPattern = ("(design)\\s+\\w+\\s+(((let)\\s+\\w+\\s+((=)\\s+(\\w+|\\d+|(\\d+(.)\\d+)|((\")\\s*\\w+\\s*(\")))\\s+)*)*)(finish)*");
    public static String initializeData = ("(let)(\\s)(\\w+)(\\s?)\\=(\\s?)(\\w+)");
    public static String accessData = ("(\\w+)(\\.)(\\w+)");
    
    public static String getCustomDataTypesRegex(){
        
        return "("+design+"|"+dataPattern+"|"+initializeData+"|"+accessData+")";
    }
}
