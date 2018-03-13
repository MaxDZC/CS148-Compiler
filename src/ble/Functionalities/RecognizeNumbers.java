/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ble.Functionalities;

/**
 *
 * @author addcarl
 */
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author shellygobui
 */
public class RecognizeNumbers {
    public static String recognizeNumbers(String text){
        //Scanner scanner = new Scanner(text);
        String ret = "";
        //while (scanner.hasNextLine()) {
            String num = text;
            Pattern patternDisplay =  Pattern.compile("display\\(\".*(b_|h_|o_).*\"\\)");
            Matcher matchDisplay = patternDisplay.matcher(num);
            int search1 = num.indexOf(" = ");
            int search2 =  num.indexOf("b_");
            int search3 = num.indexOf("h_");
            int search4 = num.indexOf("o_");
            if((search1 != -1 || search2 != -1 || search3 != -1 || search4 != -1) && !matchDisplay.find()){
                Scanner sc = new Scanner(System.in);
                Pattern patternNumBinary = Pattern.compile("\\bb_[01]+\\b(\\s|\\n)*");
                Pattern patternNumHexa = Pattern.compile("\\bh_([A-F|a-f|0-9]+[A-F|a-f|0-9]+)+\\b(\\s|\\n)*");
                Pattern patternNumOctal = Pattern.compile("\\bo_([1-7])+\\b(\\s|\\n)*;?");
                Pattern patternNumDecimal = Pattern.compile("\\b\\d+\\b(\\s|\\n)*");

		System.out.println("=>This line found a possible binary/octal/hexadecimal/decimal:\n"+num);
                System.out.println("");
                Matcher matcher = patternNumBinary.matcher(num);
		Matcher matcher1 = patternNumHexa.matcher(num);
                Matcher matcher2 = patternNumOctal.matcher(num);
		Matcher matcher3 = patternNumDecimal.matcher(num);
                if(matcher.find()){
                    String extracted = matcher.group();
                    num = extracted.replaceAll("(b_|;|\\n|\\s)","");
                    ret = "\nIt is a binary: \n";
                    ret = ret.concat("Value in decimal: "+Integer.parseInt(num,2));
                    //return ret;
		}else if(matcher1.find()){
                    String extracted = matcher1.group();
                    num = extracted.replaceAll("(h_|;|\\n|\\s)","");
                    ret = "\nIt is a hexadecimal: \n";
                    ret = ret.concat("Value in decimal: "+Integer.parseInt(num,16));
                    //return ret;
                }else if(matcher2.find()){
                    String extracted = matcher2.group();
                    num = extracted.replaceAll("(o_|;|\\n|\\s)","");
                    ret = "\nIt is a octal: \n";
                    ret = ret.concat("Value in decimal: "+Integer.parseInt(num,8)+"\n");
                    
                }else if(matcher3.find()){
                    System.out.println("It is a decimal");
                }else{
                    System.out.println("ERROR");
                }
            }
        //}
        //scanner.close();       
        return ret;
    }
}
