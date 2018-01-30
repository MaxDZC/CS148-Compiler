package ble.Functionalities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Max
 */
public class ValidationFunct {
   	private static String regex = "\\s*validate\\s*[(]\\s*((\\d+(\\.\\d+)?|'[^\\\\]')(\\s*[+]\\s*(\\d+(\\.\\d+)?|'[^\\\\]'))*\\s*[+]\\s*)?(\"(\\\\.|[^\\\\\"]|)+\"|[a-zA-Z]\\w*)(\\s*[+]\\s*(\"(\\\\.|[^\\\\\"]|)+\"|'[^\\\\]'|\\d+(\\.\\d+)?|[a-zA-Z]\\w*))*\\s*,\\s*((\\d+(\\.\\d+)?|'[^\\\\]')(\\s*[+]\\s*(\\d+(\\.\\d+)?|'[^\\\\]'))*\\s*[+]\\s*)?(\"(\\\\.|[^\\\\\"]|)+\"|[a-zA-Z]\\w*)(\\s*[+]\\s*(\"(\\\\.|[^\\\\\"]|)+\"|'[^\\\\]'|\\d+(\\.\\d+)?|[a-zA-Z]\\w*))*\\s*[)]\\s*";
	
	public static boolean checkPattern(String expression) throws NullPointerException{
		boolean retVal = expression.matches(ValidationFunct.regex);
		
		return retVal;
	}
	
	public static void run(String s){
		Pattern r = Pattern.compile("\\(([^)]+)\\)"); // Create a Pattern object to compile the regular expression
		String err = "";
		
		if(checkPattern(s)){
			Matcher m = r.matcher(s);
			
			if (m.find()) {
				String c = m.group(1);
				// For "debugging" purpose: show the caught group of text
//	        	System.out.println(c + "\n\n"); 
	        	
	        	String[] params = c.split("\\s*,\\s*"); //separate the parameters
	        	String regexChoice = "";
	        	
	        	for(int i = 0; i < 2; i++){
	        		if(params[i].matches("\\s*[a-zA-Z]\\w*\\s*")){ 
	        			// if parameter is a variable disregard for now
	        			err+="DEV: disregard variable in parameter "+ (i+1) +" for now\n";
	        			params[i] = "";
	        		} else {
	        			//if parameter == <anything> + <anything>
	        			if(params[i].matches("(\"(\\\\.|[^\\\\\"]|)+\"|'[^\\\\]'|\\d+(\\.\\d+)?|[a-zA-Z]\\w*)(\\s*[+]\\s*(\"(\\\\.|[^\\\\\"]|)+\"|'[^\\\\]'|\\d+(\\.\\d+)?|[a-zA-Z]\\w*))+")){
	        				//separate the operandszx
	        				String[] eval = params[i].trim().split("\\s*[+]\\s*");
	        				params[i] = "";
	        				for(int x = 0; x < eval.length; x++){
	        					if(eval[x].matches("\\s*[a-zA-Z]\\w*\\s*")){
	        						//if variable disregard for now
	        						err+="DEV: disregarded variable in paramater "+ (i+1) +" for now\n";
	        					}else{
	        						//remove leading and trailing ' and " of chars and strings
	        						eval[x]= eval[x].trim().replaceAll("(^['\"]|['\"]$)","");
	        						params[i] += eval[x];
	        					}
	        				}
	        			}else {
	        				//remove leading and trailing ' and " of chars and strings
	        				params[i] = params[i].trim().replaceAll("(^['\"]|['\"]$)","");
	        			}
	        		}
	        	}
	        	
        		switch(params[0]){ //assign regex according to choice
	        		case "email":
	        			regexChoice = "(\\w|[_.!#$%&*+-~/,\\(\\)'])+\\@\\w+.[A-z]{2,4}";
	        		break;
	        		case "password":
	        			regexChoice = "\\w+";
	        		break;
	        		case "number": 
	        			regexChoice = "\\d+(\\.\\d+)?";
	        		break;
	        		default:
	        			err += "ERR: Regex Choice does not exist.";
	        		break;
	        	}
        		
	        	if(regexChoice != "" && params[1] != ""){
	        		System.err.println(params[1].matches(regexChoice));
	        	}else{
	        		
	        	}
	        	System.err.println(err);
			}
		} else {
			System.err.println("DEV: Compile failed.");
		}	
	}
}
