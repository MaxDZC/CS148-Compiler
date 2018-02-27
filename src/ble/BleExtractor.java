package ble;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Cadigal, Dimpas, Gelbolingo, Gimenez, Po
 *
 */
public class BleExtractor {
	private final String regex = "(?<=(<\\?ble>))(\\w|\\d|\\n|[().,\\-:;@#$%^&*\\[\\]\"'+–/\\/®°°!?{}|`~=]|\\t|\\s)+?(?=(<ble\\?>))";
	private static final File CONSTANT_FOLDER = new File("bledocs/");
	
	public BleExtractor() {
		
	}
	
	public String extractBle(File file) throws FileNotFoundException, IOException {
		String temp = "", text = null;
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		while((text = reader.readLine()) != null) {
			temp += text;
		}
		
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(temp);
		String filename = file.getName();
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(CONSTANT_FOLDER+"extracted_"+filename));
		writer.write(m.group());
		
		writer.close();
		reader.close();
		
		return "extracted_"+filename;
	}
}
