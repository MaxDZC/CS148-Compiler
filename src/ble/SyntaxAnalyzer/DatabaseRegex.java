package ble.SyntaxAnalyzer;

/**
 *
 * @author Max
 */
public class DatabaseRegex {
    public static String getDatabaseRegex() {
        String syntax;
        
        syntax = "(let\\s+\\w+\\s+=\\s+" +
                "(table|insertRow)" +
                "\\(\\s*\\w+\\s*\\)";
       
        syntax += "|let\\s+\\w+\\s+=\\s+" +
                "(retrieveRows|updateRows)" +
                "\\(\\s*\\w+\\s*,\\s*\\w+\\s*\\)";
		
        syntax += "|let\\s+\\w+\\s+=\\s+" +
                "(deleteRows)" +
                "\\(\\s*\\w+\\s*,\\s*\\w+\\s*,\\s*\\w+\\s*\\)";
		
        syntax += "|let\\s+\\w+\\s+=\\s+" +
                "(table)" +
                "\\(\\s*\\w+\\s*"
            + "(,\\s*\\w+\\s*)+"
            + "\\))";
		
        return syntax;
    }
}
