/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Functions;

/**
 *
 * @author TJ
 */
public class Functions {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
        String check1 = "functionName(date, time)";
        String check2 = "var = functionName()";
        String check3 = "function functionname(name, username)\n" +
                        "	if(true)\n" +
                        "		print(\"true\")\n" +
                        "	else\n" +
                        "		print(\"false\")\n" +
                        "\n" +
                        "		print(\"hello world\")";
        
        if(check2.matches(pattern2)){
            System.out.println("True");
        }else{
            System.out.println("False");
        }
    }
    
}
