package ble.Functionalities;
import ble.SyntaxAnalyzer.DataTypes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Display {
        public static List<String> dataToDisplay = new ArrayList<String>();
	private static final String regex = "\\s*\\t*display[(]\\s*((\".+\"|\'.+\'|\\d+(\\.\\d+)?|[a-zA-Z]\\w*)(\\s*[+]\\s*(\".+\"|\'.+\'|\\d+(\\.\\d+)?|[a-zA-Z]\\w*))*)?\\s*[)]\\s*";
	
	public static boolean checkPattern(String expression) throws NullPointerException{
		boolean retVal = expression.matches(Display.regex);
		return retVal;
	}
	public String getDisplayedContents(){
            
             for(String x: dataToDisplay){
                    System.out.println(x);
                } 
             String listString = String.join("<br>", dataToDisplay);
             dataToDisplay = new ArrayList<String>();
             return listString; 
        } 
               
	public static void run(String s){
        List<String> contents = new ArrayList<String>(); 
		
        Pattern r = Pattern.compile(Display.regex); // Create a Pattern object to compile the regular expression
        
        // Proceed only when the syntax is valid
		if(checkPattern(s) == true){
	        // Now create matcher object to find the captured groups of text
	        Matcher m = r.matcher(s);
               
	        // If there are text captured from the syntax,
	        if (m.find()) {	  
	        	String c = m.group(1);
	        	//System.out.println(c + "\n\n"); // For "debugging" purpose: show the caught group of text
	        	DataTypes dTypes = new DataTypes(); 
	        	// Parse the dataToDisplay of the function's parameter and put its dataToDisplay into an ArrayList
	        	String[] temp = c.split("\\+\\s*");
                        for(String x : temp){
                            contents.add(x);
                        }
	        	System.out.println("****IN DISPLAY JAVA");
                        String value = ""; 
	        	for (int i=0; i< contents.size(); i++){
	        		if(contents.get(i).matches("\\s*[a-zA-Z]\\w*\\s*")){ 
                                    System.out.println(contents.get(i)); 
                                        if(dTypes.doesVarExist((String)contents.get(i))){ // if variable has already been declared
                                            String typeOfVar = dTypes.vars.get(contents.get(i)).getValue().getClass().getName(); 
                                            System.out.println("type of var" + typeOfVar);
                                            if(typeOfVar == "java.lang.String"){
                                                value =  dTypes.getValueString(contents.get(i));
                                            }else if(typeOfVar == "java.lang.Integer"){
                                                value =  Integer.toString(dTypes.getValueInt(contents.get(i)));
                                            }else if(typeOfVar == "java.lang.Float"){
                                                 value =  Float.toString(dTypes.getValueFloat(contents.get(i)));
                                            }
                                            dataToDisplay.add(value);
                                        }else{ 
                                            System.out.println("Variable does not exist");
                                           // contents.remove(i);
                                          //  i--; 
                                        }
	        		} else if(contents.get(i).matches("\\s*(\".+\"|\'.+\')\\s*")){ // If the caught group is a string literal,
	        			dataToDisplay.add(contents.get(i).trim().replaceAll("\"|\'", "")); // Remove leading and trailing whitespaces, and remove quotation marks
	        		}
	        	}

	        	// Print the dataToDisplay in the console, in order
                        System.out.println("print function" );
	        	for (int i=0; i<dataToDisplay.size(); i++){
	        		System.out.print(dataToDisplay.get(i) + i + "\n");
	        	} 
	        	
	        } else {
	            System.err.println("DEV: There was no match.");
	        }
		} else {
			System.err.println("DEV: Compile failed.");
		}		
	}

}
