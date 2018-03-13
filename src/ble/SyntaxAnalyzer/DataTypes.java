package ble.SyntaxAnalyzer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DataTypes {
      
    public static Map<String, Data> vars = new HashMap<String, Data>(); 
    
    public static Map<Integer,Map<String, Data>> varMap = new HashMap<Integer,Map<String, Data>>();

        
    
    public static void storeVar(String toCheck) throws IOException
    {
        String varName, value;
        String letVar = "\\s*let\\s*([a-z|A-Z]+\\w*)\\s*[\r\n]";
        String letVarVal = "\\s*let\\s*([a-z|A-Z]+\\w*)\\s*=\\s*(\"?([^\"]*)\"|-?\\d+(\\.?\\d+|))+\\s*[\r\n]";
        String varVal = "\\s*([a-z|A-Z]+\\w*)\\s*=\\s*(\"?([^\"]*\")|([+-]?([0-9]*[.])?[0-9]+))\\s*[\r\n]";
        String letVarVar = "\\s*let\\s*([a-z|A-Z]+\\w*)\\s*=\\s*([a-z|A-Z]+\\w*)\\s*[\r\n]";
        String varVar = "\\s*([a-z|A-Z]+\\w*)\\s*=\\s*([a-z|A-Z]+\\w*)\\s*[\r\n]";
        System.out.println("\n***statement:\n" + toCheck);



        if(toCheck.matches(letVar)){
            varDec(toCheck);
        }else if(toCheck.matches(letVarVal)){
            varDec(toCheck);
        }else if(toCheck.matches(varVal)){
            varName = toCheck.replaceAll(varVal, "$1"); 
            value = toCheck.replaceAll(varVal, "$2");
            if(doesVarExist(varName)){
                changeVal(varName, value);
            }else{
                System.out.println("var " + varName + " not declared or outside of scope lol");
            }
        }else if(toCheck.matches(letVarVar)){
            varDec(toCheck);
        }else if(toCheck.matches(varVar)){
            value = toCheck.replaceAll(varVar, "$2");
            varName = toCheck.replaceAll(varVar, "$1");

            if(doesVarExist(varName) && doesVarExist(value)){
                changeVal(varName, value);
            }else{
                System.out.println("cannot find " + varName + " or " + value + " or both ");
            }
        }else{
            System.out.println(toCheck + "**does not match syntax");
        }

        //toCheck.matches(varVar)
            
    } 
    private static void varDec(String statement)
    {
        String letVar = "\\s*let\\s*([a-z|A-Z]+\\w*)\\s*[\r\n]";            
            
        String letVarVal = "\\s*let\\s*([a-z|A-Z]+\\w*)\\s*=\\s*(\"?([^\"]*)\"|-?\\d+(\\.?\\d+|))+\\s*[\r\n]";
        String letVarVar = "\\s*let\\s*([a-z|A-Z]+\\w*)\\s*=\\s*(([a-z|A-Z]+\\w*))\\s*[\r\n]";


        String varName = statement.replaceAll(letVar, "$1");
        String value = statement.replaceAll(letVarVal, "$2");



        if(value == statement){
            // no assignment, only declaration
            value = "\"\"";
        }else{
            varName = statement.replaceAll(letVarVal, "$1");
        }

        if(varName == statement){
            //let var = var2
            varName = statement.replaceAll(letVarVar, "$1"); 
            value =  statement.replaceAll(letVarVar, "$2");
        }

       

        changeVal(varName, value);
    }
    private static void changeVal(String name, String val)
    {
        val = val.replaceAll("\n", "");
        String intExp = "(?<!\\.)\\b[0-9]+\\b(?!\\.)";

        String stringExp = "\"([^\"]*)\"";
        String floatExp = "\\d+\\.\\d+";

        String varExp = "\\s*([a-z|A-Z]+\\w*)\\s*";

       
        try{
            if(val.matches(intExp)){
                int res = Integer.parseInt(val);
                Data<Integer> valu = new Data<Integer>();
               // valu.setScope(true); // false if non static
                valu.setValue(res);

                vars.put(name, valu);

            }else if(val.matches(stringExp)){

                val = val.replaceAll(stringExp, "$1");
                Data<String> valu = new Data<String>();
               // valu.setScope(false); // false if non static
                valu.setValue(val);
                vars.put(name, valu);

            }else if(val.matches(floatExp)){

                float res = Float.parseFloat(val);

                Data<Float> valu = new Data<Float>();
                valu.setValue(new Float(res).floatValue());
              //  valu.setScope(false); // false if non static
                vars.put(name, valu);
            }else if(val.matches(varExp)){
                System.out.println("inside varExp");
                if(doesVarExist(val)){
                    vars.put(name, vars.get(val));
                }else{
                    //error message (var does not exist)
                    Data.outVarUndeclared(val);
                    System.out.println("unsuccessfully created val");
                    // THROW ERROR
                }
            }else{
                System.out.println("Data Type Error");
            }
            System.out.println("data type:\n" + vars.get(name).getValue().getClass());
            System.out.println(name+" successfully stored?\n"+vars.containsKey(name));
            System.out.println("value of "+name+":\n"+vars.get(name).getValue());
        }catch(Exception e){
            System.out.println(e);
        }   
    }
        
    public static boolean doesVarExist(String varName)
    {
        if(vars.containsKey(varName) && Data.currScope == vars.get(varName).getScope()){
            return true;
        }else{
            return false;
        }
            
    }
    
    /***************************************************************************
     * getValue shit
     * needs improvement
    ***************************************************************************/
    public static float getValueFloat(String varName)
    {
        return (float) vars.get(varName).getValue();
    }
    public static int getValueInt(String varName)
    {
            return (int) vars.get(varName).getValue();
    }  
    public static String getValueString(String varName)
    {
            return (String) vars.get(varName).getValue();
    }
       
    /***************************************************************************
     * Scope shit
    ***************************************************************************/
    public static void setNewScope() 
    {
       // from old scope
       varMap.put(Data.currScope, vars);
       vars = new HashMap<String, Data>(); 
       // call if new scope
       Data.currScope++;
    }
    public static void endScope()
    {
        // delete all data with currScope then: 
        Data.currScope--;
        vars = varMap.get(Data.currScope);
        //deleteVarInScope();
    }
        
    public static String getSyntax() {
        String syntax;
        
        syntax = "(\\s*let\\s*([a-z|A-Z]+\\w*)\\s*[\r\n]";

        syntax += "\\s*let\\s*([a-z|A-Z]+\\w*)\\s*=\\s*(\"?([^\"]*)\"|-?\\d+(\\.?\\d+|))+\\s*[\r\n]";
        syntax += "\\s*([a-z|A-Z]+\\w*)\\s*=\\s*(\"?([^\"]*\")|([+-]?([0-9]*[.])?[0-9]+))\\s*[\r\n]";
        syntax += "\\s*let\\s*([a-z|A-Z]+\\w*)\\s*=\\s*([a-z|A-Z]+\\w*)\\s*[\r\n]";
        syntax += "\\s*([a-z|A-Z]+\\w*)\\s*=\\s*([a-z|A-Z]+\\w*)\\s*[\r\n]";
        
        
        
        System.out.println(syntax);
    
        return syntax;
    }
}
	
	
	


