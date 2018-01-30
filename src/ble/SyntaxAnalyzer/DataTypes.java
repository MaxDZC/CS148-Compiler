package ble.SyntaxAnalyzer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DataTypes {
    public static Map<String, Data> allVars = new HashMap<String, Data>();

        
	public static void test(String[] args) throws IOException{
    	// int, float, char, string, bool
    	
    	
    	//let varName
    	String test1 = "let y \n";
    	String test2 = "let 2yella1s2 \n";
    	
    	String letVar = "\\s*let\\s*([a-z|A-Z]+\\w*)\\s*[\r\n]";
    	
        checkSyntax(test1);
        checkSyntax(test2);
        
    	// let y = 10
    	
    	String test3 = "let val2  = 2.9 \n";
    	String test4 = "let valstring  = \"string\" \n";
    	String test5 = "let errorSt = \n";
    	
    //	String letVarVal = "\\s*let\\s*([a-z|A-Z]+\\w*)\\s*=\\s*\"?(\\w+)+\"?\\s*[\r\n]";
    	
    	checkSyntax(test3);
    	checkSyntax(test4);
    	checkSyntax(test5);
    	
        test5 = "y = \" \"\n";
        checkSyntax(test5);
        
        test5 = "y = 0.9 \n";
        checkSyntax(test5);
        
        Float hhh= (Float) allVars.get("y").getValue(); 
        System.out.println(hhh  + 2);
        
    }
        
    public static String getSyntax() {
        String syntax;
        
        syntax = "(\\s*let\\s*([a-z|A-Z]+\\w*)\\s*[\r\n]";
        syntax += "|\\s*let\\s*([a-z|A-Z]+\\w*)\\s*=\\s*(\"?([^\"]*)\"|-?\\d+(\\.?\\d+|))+\\s*[\r\n]";
        syntax += "|\\s*([a-z|A-Z]+\\w*)\\s*=\\s*(\"?([^\"]*\")|([+-]?([0-9]*[.])?[0-9]+))\\s*[\r\n])";
    
        return syntax;
    }
	public static void checkSyntax(String toCheck) throws IOException
	{
		String varName, value;
		String letVar = "\\s*let\\s*([a-z|A-Z]+\\w*)\\s*[\r\n]";
		String letVarVal = "\\s*let\\s*([a-z|A-Z]+\\w*)\\s*=\\s*(\"?([^\"]*)\"|-?\\d+(\\.?\\d+|))+\\s*[\r\n]";
	//	String varVal = "\\s*([a-z|A-Z]+\\w*)\\s*=\\s*(\"?([^\"]*)\"|-?\\d+(\\.?\\d+|))+\\s*[\r\n]";
		String varVal = "\\s*([a-z|A-Z]+\\w*)\\s*=\\s*(\"?([^\"]*\")|([+-]?([0-9]*[.])?[0-9]+))\\s*[\r\n]";

                //Creating a file
                File f = new File("C://Desktop//values.txt");
                FileWriter fr = null;
                
                System.out.println("\n***\n" + toCheck);
                if(toCheck.matches(letVar)){
                    // let varName
                    varName = toCheck.replaceAll(letVar, "$1");
                    
                    storeVars(varName, "\"\"");
                }else if(toCheck.matches(letVarVal)){
                    // let varName = value
                    varName = toCheck.replaceAll(letVarVal, "$1");
                    value = toCheck.replaceAll(letVarVal, "$2");
                    /*varName = toCheck.replaceAll("(=\\s*(\"?([^\"]*)\"|-?\\d+(\\.?\\d+|))+\\s*[\r\n])$", "");
                    varName = varName.replaceAll("\\s*let\\s*", "");
                    value = toCheck.replaceAll("\\s*let\\s*([a-z|A-Z]+\\w*)\\s*=\\s*", "");
                    value = value.replaceAll("\\s*[\r\n]$", ""); */
                    System.out.println("value " + value);
                    
                    //Writing to file
                    fr = new FileWriter(f);
                    fr.write(allVars.get(letVarVal).getValue().getClass().getName());
                    fr.write((String) allVars.get(letVarVal).getValue());
                   
                    storeVars(varName, value);
                }else if(toCheck.matches(varVal)){
                    // y = value here ;
                    varName = toCheck.replaceAll(varVal, "$1");
                    //value = toCheck.replaceAll("\\s*([a-z|A-Z]+\\w*)\\s*=\\s*", "");
                    value = toCheck.replaceAll(varVal, "$2");
                    
                    if(allVars.containsKey(varName)){
                        
                        fr = new FileWriter(f);
                        fr.write(allVars.get(varName).getValue().getClass().getName());
                        fr.write((String) allVars.get(varName).getValue());
                        
                        storeVars(varName, value);
                    }else{
                        System.out.println("var " + varName + " not declared");
                    }
                }else{
                    System.out.println(toCheck + "**does not match syntax");
                }
                fr.close();
        }               
        
	public static void storeVars(String name, String val)
	{
		val = val.replaceAll("\n", "");
		String intExp = "(?<!\\.)\\b[0-9]+\\b(?!\\.)";
		
		String stringExp = "\"([^\"]*)\"";
		String floatExp = "\\d+\\.\\d+";
		//allVars.put(name, val);
		
		if(val.matches(intExp)){
			
			int res = Integer.parseInt(val);
			Data<Integer> valu = new Data<Integer>();
			valu.setValue(res);
			
			allVars.put(name, valu);
			
		}else if(val.matches(stringExp)){
			
			val = val.replaceAll(stringExp, "$1");
			Data<String> valu = new Data<String>();
			valu.setValue(val);
			allVars.put(name, valu);
			
		}else if(val.matches(floatExp)){
			//val = val.replaceAll(floatExp, "$1");
			
			float res = Float.parseFloat(val);
			Data<Float> valu = new Data<Float>();
			valu.setValue(res);
			allVars.put(name, valu);
			
			
		}else{
			System.out.println("did not match anything");
		}
		System.out.println(allVars.get(name).getValue().getClass().getName());
		System.out.println(name+" successfully stored? "+allVars.containsKey(name));
		System.out.println("value of "+name+": "+allVars.get(name).getValue()+"\n");
	}
	
	
	
}

