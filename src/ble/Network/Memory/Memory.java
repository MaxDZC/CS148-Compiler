package ble.Network.Memory;

import java.util.ArrayList;
import java.util.List;

abstract public class Memory {

	protected List<MemoryItem> items = null;

	public Memory() {
		this.items = new ArrayList<MemoryItem>();
	}

	public void set(String key, String value) {
		MemoryItem item = new MemoryItem(key, value);

		this.items.add(item);
	}

	public MemoryItem get(String key) {
		MemoryItem item = null;

		for (MemoryItem _item : this.items) {
			if (_item.getKey() == key) {
				item = _item;
				break;
			}
		}

		return item;
	}
}