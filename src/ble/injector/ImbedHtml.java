package injector;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImbedHtml {
	
	//Contains the contents of a .html file received via http request from special task no. 1...
	private String htmlCode;
	
	//Contains the result processed from the .html file suitable for injection from special task no. 2...
	private String results[];
	
	//Catches BLE tags...
	private static final String extractTag = "(\\<\\@BLE)([^\\<\\@BLE\\@\\>]*)(\\@\\>)";
	
	public ImbedHtml(String htmlCode, String[] results){
		//sample input...
		this.htmlCode = htmlCode;
		this.results = results;
	}
	
	//getters and setters...
	public String getHtmlCode() {
		return htmlCode;
	}

	public void setHtmlCode(String htmlCode) {
		this.htmlCode = htmlCode;
	}

	public String[] getResults() {
		return results;
	}

	public void setResults(String[] results) {
		this.results = results;
	}
	
	public void imbedResults(){
		Pattern pattern = Pattern.compile(ImbedHtml.extractTag);
		Matcher matcher = pattern.matcher(this.htmlCode);
		
		ArrayList<String> contents = new ArrayList<String>();
		while(matcher.find()){
			contents.add(matcher.group(0));
		}
		
		@SuppressWarnings("unchecked")
		ArrayList<String> clone = (ArrayList<String>) contents.clone();
		ArrayList<String> filter = clone;
		
		for(int x = 0; x < filter.size(); x++){
			filter.set(x, filter.get(x).replaceAll("\\s*\\<\\@BLE\\s*", ""));
			filter.set(x, filter.get(x).replaceAll("\\s*\\@\\>\\s*", ""));
		}
		
		for(int x = 0; x < filter.size(); x++){
			Pattern checker = Pattern.compile(filter.get(x));
			for(int y = 0; y < this.results.length; y++){
				Matcher result = checker.matcher(this.results[y]);
				Matcher htmlCode = checker.matcher(this.htmlCode);
				if(result.find() && htmlCode.find()){
					this.htmlCode = this.htmlCode
							.replaceAll(contents.get(x), this.results[y]
									.replaceAll("\\s*"+filter.get(x)+"\\s*\\=\\s*", ""));
				}
			}
		}
	}
}
