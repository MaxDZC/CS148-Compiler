package ble.Storage;

import java.util.HashMap;
import java.util.Map;

import ble.entities.Entity;
import ble.entities.EntityFunction;
import ble.entities.EntityVariable;

public class BleStorage {
	protected Map<String, Entity> storage;

	public BleStorage() {
		storage = new HashMap<String, Entity>();
	}
	
	public void storeVar(String type, String name, Object value, String scope) {
		if(!storage.containsKey(name)) {
			EntityVariable var = new EntityVariable(name,value,scope,type);
			storage.put(name, var);
		}
	}
	
	public void storeFunction(String name, String value, String scope) {
		if(!storage.containsKey(name)) {
			EntityFunction func = new EntityFunction(name,value,scope);
			storage.put(name, func);
		}
	}
	
	public Entity read(String name) {
		return (storage.containsKey(name))?storage.get(name):null;
	}
	
	public Entity varUpdate(String name, Object value) {
		Entity var = null;
		if(storage.containsKey(name)) {
			var = storage.get(name);
			var.setValue(value);
			storage.replace(name, var);
		}
		return var;
	}
	
	public boolean exist(String name) {
		return storage.containsKey(name);
	}
	
	public Map<String,Entity> getStorage(){
		return this.storage;
	}
	
	public void setStorage(Map<String,Entity> storage) {
		this.storage = storage;
	}
}
