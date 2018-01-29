package timelibrary;
import java.util.*;
public class DataTypes{
    public static Map<String, Data> allVars = new HashMap<String, Data>();

	public static void main(String[] args){
    	// int, float, char, string, bool
    	
    	
    	//let varName
    	String test1 = "let yella1s2 \n";
    	String test2 = "let 2yella1s2 \n";
    	
    	String letVar = "\\s*let\\s*([a-z|A-Z]+\\w*)\\s*[\r\n]";
    	
        checkSyntax(test1);
        checkSyntax(test2);
        
    	// let y = 10
    	
    	String test3 = "let val2  = 2 \n";
    	String test4 = "let valstring  = \"string is test. 323\n\" \n";
    	String test5 = "let errorSt = \n";
    	
    //	String letVarVal = "\\s*let\\s*([a-z|A-Z]+\\w*)\\s*=\\s*\"?(\\w+)+\"?\\s*[\r\n]";
    	
    	checkSyntax(test3);
    	checkSyntax(test4);
    	checkSyntax(test5);
    	
        test5 = "y = 5\n";
        checkSyntax(test5);
    	
    	// y = 11
    	//let stackName = Stack
        
    	/* 
    	 *  * * * * * * 
    	 *  * tasks: * *
    	 * 	* * * check syntax * * * 
    	 *  * * * store variables and values * * * 
    	 *  
    	 * 2nd - store var name if preceded by "let"
    	 * 3rd - if not, check list of declared variables; throw errors and shit
    	 * 4th - if "=" is found, check type
    	 *  * * * if with " 			-> string or char
    	 *  * * * if \d with . 			-> float
    	 *  * * * if \d without . 		-> int
    	 *  * * * if true or false no "	-> bool 
    	 */
        
    }
	public static void checkSyntax(String toCheck)
	{
		String varName, value;
		String letVar = "\\s*let\\s*([a-z|A-Z]+\\w*)\\s*[\r\n]";
		String letVarVal = "\\s*let\\s*([a-z|A-Z]+\\w*)\\s*=\\s*(\"?([^\"]*)\"|-?\\d+(\\.?\\d+|))+\\s*[\r\n]";
		String varVal = "([a-z|A-Z]+\\w*)\\s*=\\s*(\"?([^\"]*)\"|-?\\d+(\\.?\\d+|))+\\s*[\r\n]";
		if(toCheck.matches(letVar)){
			// let varName 
			varName = toCheck.replaceAll(letVar, "$1");
			System.out.println("varanem" + varName);
			storeVars(varName, "\"\"");
		}else if(toCheck.matches(letVarVal)){
			// let varName = value
			varName = toCheck.replaceAll(letVarVal, "$1");
	    	value = toCheck.replaceAll(letVarVal, "$2");
	    	storeVars(varName, value);
		}else if(toCheck.matches(varVal)){
			// y = value here ; remember: dynamic typing
			System.out.println("test 5 y = val matches!");
		}else{
			System.out.println(toCheck + "**does not match syntax");
		}
	}
	public static void storeVars(String name, String val)
	{
		String intExp = "(?<!\\.)\\b[0-9]+\\b(?!\\.)";
		String stringExp = "\"{1}([^\"]*)\"{1}";
		String floatExp = "\\d+\\.\\d+";
		//allVars.put(name, val);
			System.out.println("Valis" + val);
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
			float res = Float.parseFloat(val);
			Data<Float> valu = new Data<Float>();
			valu.setValue(res);
			allVars.put(name, valu);
		}
		
		System.out.println(name+" successfully stored? "+allVars.containsKey(name)+"\n");
		System.out.println("value of "+name+": "+allVars.get(name).getValue()+"\n");

	}
	
	
	
}

