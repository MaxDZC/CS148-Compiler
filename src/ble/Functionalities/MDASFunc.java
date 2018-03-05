package ble.Functionalities;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Hourani
 *
 */
public class MDASFunc {
	private final static String regex = "\\s*((\\-|\\+|\\s*)\\s*(\\d+(\\.\\d+)?|[a-zA-Z]\\w*)(\\s*([+-[*]/%]|\\*\\*)\\s*(\\s+\\-|\\s+\\+|\\s*)\\s*(\\d+(\\.\\d+)?|[a-zA-Z]\\w*))+)*\\s*";
	private static ArrayList <String> pemdasPatterns = new ArrayList<String>();
	
	public static boolean checkPattern(String expression) throws NullPointerException{
		boolean retVal = false;
		
		Pattern pattern = Pattern.compile("(\\()([^\\(\\)]*)(\\))");//finds anything inside a parenthesis
		Matcher matcher = pattern.matcher(expression);
		Pattern checker = Pattern.compile("\\(.*\\)\\(.*\\)");
		Matcher tester = checker.matcher(expression);
		
		if(!tester.find()){//languages don't allow two parentheses structures next to each other
			while(matcher.find()){ //If a parentheses structure exists
				String tempExp;
				tempExp = matcher.group(0).replaceAll("\\(", " ");
				tempExp = tempExp.replaceAll("\\)", " ");
			
				if(tempExp.matches("\\s*(\\-|\\+|\\s*)\\s*(\\d+(\\.\\d+)?|[a-zA-Z]\\w*)\\s*") || tempExp.matches(MDASFunc.regex)){
					expression = expression.replace(matcher.group(0), tempExp);
					matcher = pattern.matcher(expression);
				}else{
					retVal = false;
					break;
				}
			}
		}
		if(!matcher.find()){
			retVal = expression.matches(MDASFunc.regex);
		}
		
		return retVal;
	}
	
	public static Double evaluateStatement(String expression, int num){
		expression.replaceAll("\\*\\*", "^");//in order to be able to evaluate the exponent function
		
		//Do NOT edit unless sure
		pemdasPatterns.add("(\\()([^\\(\\)]*)(\\))");//captures contents inside a parenthesis
		pemdasPatterns.add("(-?\\d+\\.?\\d*)\\D*?(\\^)\\D*?(-?\\d+\\.?\\d*)"); //Covers exponents
		pemdasPatterns.add("(-?\\d+\\.?\\d*)\\D*?(\\*|\\/|%)\\D*?(-?\\d+\\.?\\d*)"); //Covers multiplication, division, and modulo
		pemdasPatterns.add("(-?\\d+\\.?\\d*)\\D*?(\\+|-)\\D*?(-?\\d+\\.?\\d*)"); //Covers addition and subtraction
		
		//proceed to evaluating the statement
		Double result = null;
    	String next;
        Pattern filter = Pattern.compile(pemdasPatterns.get(num));
        Matcher calcMatch = filter.matcher(expression);
        
        if(!calcMatch.find()){
        	if(num < 3) return evaluateStatement(expression, num+1);
	        return null;
        }
        if(num == 0){
        	Double pSol = evaluateStatement(calcMatch.group(2), 1);
        	
        	if(pSol !=null){ result = pSol;
        		next = expression.substring(0, calcMatch.start()) + result.toString() + expression.substring(calcMatch.end());
        	}
        	else{
        		next = expression.substring(0, calcMatch.start()) + expression.substring(calcMatch.end());
        	}
        }
        else{
	          switch(calcMatch.group(2)){
	            case "+":
	            	result = Double.parseDouble(calcMatch.group(1)) + Double.parseDouble(calcMatch.group(3));
	              	break;
	              	
	            case "-":
	            	result = Double.parseDouble(calcMatch.group(1)) - Double.parseDouble(calcMatch.group(3));
	              	break;
	              	
	            case "/":
	            	result = Double.parseDouble(calcMatch.group(1)) / Double.parseDouble(calcMatch.group(3));
	              	break;
	              	
	            case "*":
	            	result = Double.parseDouble(calcMatch.group(1)) * Double.parseDouble(calcMatch.group(3));
	              	break;
	              	
	            case "^":
	            	result = Math.pow(Double.parseDouble(calcMatch.group(1)), Double.parseDouble(calcMatch.group(3)));
	            	break;
	            	
	            case "%":
	            	result = Double.parseDouble(calcMatch.group(1)) % Double.parseDouble(calcMatch.group(3));
	            	break;
	          }
	          next = expression.substring(0, calcMatch.start()) + result.toString() + expression.substring(calcMatch.end());
      	}
        
	    if(filter.matcher(next).find()) return evaluateStatement(next, num);
	    if(num < 3){
	        Double nextResult = evaluateStatement(next, num+1);
	        if(nextResult !=null) return nextResult;
	    }
	    return result;
	}
	
	public static Double evalExp(String expression){
		assert expression != null;
		assert MDASFunc.checkPattern(expression) == true;
		
		return MDASFunc.evaluateStatement(expression, 0);
	}
}
