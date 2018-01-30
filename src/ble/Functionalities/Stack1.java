/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ble.Functionalities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Stack;

public class Stack1 {

	/**
	 * @param args
	 */
	static String pat_stack = "let\\s+\\w+\\s+(\\=\\s+stack\\(([1-9][0-9]*|[a-zA-Z][\\d\\w\\_]*)?\\))|" +
			"\\s*stack\\s*\\(\\w[\\w\\d\\_]*\\s*\\)\\s*|" +
			"\\s*pop\\s*\\(\\w[\\w\\d\\_]*\\s*\\)\\s*|" +
			"\\s*size\\s*\\(\\w[\\w\\d\\_]*\\s*\\)\\s*|" +
			"\\s*push\\s*\\(\\w[\\w\\d\\_]*\\s*\\,\\s*\\w[\\w\\d\\_]*\\s*\\)\\s*";
	
	String [] lineSt = {"let st = stack()","push(st,10)", "push(st,8)", "push(st,1)", "pop(st)", "size(st)"};

	static Pattern p = Pattern.compile(",\\d+");
	
	public static void declareStack(Stack<Integer> st, String lineSt){
		//for(int i = 0; i < lineSt.length; i++){
			if(lineSt.matches(pat_stack)){
				if(lineSt.contains("stack()") == true){
					st = stack(st);
				}else if(lineSt.contains("push") == true){
					System.out.println("Push Stack:");
					push(st, lineSt);
				}else if(lineSt.contains("pop") == true){
					System.out.println("Pop Stack:");
					pop(st);
					System.out.println(st);	
				}else if(lineSt.contains("size") == true){
					System.out.println("Size Stack:");
					System.out.println(st.size());
				}
			}
		//}
	}
	public static Stack<Integer> stack(Stack<Integer> st){
		System.out.println("Initialize Stack");
		st = new Stack<Integer>();
		return st;
	}
	
	public static void push(Stack<Integer> st, String line){
		String output = line.replaceAll("\\s+",""); 
		Matcher m = p.matcher(output);
		int start, end;
		Integer elem;
		if(m.find()){
			start = m.start()+1;
			end = m.end();
	 		elem = Integer.parseInt(output.substring(start,end));
	 		st.push(elem);
	 		System.out.println(st);
		}
	}
	
	public static void pop(Stack<Integer> st){
		if(st.size() > 0){
			st.pop();
		}else{
			System.out.println("Stack is Empty!");
		}
	}

}
