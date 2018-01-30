/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ble.Functionalities;

public class Condi {
	String pat_cond = "if(\\s*\\(\\s*(\\-?\\d+|\\-?\\d+\\.\\d+|[A-z][\\w\\_]*)\\s*)*"+
			"(\\s*(<|>|==|<=|>=|!=|!|&&|\\|\\||\\^)\\s*(\\-?\\d+|\\-?\\d+\\.\\d+|[A-z][\\w\\_]*)\\s*)*\\)\\s*"+
			"\\r\\n(\\t.+(\\r\\n)*)*"+
			"(else if\\s*\\(\\s*(\\-?\\d+|\\-?\\d+\\.\\d+|[A-z][\\w\\_]*)\\s*"+
			"(\\s*(<|>|==|<=|>=|!=|!|&&|\\|\\||\\^)\\s*(\\-?\\d+|\\-?\\d+\\.\\d+|[A-z][\\w\\_]*))*\\s*\\)\\s*"
			+ "\\r\\n(\\t.+(\\r\\n)*)+)*"+
			"(else\\s*\\(\\s*(\\-?\\d+|\\-?\\d+\\.\\d+|[A-z][\\w\\_]*)\\s*"+
			"(\\s*(<|>|==|<=|>=|!=|!|&&|\\|\\||\\^)\\s*(\\-?\\d+|\\-?\\d+\\.\\d+|[A-z][\\w\\_]*))*\\s*\\)\\s*"
			+ "\\r\\n(\\t.+(\\r\\n)*)+)?";
	
	
	public String evalStatement (String sampc) {
		
		String ret ="";
		
		if (sampc.matches(pat_cond)) {
			int i,val,ifval;
			val = 0;
			ifval = 0;
			for(i = 0; i < sampc.length(); i++) {
				if (sampc.substring(i,i+2).equals("if")) {
					if (i < 4 || !sampc.substring(i-5,i-1).equals("else")) {
						i += 2;
						while (sampc.charAt(i) !='(') {
							i++;
						}
						i++;
						while (sampc.charAt(i) == ' ') {
							i++;
						}
						System.out.println (sampc.charAt(i));
						val = Character.getNumericValue(sampc.charAt(i));
					}
				} 
				if (val == 1) {
					ret = "will go inside if statement";
					break;
				}
				while (val != 1 && i+10 < sampc.length()) {
					if (sampc.substring(i,i+7).equals("else if")){
						i += 7;
						while (sampc.charAt(i) !='(') {
							i++;
						}
						i++;
						while (sampc.charAt(i) == ' ') {
							i++;
						}
						val = Character.getNumericValue(sampc.charAt(i));
						if (val == 1) {
							ret = "will go inside else if statement";
							break;
						}
					}else break;
				}
						
				if (sampc.substring(i,i+4).equals("else")) {
					ret = "will go inside else statement";
					break;
				}
				if (val == 1) {
					break;
				}
			}
		}else {
			ret = "Incorrect Syntax of conditional statement";
		}
		return ret;
	}
}

