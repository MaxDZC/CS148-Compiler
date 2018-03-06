/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ble.Functionalities;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ble.entities.Entity;
import ble.objects.ArrayBle;

/**
 *
 * @author Max
 */
public class ArraysFunct {
	private static String f;									//holder for the function call
    static Pattern p;
    static Matcher m;
    
    public static void arrayStuff(String line)
    {
        String pat = "\\s[a-zA-z]*(\\s*)?=";
        p = Pattern.compile(pat);
        m = p.matcher(line);
        if(m.find()){
            String patt = "[a-zA-z]+";
            p = Pattern.compile(patt);
            m = p.matcher(m.group());
            if(m.find()){
                System.out.println(m.group());
                
            }
            //System.out.println(m.find()?m.group():"None");
        }
    }
    
    public boolean getFunction(String text) {
		String pattern = "\\w*\\(.*\\)";			// regex to be used in order to get the function call
		
		p = Pattern.compile(pattern);
		m = p.matcher(text);
		if(m.find()) {
			this.f = m.group();						// saves the calling function to the class variable "f";
			return true;
		}
		return false;
	}
	
	/**
	 * finds any corresponding array function that source file has specify
	 * @param memory  - holds the value of the objects
	 * @return any specified errors or value
	 */
	
    public static Object findFunction(Map<String,?> memory) {	
		Object returnValue = null;					// object class that is used to return to the compiler
		String pattern = "\\w*";					// regex to be used in order to get the desired function
		String function;
		String[] params;							// parameters of the called function		
		
		p = Pattern.compile(pattern);
		m = p.matcher(f);
		m.find();
		function = m.group();
		//search for the desired function to be conducted
		params = getParameters();
		switch(function) {
			case "search":					
				// parameters (1) ArrayName (2) Value to be searched
				if(params.length == 2) {
					returnValue = (memory.containsKey(params[0]))? search(params,memory) : (String) "Array not found";
				} else {
					returnValue = (String) "Invalid Parameters";
				}
				
				break;
			
			case "size": 			
				// parameters (1) ArrayNAme
				if(params.length == 1) {
					returnValue = (memory.containsKey(params[0])) ? size(params,memory) : (String) "Array not found";
				} else {
					returnValue = (String) "Invalid Parameters";
				}
				
				break;
			case "delete":			
				// parameters (1) ArrayName (2) Value
				if(params.length == 2) {
					returnValue = (memory.containsKey(params[0])) ? delete(params,memory) : (String) "Array not found";
				} else {
					returnValue = (String) "Invalid Parameters";
				}
				
				break;
			case "append":			
				// parameters (1) ArrayName (2) Value
				if(params.length == 2) {
					returnValue = (memory.containsKey(params[0])) ? append(params,memory) : (String) "Array not found";
				} else {
					returnValue = (String) "Invalid Parameters";
				}
				
				break;
			case "insert": 		
				// parameters (1) ArrayName (2) Value (3)[Optional] Position
				if(params.length >= 2 && params.length <= 3) {
					returnValue = (memory.containsKey(params[0])) ? insert(params,memory) : (String) "Array not found";
				} else {
					returnValue = (String) "Invalid Parameters";
				}
				
				break;
			case "retrieve": 		
				// parameters (1) ArrayName (2)[Optional] Position
				if(params.length >= 1 && params.length <= 2) {
					returnValue = (memory.containsKey(params[0])) ? retrieve(params,memory) : (String) "Array not found";
				} else {
					returnValue = (String) "Invalid Parameters";
				}
				
				break;
			case "stringToArray":	break;
			default:  
		}
		
		return returnValue;
	}
	
	/**
	 * gets the parameters in the calling function
	 * @return parameters of the function
	 */
	private static String[] getParameters() {
		String pattern = "\\(.*\\)";

		String paramGroup;
		
		p = Pattern.compile(pattern);
		m = p.matcher(f.toString());
		m.find();
		paramGroup = m.group();
		
		paramGroup = paramGroup.replaceAll("\\(|\\)", "");	
		
		return paramGroup.split(",");
	}
	
	/**
	 * finds the index of the given value
	 * @param params -> parameters of search function (1) - ArrayName (2) - Value
	 * @param memory -> memory of the compiler
	 * @return the index of the first occurrence of the value else returns -1
	 */
	private static int search(String[] params, Map<String,?> memory) {
		int i;
		int returnValue = -1;
		
		ArrayBle array = (ArrayBle) memory.get(params[0]);
		System.out.println("Searching for "+params[1]);
		for(i = 0; i < array.size()-1 && !array.get(i).equals(params[1]) ; i++) {}
		
		if( i < array.size()-1) {
			returnValue = i;
		}
		
		return returnValue;
	}
	
	
	/**
	 * gets the size of the corresponding array
	 * @param params -> parameters of size function (1) - ArrayName
	 * @param memory -> memory of compiler
	 * @return the size of the array
	 */
	private static Integer size(String[] params, Map<String,?> memory) {
		ArrayBle array = (ArrayBle) memory.get(params[0]);

		return array.size();
	}
	
	/**
	 * deletes the object with the specific value
	 * @param params -> parameters of the delete function (1) - ArrayName (2) - Value
	 * @param memory -> memory of compiler
	 * @return the class of the deleted object
	 */
	private static Object delete(String[] params, Map<String,?> memory) {
		Object returnValue;
		
		ArrayBle array = (ArrayBle) memory.get(params[0]);
		
		if(array.isEmpty()) {
			returnValue = (String) "ERROR: -1 is not a valid index";
		} else {
			returnValue = array.delete(params[1]);
		}
		
		return returnValue;
	}
	
	/**
	 * inserts at the last index of the array
	 * @param params
	 * @param memory
	 * @return
	 */
	private static String append(String[] params, Map<String, ?> memory) {
		ArrayBle array = (ArrayBle) memory.get(params[0]);
		
		// TODO: check if the inserted value is a string, character or number
		array.insert(params[1].toString(), params[1]);
		
		return "append successful";
	}
	
	/**
	 * insert at a given position or insert last
	 * @param params
	 * @param memory
	 * @return
	 */
	private static String insert(String[] params, Map<String, ?> memory) {
		String message = null;
		
		ArrayBle array =  (ArrayBle) memory.get(params[0]);
		
		// TODO: check if the inserted value is a string, character, or number
		if(params.length == 2) {
			array.insert(params[1].toString(), params[1]);
			message = "insert successful";
		} else if (params.length == 3) {
			// TODO: Check if the third parameter is an integer
			//if() {
				array.insert(params[1].toString(), params[1], Integer.parseInt(params[2]));
				message = "insert successful at "+params[2]+" position";
			//} else {
			//	message = "3rd parameter is not a valid integer";
			//}
		}		
		return message;
	}
	
	/**
	 * retrieve the value of the array in the position or first index
	 * @param params
	 * @param memory
	 * @return
	 */
	private static Object retrieve(String[] params, Map<String, ?> memory) {
		
		Object ret = null;
		ArrayBle array = (ArrayBle) memory.get(params[0]);
		
		if(params.length == 1) {
			System.out.println("retrieving first position");
			ret = array.get(0);
		} else if(params.length == 2) {
			// TODO: Check if the second parameter is an integer
			System.out.println("retrieveing at "+params[1]+" position");
			ret = array.get(Integer.parseInt(params[1]));
		}
		
		return ret;
	}
}
