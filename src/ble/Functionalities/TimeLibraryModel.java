/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ble.Functionalities;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Max
 */
public class TimeLibraryModel {
        private Float maxTime;
    private Timer time = new Timer();
    int interval;
    boolean start = true;
    TimeLibraryModel(){
        maxTime = -1.0f;
    }
    public Float getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Float maxTime) {
        this.maxTime = maxTime;
    }
    
    public boolean checkTimeDeclartion(String check){
        boolean returnVal =false;
 
        if(check.matches("\\s*(let)\\s*\\w+\\s*(=)+\\s*(Timer)+")){
        
            returnVal = true;
        }
        
        return returnVal;
    }
   
    public String checkTimeFunctionality(String check){
        String returnVal = "false";
        String[] data = check.split("\\(");
        int delay = 1000, period = 1000;
      
        if(data.length >=2){
            data[1] = data[1].replaceAll("\\)","");
            String[] data1stpart = data[0].split("\\.");
            System.out.println(data1stpart[1]);
            
            if(data1stpart[1].matches("\\s*(setMax)+\\s*\\(\\w+\\)\\s*")){
               returnVal = "setMax using variable";
            }else if(data1stpart[1].matches("\\s*(setMax)\\s*")){
                if(data[1].matches("\\w+")){
                    System.out.println("variable used");
                } else {
                   
                    maxTime = Float.parseFloat(data[1].trim());
                    interval = maxTime.intValue();
                }
                System.out.println("maxtime is:"+maxTime);
                return "setMax is used";
            }
//            else if (data[1].matches("\\s*(setMax)+\\s*\\(\\d+(\\.\\d+)\\)\\s*")){
//                
//                    data[1] = data[1].replaceAll("\\s*(setMax)+\\s*\\(\\s*","");
//                    data[1] = data[1].replace("\\)", "");
//                    data[1].trim();
//                    maxTime = Long.parseLong(data[1]);
//                    System.out.println("max time is: " + maxTime);
//                    returnVal = "setMax using float value";
//            }
        } else {
            data = check.split("\\.");
            if(data[1].matches("\\s*(start)\\s*")){
                if(maxTime < 0){
                    System.out.println("max Time hasnt been set");
                } else {
                   if(start ){
                 
                        start = false;
                      
                        time.scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {
                                  checkInterval();
                            }
                        }, delay, period);
                   } 
                }
                    returnVal = "start";
                } else if(data[1].matches("\\s*(reset)\\s*")){
                    interval = maxTime.intValue();
                    returnVal = "reset";
                } else if(data[1].matches("\\s*(pause)\\s*")){
                    if(interval>0){
                        time.cancel();
                        time= new Timer();
                        start= true;
                    }
                    returnVal = "pause";
                } else if(data[1].matches("\\s*(getTime)\\s*")){
                    System.out.println("the current time is:"+interval);
                    returnVal = "" + interval;
                }
        }
        
        if(data.length == 2){
             
        }
        return returnVal;
    }
     public boolean checkInterval(){
          System.out.println(interval);
        if (interval == 0){
            time.cancel();
            interval = maxTime.intValue();
            System.out.println("TIME IS UP");
        }
        
                    
        interval-=1;
        return true;
    }
    public static TimeLibraryModel getTimeVariable(){
        return new TimeLibraryModel();
    }
}
