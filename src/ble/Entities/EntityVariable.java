package ble.entities;

public class EntityVariable extends Entity {	
	
	protected String type;
	
	public EntityVariable(String name, Object value, String scope, String type) {
		super.setName(name);
		super.setValue(value);
		super.setScope(scope);
		this.setType(type);
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

}
