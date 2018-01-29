package testing;

import java.util.Scanner;

import regex.Display;

public class DisplayTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Display d = new Display();
		Scanner scan = new Scanner(System.in);

		System.out.print("> ");
		String temp = scan.nextLine();
		d.run(temp);
		
		scan.close();
	}

}
