/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ble.objects;

import java.util.ArrayList;
import java.util.List;
import ble.objects.*;

/**
 *
 * @author christian
 */
public class Array2dBle {
    private List<Array2dElement<?>> l;
    
    public Array2dBle() {
        this.l = new ArrayList<>();
    }
    
    public void insert(String row, String key, Object value) {
        ArrayBle tempRow;
        Array2dElement temp;
        int x;
        if(!this.checkRowExists(row)){
            tempRow = new ArrayBle();
            tempRow.insert(key, value);
        }else{
            for(x = 0; x < l.size(); x++){
                if(l.get(x).getKey().equals(row)){
                    tempRow = l.get(x).getRow();
                    tempRow.insert(key, value);
                    //l.get(x).setRow(tempRow);
                    temp = new Array2dElement(key, tempRow);
                    
                    l.set(x, temp);
                }
            }
        }
    }
    
    void deleteAtPos(String row, String col){
        int x, y;
        ArrayBle tempRow;
        Array2dElement temp;
        if(this.checkPosExists(row, col)){
            for(x = 0; x < l.size() && !l.get(x).getKey().equals(row); x++){}
            tempRow = l.get(x).getRow();
            for(y = 0; y < tempRow.size() && !tempRow.getKeyAt(y).equals(col); y++){}
            tempRow.deleteAt(y);
            temp = new Array2dElement(col, tempRow);
            l.set(x, temp);
        }
    }
    
    public boolean isEmpty() {
        return l.isEmpty();
    }

    public int size() {
        return l.size();
    }
    
    boolean checkRowExists(String RowKey){
        int x;
        boolean ret;
        ret = false;
        
        for(Array2dElement row : l){
            if(row.getKey().equals(RowKey)){
                ret = true;
                break;
            }
        }
        return ret;
    }
    
    boolean checkPosExists(String row, String col){
        boolean ret = false;
        int x;
        for(x = 0; x < l.size() && !l.get(x).getKey().equals(row); x++){}
        if(l.get(x).getKey().equals(row)){
            ret = true;
        }
        return ret;
    
    }
    
    Object getValueAt(String row, String col){
        Object ret = 0;
        int x, y;
        ArrayBle temp;
        for(x = 0; x < l.size() && !l.get(x).getKey().equals(row); x++){}
        if(l.get(x).getKey().equals(row)){
            temp = l.get(x).getRow();
            ret = temp.get(col);
        }
        return ret;
    }
}
