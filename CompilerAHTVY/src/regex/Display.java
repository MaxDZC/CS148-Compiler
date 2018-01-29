/**
 * 
 */
package regex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Display {
	private static final String regex = "\\s*display[(]\\s*((\".+\"|\'.+\'|\\d+(\\.\\d+)?|[a-zA-Z]\\w*)(\\s*[+]\\s*(\".+\"|\'.+\'|\\d+(\\.\\d+)?|[a-zA-Z]\\w*))*)?\\s*[)]\\s*";
	
	public static boolean checkPattern(String expression) throws NullPointerException{
		boolean retVal = expression.matches(Display.regex);
		return retVal;
	}
	
	public void run(String s){
		List<String> contents = null;
        Pattern r = Pattern.compile(Display.regex); // Create a Pattern object to compile the regular expression
        
        // Proceed only when the syntax is valid
		if(checkPattern(s) == true){
	        // Now create matcher object to find the captured groups of text
	        Matcher m = r.matcher(s);
	        
	        // If there are text captured from the syntax,
	        if (m.find()) {	        	
	        	String c = m.group(1);
//	        	System.out.println(c + "\n\n"); // For "debugging" purpose: show the caught group of text
	        	
	        	// Parse the contents of the function's parameter and put its contents into an ArrayList
	        	String[] temp = c.split("\\+\\s*");
	        	contents = new ArrayList<>(Arrays.asList(temp));
	        	
	        	for (int i=0; i<contents.size(); i++){
		        	// TODO: Resolve variable contents, which is dependent on Lee's group
	        		if(contents.get(i).matches("\\s*[a-zA-Z]\\w*\\s*")){ // In order to compile this right now, I'll have to ignore variables
	        			contents.remove(i);
	        			i--;
	        		} else if(contents.get(i).matches("\\s*(\".+\"|\'.+\')\\s*")){ // If the caught group is a string literal,
	        			contents.set(i, contents.get(i).trim().replaceAll("\"|\'", "")); // Remove leading and trailing whitespaces, and remove quotation marks
	        		}
	        	}

	        	// Print the contents in the console, in order
	        	for (int i=0; i<contents.size(); i++){
	        		System.out.print(contents.get(i));
	        	} 
	        	
	        } else {
	            System.err.println("DEV: There was no match.");
	        }
		} else {
			System.err.println("DEV: Compile failed.");
		}		
	}

}
