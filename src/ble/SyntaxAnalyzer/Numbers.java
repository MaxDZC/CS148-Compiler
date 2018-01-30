package ble.SyntaxAnalyzer;

/**
 *
 * @author Max
 */
public class Numbers {
    private static String patternNumBinary = ("\\bb_[01]+\\b(\\s|\\n)*");
    private static String patternNumHexa = ("\\bh_([A-F|a-f|0-9]+[A-F|a-f|0-9]+)+\\b(\\s|\\n)*");
    private static String patternNumOctal = ("\\bo_([1-7])+\\b(\\s|\\n)*;?");
    private static String patternNumDecimal = ("\\b\\d+\\b(\\s|\\n)*");
    
    public static String getBinary(){
        return patternNumBinary;
    }
    public static String getHexadecimal(){
        return patternNumHexa;
    }
    public static String getOctal(){
        return patternNumOctal;
    }
    public static String getDecimal(){
        return patternNumDecimal;
    }
}
