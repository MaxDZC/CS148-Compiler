package ble.injector;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Display {
	
	//Contains the contents of a .html file received via http request from special task no. 1...
	private String htmlCode;
	
	//Contains the result processed from the .html file suitable for injection from special task no. 2...
	private String results[];
	
	//Catches all BLE tags...
	private static final String EXTRACT_TAG = "(?<=(<@BLE))(\\w|\\d|\\n|[().,\\-:;@#$%^&*\\[\\]\"'+–/\\/®°°!?{}|`~=]|\\t|\\s)+?(?=(@>))";
                  
                   //Filters BLE to lines that contain display
                  private static final String DISPLAY_TAG = "\\s*display[(]\\s*((\".+\"|\'.+\'|\\d+(\\.\\d+)?|[a-zA-Z]\\w*)(\\s*[+]\\s*(\".+\"|\'.+\'|\\d+(\\.\\d+)?|[a-zA-Z]\\w*))*)?\\s*[)]\\s*";
	
	public Display(String htmlCode, String[] results){
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
	
	public void display(){
		Pattern pattern = Pattern.compile(Display.EXTRACT_TAG);
		Matcher matcher = pattern.matcher(this.htmlCode);
		
		ArrayList<String> contents = new ArrayList<>();
		while(matcher.find()){
			contents.add(matcher.group(0));
		}
		
		@SuppressWarnings("unchecked")
		ArrayList<String> clone = (ArrayList<String>) contents.clone();
		ArrayList<String> filter = new ArrayList<>();
		
                                    Pattern catchDisplay = Pattern.compile(Display.DISPLAY_TAG);
		for(int x = 0; x < clone.size(); x++){
                                        Matcher matchDisplay = catchDisplay.matcher(clone.get(x));
                                        while(matchDisplay.find()){
                                                if(filter.indexOf(clone.get(x).replaceAll("\\s*", "")) == -1){
                                                filter.add(clone.get(x).replaceAll("\\s*", ""));
                                            }
                                        }
		}
		
		for(int x = 0; x < filter.size(); x++){
                                                      String temp = filter.get(x).replaceAll("\\s*(display)\\(\\s*", "").replaceAll("\\s*\\)\\s*", "");
			Pattern checker = Pattern.compile(temp);
			for(int y = 0; y < this.results.length; y++){
				Matcher result = checker.matcher(this.results[y]);
				Matcher htmlCode = checker.matcher(this.htmlCode);
				if(result.find() && htmlCode.find()){
					this.htmlCode = this.htmlCode
							.replaceAll("\\s*(display)\\(\\s*"+temp+"\\s*\\)\\s*", this.results[y]
									.replaceAll("\\s*"+temp+"\\s*\\=\\s*", ""));
				}
			}
		}
                
                                    this.htmlCode = this.htmlCode.replaceAll("<@BLE","");
                                    this.htmlCode = this.htmlCode.replaceAll("@>", "");
                                    this.htmlCode = this.htmlCode.replaceAll("(?<=(<@BLE))(\\w|\\d|\\n|[().,\\-:;@#$%^&*\\[\\]\"'+–/\\/®°°!?{}|`~=]|\\t|\\s)+?(?=(@>))", "");
		
	}
}
