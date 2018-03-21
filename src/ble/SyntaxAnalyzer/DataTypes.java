package ble.SyntaxAnalyzer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DataTypes<T> {
     public static Map<String, Data> vars = new HashMap<String, Data>();     
    
    public static void storeVar(String toCheck) throws IOException
    {
        String varName, value;
        String letVar = "\\s*let\\s*([a-z|A-Z]+\\w*)\\s*[\r\n]";
        String letVarVal = "\\s*let\\s*([a-z|A-Z]+\\w*)\\s*=\\s*(\"?([^\"]*)\"|-?\\d+(\\.?\\d+|))+\\s*[\r\n]";
        String varVal = "\\s*([a-z|A-Z]+\\w*)\\s*=\\s*(\"?([^\"]*\")|([+-]?([0-9]*[.])?[0-9]+))\\s*[\r\n]";
        String letVarVar = "\\s*let\\s*([a-z|A-Z]+\\w*)\\s*=\\s*([a-z|A-Z]+\\w*)\\s*[\r\n]";
        String varVar = "\\s*([a-z|A-Z]+\\w*)\\s*=\\s*([a-z|A-Z]+\\w*)\\s*[\r\n]";
        System.out.println("\n***statement:\n" + toCheck);
        
        checkIfNewScope(toCheck);
        if(toCheck.matches(letVar)){
            varDec(toCheck);
        }else if(toCheck.matches(letVarVal)){
            varDec(toCheck);
        }else if(toCheck.matches(letVarVar)){
            varDec(toCheck);
        }else if(toCheck.matches(varVal)){
            varName = toCheck.replaceAll(varVal, "$1"); 
            value = toCheck.replaceAll(varVal, "$2");
            if(doesVarExist(varName)){
                saveVar(varName, value, false);
            }else{
                dispErrorVariable("Variable must be declared first.", varName);
            }
        }else if(toCheck.matches(varVar)){
            value = toCheck.replaceAll(varVar, "$2");
            varName = toCheck.replaceAll(varVar, "$1");

            if(doesVarExist(varName) && doesVarExist(value)){
                saveVar(varName, value, false);
            }else{
                dispErrorVariable("Unable to find variables.", varName + "or" + value);
            }
        }else{
            System.out.println(toCheck + "**does not match syntax");
        }

        //toCheck.matches(varVar)
            
    } 
    
    /***************************************************************************
     * getValue
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

    /**
     *
     * @param varName
     * @return
     */
    public T getValueGeneric(String varName)
    {
        return (T) vars.get(varName).getValue(); 
    }
       
    /***************************************************************************
     * Scope
    ***************************************************************************/
    public static void checkIfNewScope(String exp){   
       //String newScopeExp = "\t.+\n";
      
           int count = exp.length() - exp.replace("\t", "").length();
           
           if(Data.currScope < count ){
               setNewScope(); 
           }else if(Data.currScope > count){
               endScope(); 
           }
           
       

    }
    public static void setNewScope() 
    {
       Data.currScope++;
    }
    public static void endScope()
    {
        Data.currScope--;
        deleteVarInScope();
    }
    public static boolean doesVarExist(String varName)
    {
        if(vars.containsKey(varName)){
            return true;
        }else{
            return false;
        }
            
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
    
    
    /* stuff */
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

       
        if(!doesVarExist(varName)){
            saveVar(varName, value, true);
        }else{
            dispErrorVariable("Variable has already been declared", varName);
        }
        
    }
    
    private static void saveVar(String name, String val, boolean NewDec)
    {
        val = val.replaceAll("\n", "");
        String intExp = "(?<!\\.)\\b[0-9]+\\b(?!\\.)";
        String stringExp = "\"([^\"]*)\"";
        String floatExp = "\\d+\\.\\d+";
        String varExp = "\\s*([a-z|A-Z]+\\w*)\\s*";
        
        if(val.matches(intExp)){
                int res = Integer.parseInt(val);
                Data<Integer> valu = new Data<>(NewDec);
                valu.setValue(res);
                vars.put(name, valu);

            }else if(val.matches(stringExp)){

                val = val.replaceAll(stringExp, "$1");
                Data<String> valu = new Data<>(NewDec);
               // valu.setScope(false); // false if non static
                valu.setValue(val);
                vars.put(name, valu);

            }else if(val.matches(floatExp)){

                float res = Float.parseFloat(val);

                Data<Float> valu = new Data<>(NewDec);
                valu.setValue(new Float(res).floatValue());
              //  valu.setScope(false); // false if non static
                vars.put(name, valu);
            }else if(val.matches(varExp)){
               
                if(doesVarExist(val)){
                    vars.put(name, vars.get(val));
                }else{
                    //error message (var does not exist)
                    dispErrorVariable("Variable " + val + " does not exist", name); 
                    // THROW ERROR
                }
            
            }else{
                
                dispErrorVariable("Unknown Datatype? ", name); 
            }
            
     
            
        if(vars.containsKey(name)){
            System.out.println(name+" successfully stored?\n"+vars.containsKey(name));
            System.out.println("data type:\n" + vars.get(name).getValue().getClass());
            System.out.println("value of "+name+":\n"+vars.get(name).getValue());
        }
           
         
    }
        
    
    
    private static void deleteVarInScope()
    {
        
        Set x = vars.keySet();
       
        List<String> deleteKeys = new ArrayList<String>();
        
       
        for (Object key : x) {
            String keyS = (String)key; 
            if(vars.get(keyS).getScope() > Data.currScope){
               //vars.remove(keyS); --> error 
               deleteKeys.add(keyS);
            }
        }
        System.out.println("out of scope: " + deleteKeys);
        for(String key: deleteKeys){
            vars.remove(key);
        } 
    }
    private static void dispErrorVariable(String err, String varName)
    {   
        System.out.println("Unable to declare variable: " + varName); 
        System.out.println(err);
    
    }

}