package ble.SyntaxAnalyzer;

/**
 *
 * @author Max
 */
public class TimeLibrary {
    public static String getTimeSyntax() {
        String syntax;
        
        syntax = "(\\s*(let)\\s*\\w+\\s*(=)+\\s*(Timer)+";
        syntax += "|\\s*(setMax)+\\s*\\(\\w+\\)\\s*";
        syntax += "|\\s*(setMax)\\s*";
        syntax += "|\\s*(start)\\s*";
        syntax += "|\\s*(reset)\\s*";
        syntax += "|\\s*(pause)\\s*";
        syntax += "|\\s*(getTime)\\s*)";
        
        return syntax;
    }
}
