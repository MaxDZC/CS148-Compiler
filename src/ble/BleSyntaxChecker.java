package ble;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ble.objects.ArrayBle;
import ble.Functionalities.ArraysFunct;
import ble.Storage.BleStorage;
import ble.SyntaxAnalyzer.Arrays;

public class BleSyntaxChecker {
	private Matcher m;
	private Pattern p;
	
	public boolean syntaxCheck(String line, BleStorage storage) {
		
		boolean cond = false;
		//checks if array
		p = Pattern.compile(Arrays.getArrayPattern());
		m = p.matcher(line);
		if(m.find()) {
			String name = getVariableName(line);
			if(!storage.exist(name)) {
               ArrayBle arr = new ArrayBle();
               storage.storeVar("Array", name, arr, null);
               cond = true;
               System.out.println("Array Stored: "+name);
			}
		}
		
		p = Pattern.compile("\\w*\\(.*\\)");
		m = p.matcher(line);
		System.out.println(line);
		if (m.find() && !cond) {			// checks if function
			ArraysFunct arrFunction = new ArraysFunct();
			
			if(arrFunction.getFunction(line)) {
				arrFunction.findFunction(storage.getStorage());
				cond = true;
			}
		}
		
		return cond;
	}
	
	public String getVariableName(String line) {
		String name = null;
		String pat = "\\s[a-zA-z]*(\\s*)?=";
        p = Pattern.compile(pat);
        m = p.matcher(line);
        if(m.find()){
            String patt = "[a-zA-z]+";
            p = Pattern.compile(patt);
            m = p.matcher(m.group());
            if(m.find()){
               name =   m.group();                        
            }
        }
        return name;
	}
}
