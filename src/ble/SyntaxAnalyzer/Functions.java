package ble.SyntaxAnalyzer;

/**
 *
 * @author Max
 */
public class Functions {
    public static String getFunctionRegex() {
        // functionName(date, time) //
        String pattern1 = "\\w+\\((\\w*|(\\w*,\\s*\\w+)*)\\)";
        
        // var = functionName() //
        String pattern2 = "\\w+\\s*=\\s*\\w+\\((\\w*|(\\w*,\\s*\\w+)*)\\)";
        
        /*
            function functionname(name, username)
                if(true)
                    print("true")
                else
                    print("false")

                    print("hello world")
        */
        String pattern3 = "function \\w+\\((\\w*|(\\w*,\\s*\\w+)*)\\)((\\s*|\\t|\\n)\\t.+)+";
        
        String patternFunctionCall = pattern1;
        String patternMain = "function main\\((\\w*|(\\w*,\\s*\\w+)*)\\)((\\s*|\\t|\\n)\\t.+)+";
        String patternFuncHeader = "function \\w+\\((\\w*|(\\w*,\\s*\\w+)*)\\)";
        String patternFuncBody = "((\\s*|\\t|\\n)\\t.+)+";
        
        String function = "("+patternMain+"|"+patternFuncHeader+"|"+patternFuncBody+"|"+patternFunctionCall+")";
        
        return function;
    }
}
