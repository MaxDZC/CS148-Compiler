package ble.objects;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Sean Cadigal
 * @version 0.1.0
 * @since January 11, 2018 */
public class ArrayBle {
	private List<ArrayElement<?>> l;
	
	public ArrayBle() {
		this.l = new ArrayList<>();
	}
	
	public void put(String key, Object value) {
		ArrayElement<Object> e = new ArrayElement<>(key,value);
		l.add(e);
	}
	
	public Object get (String key) {
		Object ret = null;
		int i;
		
		for(i = 0 ; i < l.size() && l.get(i).getKey().contentEquals(key); i++) { }
		if( i < l.size()) {
			ret = l.get(i).getValue();
		}
		
		return ret;
	}
	
	public Object get (int index) {
		return (index < l.size()) ? l.get(index).getValue() : null;
	}
	
	public boolean isEmpty() {
		return l.isEmpty();
	}
	
	public int size() {
		return l.size();
	}
	
	
	public Object search (Object value) {
		Integer i = -1;
				
		if(!l.isEmpty()) {
			for (i = 0; i < l.size() && !l.get(i).getValue().equals(value); i++ ) {}
			if(i >= l.size()) { 
				i = -1;
			}
			
		}
		
		return i;
	}
	
	public Object delete(Object value) {
		Object ret = "ERROR: value not found, array returned -1";
		int index;
		
		index = (Integer)this.search(value);
		if(index != -1) {
			ret = l.remove(index);
		}		
		return ret;
	}
	
	public void insert(String key, Object value) {
		ArrayElement<Object> e = new ArrayElement<>(key,value);
		
		l.add(e);
	}
	
	
	public void insert(String key, Object value, int position) {
		ArrayElement<Object> e = new ArrayElement<>(key,value);
		l.add(position, e);
	}
}
