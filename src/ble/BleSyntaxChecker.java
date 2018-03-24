package ble;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ble.objects.ArrayBle;
import ble.Functionalities.*;
import ble.SyntaxAnalyzer.*;
import ble.entities.Entity;
import ble.entities.EntityVariable;
import ble.Storage.BleStorage;
import ble.SyntaxAnalyzer.DataTypes; 

public class BleSyntaxChecker {
	private Matcher m;
	private Pattern p;
	
	public boolean syntaxCheck(String line, BleStorage storage) {
		String name = null;
		boolean cond = false;
                
		//checks if array
		p = Pattern.compile(Arrays.getArrayPattern());
		m = p.matcher(line);
		if(m.find()) {
			name = getVariableName(line);
			if(!storage.exist(name)) {
               ArrayBle arr = new ArrayBle();
               storage.storeVar("Array", name, arr, null);
               cond = true;
               System.out.println("Array Stored: "+name);
			}
		}
		
		//Assignment
		p = Pattern.compile("let \\w*\\s*=.*");
		m = p.matcher(line);
		if (m.find()) {
			name = getVariableName(line);
			if(!storage.exist(name)) {
				p = Pattern.compile("=.*");
				m = p.matcher(line);
				m.find();
				line = m.group();
				line = line.replaceAll("=", "");
				line = line.trim();
				EntityVariable en = new EntityVariable(name, null, null, "Any");
				storage.storeVar("Any", name, en, null);
				System.out.println("Variable stored: "+name);
			}
		}
		
		//MDAS
		if(MDAS.checkPattern(line) && !cond) {
			Object r = MDASFunc.evalExp(line);
			EntityVariable en = (EntityVariable)storage.read(name);
			System.out.println("EN: "+en);
			en.setValue(r);
			en.setType("Number");
			cond = true;
		}
		
		//Display
		if(DisplayRegex.checkPattern(line) && !cond) {
			Display.checkPattern(line);
			cond = true;
		}
		
		//Validate
		if(ValidationFunct.checkPattern(line) && !cond) {
			ValidationFunct.run(line);
			cond = true;
		}
		
				
		p = Pattern.compile("\\w*\\(.*\\)");
		m = p.matcher(line);
		System.out.println(line);
		
		if (m.find() && !cond) {			// checks if function
			ArraysFunct arrFunction = new ArraysFunct();
			
			if(arrFunction.getFunction() != null) {
				//arrFunction.findFunction(storage.getStorage());
				cond = true;
			}
		}
		
		
		if(name != null) {
			Entity en = storage.read(name);
			System.out.println(name+" "+en.getValue());
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