package ble.SyntaxAnalyzer;

/**
 *
 * @author Max
 */
public class Queue_stack {
    public static String getStack(){
        return "let\\s+\\w+\\s+(\\=\\s+stack\\(([1-9][0-9]*|[a-zA-Z][\\d\\w\\_]*)?\\))|" +
			"\\s*stack\\s*\\(\\w[\\w\\d\\_]*\\s*\\)\\s*|" +
			"\\s*pop\\s*\\(\\w[\\w\\d\\_]*\\s*\\)\\s*|" +
			"\\s*size\\s*\\(\\w[\\w\\d\\_]*\\s*\\)\\s*|" +
			"\\s*push\\s*\\(\\w[\\w\\d\\_]*\\s*\\,\\s*\\w[\\w\\d\\_]*\\s*\\)\\s*";
    }
    
    public static String getQueue(){
        return "let\\s+\\w+\\s+(\\=\\s+queue\\(([1-9]+[0-9]*|\\w[\\w\\d\\_]*)?\\))|" +
			"\\s*enqueue\\s*\\([A-z][\\w\\_]*\\s*\\,(\\s*[A-z][\\w\\_]*\\s*|\\d+|\\-\\d+)\\)\\s*|" +
			"\\s*dequeue\\s*\\([A-z][\\w\\_]*\\s*\\)\\s*";
    }
}
