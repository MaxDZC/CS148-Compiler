package ble.Network.Http;

import java.util.HashMap;
import java.util.Map;

public class Session {

	protected String id;

	protected Map<String, String> data = null;

	public Session() {
		this.data = new HashMap<String,String>();
	}


	//

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}


	//

	public void set(String key, String value) {
		this.data.put(key, value);
	}

	public String get(String key) {
		return this.data.get(key);
	}
}