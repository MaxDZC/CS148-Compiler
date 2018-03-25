/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ble.objects;

import java.util.List;
import ble.objects.ArrayBle;
/**
 *
 * @author christian
 */
public class Array2dElement<T> {
    private ArrayBle row;
    private String Key;
    
    public Array2dElement(String Key, ArrayBle row){
        this.row = new ArrayBle();
        this.Key = Key;
    }

    /**
     * @return the row
     */
    public ArrayBle getRow() {
        return row;
    }

    /**
     * @param row the row to set
     */
    public void setRow(ArrayBle row) {
        this.row = row;
    }

    /**
     * @return the Key
     */
    public String getKey() {
        return Key;
    }

    /**
     * @param Key the Key to set
     */
    public void setKey(String Key) {
        this.Key = Key;
    }
    
    
}
