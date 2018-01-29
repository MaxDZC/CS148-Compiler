/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timelibrary;

import java.util.ArrayList;

/**
 *
 * @author OWNER
 */
public class TimeLibraryModel {
 
    public boolean checkTimeDeclartion(String check){
        boolean returnVal =false;
 
        if(check.matches("\\s*(let)\\s*\\w+\\s*(=)+\\s*(Timer)+")){
        
            returnVal = true;
        }
        
        return returnVal;
    }
    public String checkTimeFunctionality(String check){
        String returnVal = "false";
        String[] data = check.split("\\.");
        if(data.length == 2){
            System.out.println(""+data[1]);
            if(data[1].matches("\\s*(start)\\s*")){

                returnVal = "start";
            } else if(data[1].matches("\\s*(reset)\\s*")){

                returnVal = "reset";
            } else if(data[1].matches("\\s*(pause)\\s*")){

                returnVal = "pause";
            } else if(data[1].matches("\\s*(getTime)\\s*")){

                returnVal = "getTime";
            } else if(data[1].matches("\\s*(setMax)+\\s*\\(\\w+\\)\\s*")){
               returnVal = "setMax using variable";
            }
            else if (data[1].matches("\\s*(setMax)+\\s*\\(\\d+(\\.\\d+)\\)\\s*")){
                    returnVal = "setMax using float value";
            }
        }
        return returnVal;
    }
}
