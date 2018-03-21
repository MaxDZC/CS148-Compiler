package ble.Network.Memory;

public class MemoryItem {

	protected String key = null;
	protected String value = null;

	public MemoryItem(String key, String value) {
		this.key = key;
		this.value = value;
	}


	//

	public void setKey(String key) {
		this.key = key;
	}

	public void setValue(String value) {
		this.value = value;
	}


	//

	public String getKey() {
		return this.key;
	}

	public String getValue() {
		return this.value;
	}
}