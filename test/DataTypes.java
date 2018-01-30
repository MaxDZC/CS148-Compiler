package datatype;

/*
 * Author: Celine Olan
 */

public class DataTypes {
    public static void main(String[] args){
        //integer
        // int = 12345
        String initI = "let intx";
        String i = "int = 12345";
        String checkInt = "\\s*int\\s*=\\s*\\d+";
        
        //float
        // float = 123.45
        String f = "float = 123.45";
        String checkFloat = "\\s*float\\s*=\\s*\\d+\\.\\d+";
        
        //character
        //char = 'a'
        String c = "char = 'a'";
        String checkChar = "\\s*char\\s*=\\s*'[a-zA-Z]'";
        
        //string
        //string = "hello"
        String s = "string = hello";
        String checkString = "\\s*string\\s*=\\s*\\w+\\s*";
        
        //boolean
        //bool = true
        String b = "bool = true";
        String checkBool = "\\s*bool\\s*=\\s*(true|false)";
        
        //let x
        String x = "let a = 12";
        String finalCheck = "(\\s*let\\s*([a-zA-Z]|\\w+)\\s*|"
                + "\\s*let\\s*([a-zA-Z]|\\w+)\\s*=\\s*(\\d+|\\d+\\.\\d+|'[a-zA-Z]'|\\w+|(true|false))\\s*|"
                + "\\s*([a-zA-Z]|\\w+)\\s*=\\s*(\\d+|'[a-zA-Z]'|\\w+|(true|false))\\s*)";
        
        String justLet = "\\s*let\\s*([a-zA-Z]|\\w+)\\s*";
        String withLet = "\\s*let\\s*([a-zA-Z]|\\w+)\\s*=\\s*(\\d+|\\d+\\.\\d+|'[a-zA-Z]'|\\w+|(true|false))\\s*";
        String noLet = "\\s*([a-zA-Z]|\\w+)\\s*=\\s*(\\d+|'[a-zA-Z]'|\\w+|(true|false))\\s*";
        
        if(x.matches(finalCheck)){
            System.out.println("True");
        } else{
            System.out.println("False");
        }
    }
}
