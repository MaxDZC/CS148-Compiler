/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ble.SyntaxAnalyzer;

import ble.Functionalities.TimeLibraryModel;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author OWNER
 */
public class DeclaringAndAssigning {
    public static Map<String, Data> allVars = new HashMap<String, Data>();
    
    public static boolean checkDeclarationStatement(String line,String scope){
        boolean returnval = false;
        String[] token = line.split("\\=");
        Data newVar = new Data();
         
         if(token[0].matches("\\s*(let)\\s*\\w+")){
             token[0] = token[0].replaceFirst("\\s*(let)", "");
             token[0].trim();
             if(!allVars.containsKey(token[0])){      //check if variable name exist
                if(token.length >1){     //assigning statement is dectected
                    token[1].trim();
                    if(token[1].matches("(Time)")){
                        newVar.setValue(TimeLibraryModel.getTimeVariable()); // Calls Time functionality to save datatype;
                        newVar.setScope(scope);
                        allVars.put(token[0], newVar);
                        returnval=true;
                    }else if(token[1].matches("(Array)")){
                        newVar.setValue(Float.parseFloat(token[1])); // Calls Array functionality to save datatype;
                        newVar.setScope(scope);
                        allVars.put(token[0], newVar);
                        returnval=true;
                    }else if(token[1].matches("(Stack)")){
                        newVar.setValue(Float.parseFloat(token[1])); // Calls stack functionality to save datatype;
                        newVar.setScope(scope);
                        allVars.put(token[0], newVar);
                        returnval=true;
                    }else if(token[1].matches("(Queue)")){
                        newVar.setValue(Float.parseFloat(token[1])); // Calls Queue functionality to save datatype;
                        newVar.setScope(scope);
                        allVars.put(token[0], newVar);
                        returnval=true;
                    }else if(token[1].matches("([0-9\\-\\*\\+\\s\\(\\)\\.,\\w\\\\/]+)")){
                        //MDAS FUNCTIONALITY returns the value and place it to the 2d array
                    }else if(token[1].matches("[-+]?[0-9]+")){
                        newVar.setValue(Integer.parseInt(token[1])); // Integer datatype;
                        newVar.setScope(scope);
                        allVars.put(token[0], newVar);
                        returnval=true;
                    }else if(token[1].matches("[-+]?[0-9]*\\.[0-9]+")){
                        newVar.setValue(Float.parseFloat(token[1])); // Float datatype;
                        newVar.setScope(scope);
                        allVars.put(token[0], newVar);
                        returnval=true;
                    } else if(token[1].matches("\\w+")){  //Declaring strings
                        newVar.setValue(token[1]);
                        newVar.setScope(scope);
                        allVars.put(token[0], newVar);
                        returnval=true;
                    } 
                } else {            //declaring empty variable
                    newVar.setValue("NOTHING");
                    newVar.setScope(scope);
                    allVars.put(token[0], newVar);
                    returnval=true;
                }
             }
         }
         
         return returnval;
    }
    
}
