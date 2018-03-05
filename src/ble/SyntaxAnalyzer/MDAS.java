package ble.SyntaxAnalyzer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Max
 */
public class MDAS {
    private final static String regex = "\\s*((\\-|\\+|\\s*)\\s*(\\d+(\\.\\d+)?|[a-zA-Z]\\w*)(\\s*([+-[*]/%]|\\*\\*)\\s*(\\s+\\-|\\s+\\+|\\s*)\\s*(\\d+(\\.\\d+)?|[a-zA-Z]\\w*))+)*\\s*";
    
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
			
			if(tempExp.matches("\\s*(\\-|\\+|\\s*)\\s*(\\d+(\\.\\d+)?|[a-zA-Z]\\w*)\\s*") || tempExp.matches(MDAS.regex)){
				expression = expression.replace(matcher.group(0), tempExp);
				matcher = pattern.matcher(expression);
			}else{
				retVal = false;
				break;
			}
		}
	}
	if(!matcher.find()){
		retVal = expression.matches(MDAS.regex);
	}
		
	return retVal;
        }
}
