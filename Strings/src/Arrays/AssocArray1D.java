/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arrays;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author christian
 */
public class AssocArray1D {
    private Map<Object, Object> vars;
    public AssocArray1D(){
        vars = new HashMap<>();
    }
    public void insert(Object Key, Object val){
        vars.put(Key, val);
    }
    
    public Object retrieve(Object Key){
        return vars.get(Key);   
    }
    
}
