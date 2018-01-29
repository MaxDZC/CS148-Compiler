package testing;

import java.util.Scanner;

import regex.MDAS;

public class MDASTest {

	private static Scanner keyboard;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		keyboard = new Scanner(System.in);
		
		System.out.println("Write the expression you want to solve");
		String expression = keyboard.nextLine();
		
		System.out.println(MDAS.evaluateStatement(expression, 0));
		
		keyboard.close();
	}

}
