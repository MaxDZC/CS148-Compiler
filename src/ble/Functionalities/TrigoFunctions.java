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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Andrew
 */
public class TrigoFunctions {
    public static void trigoFuncs(String text){
        //String codeStr = text;
        Scanner scanner = new Scanner(text);
        String codeStr = "";
        Pattern patternTrigo = Pattern.compile("\"*\\s*(((arc)?(sin|cos|tan|csc|cot|sec)|toRadians|toDegrees)\\s*\\((\\s+)*((-)\\d+|\\w+)(\\s+)*\\))\\s*\"*");
        Matcher matchTrigo;
        /*IF INPUT CODE
        System.out.println("Input here:");
        codeStr = scan.nesxtLine();*/
        //System.out.println("String:\n" + varStr);
        //System.out.println(codeStr);
        while(scanner.hasNextLine()){
            codeStr = scanner.nextLine();
            matchTrigo = patternTrigo.matcher(codeStr);
            
            if(matchTrigo.find()){
                //System.out.println("TRUE");
                System.out.println(matchTrigo.group());
                
                char[] codeArr = matchTrigo.group().toCharArray();
                String funcName = "", val = "";
                int i, len = matchTrigo.group().length();
                boolean start = false;
                //for(i = 0; i < len && codeArr[i] != '('; i++){}
                for(i = 0; i < len; i++){
                    if(codeArr[i] == '('){
                        start = true;
                    }
                    if(start == true && codeArr[i]!= '(' && codeArr[i] != ')'){
                        val = val + codeArr[i];
                    }else{
                        funcName = funcName + codeArr[i];
                    }
                }
                val = val.replaceAll("\\s+", "");
                funcName = funcName.replaceAll("\\s+", "");
                
                try{
                    double num = 0;
                    double parsedNum = Double.parseDouble(val);
                    
                    //TO RADIAN AND TO DEGREE FUNCs
                    if(funcName.equalsIgnoreCase("toDegrees()")){
                        num = Math.toDegrees(parsedNum);
                    }else if(funcName.equalsIgnoreCase("toRadians()")){
                        num = Math.toRadians(parsedNum);
                    }
                    
                    //TRIGO FUNCS
                    if(funcName.equalsIgnoreCase("sin()") ){
                        num = Math.sin(parsedNum);
                    }else if(funcName.equalsIgnoreCase("cos()")){
                        num = Math.cos(parsedNum);
                    }else if(funcName.equalsIgnoreCase("tan()")){
                        num = Math.tan(parsedNum);
                    }else if(funcName.equalsIgnoreCase("csc()")){
                        num = 1 / Math.cos(parsedNum);
                    }else if(funcName.equalsIgnoreCase("cot()")){
                        num = 1 / Math.tan(parsedNum);
                    }else if(funcName.equalsIgnoreCase("sec()")){
                        num = 1 / Math.sin(parsedNum);
                    }

                    
                    System.out.println(Double.toString(num));
                }catch(NumberFormatException e){
                    System.err.println(val + " is not a number");
                }
                //System.out.println(val + " " + funcName);
            }
        }
    }
}
