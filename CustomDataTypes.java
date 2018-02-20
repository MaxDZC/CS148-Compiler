/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilerProject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomDataTypes extends Object implements Serializable{
    public static Pattern design = Pattern.compile("design(\\s)+(\\w)+");
    public static Pattern dataPattern = Pattern.compile("(design)\\s+\\w+\\s*(((let)\\s+\\w+\\s+((=)\\s+(\\w+|\\d+|(\\d+(.)\\d+)|((\")\\s*\\w+\\s*(\")))\\s+)*)*)(finish)*");
    public static Pattern initializeData = Pattern.compile("(Let)(\\s)(\\w+)(\\s?)\\=(\\s?)(\\w+)");
    public static Pattern accessData = Pattern.compile("(\\w+)(\\.)(\\w+)");
    public static Pattern field = Pattern.compile("\\w+");
    
    public void customDataTypes(String match) throws FileNotFoundException, IOException, Exception{
        CustomDataTypes obj = new CustomDataTypes();
        List<String> list_design = obj.get_StructName(match,design);
        List<String> list_designPattern = obj.get_StructName(match, dataPattern);
        List<String> list_init = obj.get_StructName(match,initializeData);
        List<String> list_access = obj.get_StructName(match,accessData);
        boolean struct_bol = true, declaration_bol = true;
        
        struct_bol = checkFor_DatatypeStructure(match,list_designPattern, list_design);
        if(struct_bol){
            declaration_bol = checkFor_DeclarationVariable(match,list_design,list_init);
            if(declaration_bol){
                checkFor_AccesingField(match,list_designPattern,list_init,list_access);
            }
        }
     }
    
    //***Tokenizer***//
    public static String[] tokenizer(String s, String reference) throws Exception{
        String[] tokens = s.split(reference);
        return tokens;
    }
    
    //****Extracting Data****//
    public static String extract_Data(String s,Pattern matchD,String token_M,int num) throws Exception{
        Matcher m = matchD.matcher(s);
        String dname = null;

        if (m.find()){
            String d = m.group(0);
            String[] tokens = tokenizer(d,token_M);
            dname = tokens[num];
        }else{
           switch(num){
                case 0 : System.out.println("Datatype Struture not found.."); break;
                case 3 : System.out.println("Design keyword not found.."); break;
                case 1 : System.out.println("Datatype name not found.."); break;
            }
        }
        return dname;
    }    
    
    public static String extract_FromMatch(String s, Pattern matchD) throws Exception{
        Matcher m = matchD.matcher(s);
        String dname = null;
        
        if (m.find()){
            String d = m.group(0);
            dname = d;
        }else{
            System.out.println("Structure or variable not found!");
        }
        return dname;
    }    
    
    private List<String> get_StructName(String data, Pattern data_M){
        List<String> result = new ArrayList<String>();
        Matcher m = data_M.matcher(data);
        
        while (m.find()) {
            result.add(m.group());
        }
        return result;
    }
    
    //*****For validation of datatypes*****//
    public static boolean checkFor_DatatypeStructure(String match, List list_dataPattern, List list_design)throws Exception{
        int x,y,i;
        Matcher m;
        boolean flag = false , flag_init = true, ret = true;
        String design_name1, design_name2, str = "";
        
        for(x = 0 ; x < list_dataPattern.size() && flag == false; x++){
            m = dataPattern.matcher((CharSequence) list_dataPattern.get(x));
            if(m.matches()){
                flag = true;
            }
        }
        /*String sample = "design name"
               + "\n    let fname"
               + "\n    let lname"
               + "\n";*/
            
        if(flag == true){
            for(y = 0; y < list_design.size()-1 && flag_init == true; y++){
                i = y+1;
                design_name1 = extract_Data((String) list_design.get(y),design,"\\s+",1);
                design_name2 = extract_Data((String) list_design.get(i),design,"\\s+",1);
                while(i < list_design.size() && design_name1.equals(design_name2) != true){
                    design_name2 = extract_Data((String) list_design.get(i),design,"\\s+",1);
                    i++;
                }
                if(i < list_design.size()){
                    flag_init = false;
                }
            }
            
            if(flag_init == false){
                System.out.println("Duplication of Datatype Structure Name..");
                ret = false;
            }
        }else{
            System.out.println("An error in one of the Datatype Definition...");
            ret = false;
        }
        return ret;
    }
    
    //****For validation of declaration****//
    public static boolean checkFor_DeclarationVariable(String match, List list_design, List list_init)throws Exception{
        int i = 0, y;
        boolean flag, flag_init = true, ret = true, flag_check = true;
        String design_name, init_CompN1, init_CompN2;
        String init_name, name = null, subN = null;
        Matcher m;
        
        for(y = 0; y < list_init.size()-1 && flag_init == true; y++){
            i = y+1;
            m = initializeData.matcher((CharSequence) list_init.get(y));
            if(m.matches()){
                init_CompN1 = extract_Data((String) list_init.get(y),initializeData,"\\s+",1);
                init_CompN2 = extract_Data((String) list_init.get(i),initializeData,"\\s+",1);
                while(i < list_init.size() && init_CompN1.equals(init_CompN2) != true){
                    init_CompN2 = extract_Data((String) list_init.get(i),initializeData,"\\s+",1);
                    i++;
                }
                if(i < list_init.size()){
                    flag_init = false;
               }
            }else{
                flag_check = false;
                flag_init = false;
            }   
        }

        if(flag_check == true && flag_init == true){
            for(y = 0; y < list_init.size(); y++){
                flag = false;
                init_name = extract_Data((String) list_init.get(y),initializeData,"\\s+",3);
                subN = extract_Data((String) list_init.get(y),initializeData,"\\s+",1);
                for(i = 0; i < list_design.size() && flag != true; i++){
                    design_name = extract_Data((String) list_design.get(i),design,"\\s+",1);
                    if(design_name.equals(init_name)){
                        flag = true;
                        name = design_name;
                    }
                }
                
                if(flag == true){
                    //System.out.println("Datatype " + name + " exist..");
                }else{
                    System.out.println("Datatype " + init_name + " of variable " + subN + " does not exists..");
                    ret = false;
                }
            }
        }else{
            System.out.println("Duplication of variable names or declaration error..");
            ret = false;
        }
        return ret;
    }
        
    //****For checking accesing field****//
    public static void checkFor_AccesingField(String match, List list_designPattern, List list_init, List list_access)throws Exception{
        CustomDataTypes obj = new CustomDataTypes();
        int x,y,i,a,b,trav;
        Matcher m;
        boolean flag = true, flag_init =  true, flag_design = true, flag_access = false, flag_valid = false, flag_match = false;
        String access_data = null, init_Match = null, name = null, struct_field = null, struct_match = null, hold, hold_sub;
        List<String> sub = null;
        
        for(x = 0 ; x < list_access.size() && flag == true; x++){
            m = accessData.matcher((CharSequence) list_access.get(x));
            if(m.matches() == false){
                flag = false;
            }
        }

        if(flag == true){
           for(x = 0; x < list_access.size() && flag_init == true; x++){
              access_data = extract_Data((String) list_access.get(x),accessData,"\\.",0);
              y = 0;
              init_Match = extract_Data((String) list_init.get(y),initializeData,"\\s+",1);
                while(y < list_init.size() && init_Match.equals(access_data) == false){
                    y++;
                    if(y < list_init.size()){
                        init_Match = extract_Data((String) list_init.get(y),initializeData,"\\s+",1);
                    }
                }
                if(y  >= list_init.size()){
                    flag_init = false;
                }
            name = access_data;
           }

           if(flag_init == true){
                for(x = 0; x < list_access.size(); x++){
                    access_data = extract_Data((String) list_access.get(x),accessData,"\\.",0);
                    struct_field = extract_Data((String) list_access.get(x),accessData,"\\.",1);
                    init_Match = extract_Data((String) list_init.get(x),initializeData,"\\s+",1);
                    for(i = 0; i < list_init.size() && access_data.equals(init_Match) != true; i++){
                        init_Match = extract_Data((String) list_init.get(x),initializeData,"\\s+",1);
                    }
                    if(i < list_init.size()){
                        struct_match = extract_Data((String) list_init.get(x),initializeData,"\\s+",3);
                    }
                }

                for( a = 0; a < list_designPattern.size() && flag_valid == false; a++){    
                    sub = obj.get_StructName((String) list_designPattern.get(a),dataPattern);
                    hold = extract_Data(sub.get(0),design,"\\s+",1);
                    if(hold.equals(struct_match) == true){
                        flag_valid = true;
                    }
                }

                a--;
                sub = obj.get_StructName((String) list_designPattern.get(a),dataPattern);
                List<String> list_field = obj.get_StructName(sub.get(0),field);
                for(b = 0; b < list_field.size() && flag_match == false;b++){
                    if(list_field.get(b).equals(struct_field)){
                        flag_match = true;
                    }
                }
                
                if(flag_match == true){
                    //System.out.println("Field "+ struct_field + " exist..");
                }else{
                    System.out.println("Field "+ struct_field + " does not exist..");
                }
           }else{
               System.out.println("Variable " + name + " does not exist or does not initialize!");
           }
        }else{
            System.out.println("Accessing Error");
        }
    }
}