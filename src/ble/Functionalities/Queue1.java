package ble.Functionalities;

import static ble.SyntaxAnalyzer.DeclaringAndAssigning.checkDeclarationStatement;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Max
 */
public class Queue1 {
    	/**
	 * @param args
	 */
	static String pat_queue = "let\\s+\\w+\\s+(\\=\\s+queue\\(([1-9]+[0-9]*|\\w[\\w\\d\\_]*)?\\))|" +
			"\\s*enqueue\\s*\\([A-z][\\w\\_]*\\s*\\,(\\s*[A-z][\\w\\_]*\\s*|\\d+|\\-\\d+)\\)\\s*|" +
			"\\s*dequeue\\s*\\([A-z][\\w\\_]*\\s*\\)\\s*";
	static Pattern p = Pattern.compile(",\\d+");
	
	public static void declareQueue(Queue<Integer> qu, String lineQ){
		//for(int i = 0; i < lineQ.length; i++){
			if(lineQ.matches(pat_queue)){
				if(lineQ.contains("queue()") == true){
					System.out.println("Initialize Queue");
					checkDeclarationStatement(lineQ,"");
					qu = queue(qu);
				}else if(lineQ.contains("enqueue") == true){
					System.out.println("Enqueue Queue");
					enqueue(qu, lineQ);
				}else if(lineQ.contains("dequeue") == true){
					System.out.println("Dequeue Queue");
					dequeue(qu);
				}
			}
		//}
	}
	
	public static Queue<Integer> queue(Queue<Integer> qu){
		qu = new LinkedList<Integer>();
		return qu;
	}
	
	public static void enqueue(Queue<Integer> qu, String lineQ){
		int start, end, val;
		String output = lineQ.replaceAll("\\s+","");
		Matcher m = p.matcher(output);
		if(m.find()){
			start = m.start()+1;
			end = m.end();
	 		val = Integer.parseInt(output.substring(start,end));
	 		qu.add(val);
	 		System.out.println(qu);
		}
	}
	
	public static void dequeue(Queue<Integer> qu){
		if(!qu.isEmpty()){
			qu.remove();
			System.out.println(qu);
		}else{
			System.out.println("Error! Queue is Empty");
		}
	}
}
