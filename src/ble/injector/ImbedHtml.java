package ble.injector;

import ble.Functionalities.Display;
import ble.SyntaxAnalyzer.DataTypes;
import static ble.SyntaxAnalyzer.SyntaxAnalyzer.DISPLAY;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImbedHtml {
	
	//Contains the contents of a .html file received via http request from special task no. 1...
	private String htmlCode;
	
	//Contains the result processed from the .html file suitable for injection from special task no. 2...
	private String results[];
	
	//Catches BLE tags...
	private static final String EXTRACTTAG = "(\\<@BLE)([^\\<\\@BLE\\@\\>]*)(@\\>)";
	
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
		Pattern pattern = Pattern.compile(ImbedHtml.EXTRACTTAG);
		Matcher matcher = pattern.matcher(this.htmlCode);
		
		ArrayList<String> contents = new ArrayList<>();
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
                
            
                 
                Display fromDisp = new Display(); 
                String newHtml = "";
                
                String bleFrag = "";
            
                
                System.out.println("end of this.htmlCode" + this.htmlCode.length());
                for(int start = 0, end = this.htmlCode.indexOf("<@BLE"); start > -1 && end > -1;){
                    
                    newHtml += this.htmlCode.substring(start, end);
                   
                   // newHtml += listString;
                   
                    
                    
                    start = this.htmlCode.substring(end).indexOf("@>");
                    if(start > -1){ // if ble fragments are found
                        start += end + 2; 
                        
                        System.out.println("END :: " + this.htmlCode.substring(end, start));
                        
                        bleFrag = this.htmlCode.substring(end + 5, start -2);
                        for(String c : bleFrag.split("\n")){  //search for display one by one
                           // System.out.println("cccc" + c);
                            if(c.matches(DISPLAY)){ // if display statement
                              System.out.println("inside" + c);
                                fromDisp.run(c); // store in contents
                            }
                        }
                        newHtml += fromDisp.getDisplayedContents(); 
                        end = this.htmlCode.substring(start).indexOf("<@BLE");
                        if(end > -1){
                            end += start; 
                        }else{
                        end = this.htmlCode.length();
                        }
                    }     
                }
                
               
                this.htmlCode = newHtml; 
                /*DataTypes dt = new DataTypes(); 
                 // removes all non related bleCOde
                this.htmlCode = this.htmlCode.replaceAll(DISPLAY, "IDKKK VALUE");
                System.out.println("AFTERREOLACE DISPLAYYY" + this.htmlCode);
		//this.htmlCode = this.htmlCode.replaceAll("(?<=(<@BLE))(\\w|\\d|\\n|[().,\\-:;@#$%^&*\\[\\]\"'+–/\\/®°°!?{}|`~=]|\\t|\\s)+?(?=(@>))", "");
		this.htmlCode = this.htmlCode.replaceAll("<@BLE","");
		this.htmlCode = this.htmlCode.replaceAll("@>", "");
                */
                System.out.println("FROM IMBEDRESULTS CONSTRUCTOR HTMLCODE" + this.htmlCode);
	}
}
