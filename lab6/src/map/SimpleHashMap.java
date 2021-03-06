package map;

import java.util.Random;

public class SimpleHashMap<K, V> implements Map<K, V> {
	private Entry<K, V>[] table;
	private int capacity = 16;
	private double load = 0.75;
	private int size;

	@SuppressWarnings("unchecked")
	public SimpleHashMap() {
		table = (Entry<K, V>[]) new Entry[capacity];
		this.size = 0;
	}

	@SuppressWarnings("unchecked")
	public SimpleHashMap(int capacity) {
		this.capacity = capacity;
		table = (Entry<K, V>[]) new Entry[capacity];
		this.size = 0;
	}

	public static void main(String[] args) {
		Random rand = new Random();
		SimpleHashMap<Integer, Integer> shm = new SimpleHashMap<Integer, Integer>(10);

		for (int i = 0; i < 10; i++) {
			int rnd = rand.nextInt(200) - 100;
			shm.put(rnd, rnd);
		}
		System.out.println(shm.show());
		System.out.println("Capacity: " + shm.capacity);
		System.out.println("Size: " + shm.size());
		System.out.println("Load: " + (double) shm.size() / shm.capacity);
	}

	public String show() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < table.length; i++) {
			sb.append(i);
			sb.append("\t");

			Entry<K, V> entry = table[i];
			while (entry != null) {
				sb.append(entry);
				if (entry.next != null) {
					sb.append(" ");
				}
				entry = entry.next;
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	@Override
	public boolean isEmpty() {
		/*for (int i = 0; i < capacity; i++) {
			if (table[i] != null) {
				return false;
			}
		}
		return true;*/
		return size==0;
	}

	@Override
	public V put(K arg0, V arg1) {
		int index = index(arg0);
		if (table[index] == null) {
			table[index] = new Entry<K, V>(arg0, arg1);
			size++;
			if (size() > capacity * load) {
				rehash();
			}
			return null;
		} else {
			Entry<K, V> e = new Entry<K, V>(arg0, arg1);
			Entry<K, V> oldEntry = find(index, arg0);
			V oldValue = null;

			if (oldEntry != null) {
				oldValue = (V) oldEntry.getValue();
				oldEntry.value = arg1;
			} else {
				e.next = table[index];
				table[index] = e;
				size++;
			}
			if (size() > capacity * load) {
				rehash();
			}
			return oldValue;
		}
	}

	@SuppressWarnings("unchecked")
	private void rehash() {
		size=0;
		Entry<K, V>[] tempTable = table;
		capacity = capacity * 2;
		Entry<K, V> temp;

		table = (Entry<K, V>[]) new Entry[capacity];

		for (int i = 0; i < tempTable.length; i++) {
			if (tempTable[i] != null) {
				temp = tempTable[i];
				while (temp != null) {
					put(temp.getKey(), temp.getValue());
					temp = temp.next;
				}
			}
		}
	}

	@Override
	public V remove(Object arg0) {
		@SuppressWarnings("unchecked")
		K key = (K) arg0;
		int index = index(key);
		V value = null;
		Entry<K, V> oldEntry = find(index, key);

		if (table[index] == null) {
			//value ??r null
		} else if (table[index].getKey().equals(key)) {
			value = table[index].getValue();
			table[index] = table[index].next;
			size--;
		} else if (oldEntry != null) {
			value = (V) oldEntry.getValue();
			Entry<K, V> e = table[index];
			while (e.next != null) {
				if (e.next.equals(oldEntry)) {
					e.next = oldEntry.next;
				} else {
					e = e.next;
				}
			}
			size--;
		}
		return value;
	}

	//@Override
	/*public int size() {
		int size = 0;
		for (int i = 0; i < capacity; i++) {
			size = size + size(table[i], 0);
		}
		return size;
	}

	private int size(Entry e, int i) {
		if (e == null)
			return i;
		else {
			return size(e.next, i+1);
		}
	}*/
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public V get(Object arg0) {
		@SuppressWarnings("unchecked")
		K key = (K) arg0;
		for (int i = 0; i < capacity; i++) {
			if (find(i, key) != null) {
				return find(i, key).getValue();
			}
		}
		return null;
	}

	private int index(K key) {
		return Math.abs(key.hashCode() % capacity);
	}

	private Entry<K, V> find(int index, K key) {
		Entry<K, V> temp = table[index];
		while (temp != null) {
			if (key.equals(temp.getKey())) {
				return temp;
			}
			temp = temp.next;
		}
		return null;
	}

	private static class Entry<K, V> implements Map.Entry<K, V> {
		private K key;
		private V value;
		private Entry<K, V> next;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			this.value = value;
			return value;
		}

		public String toString() {
			return key + "=" + value;
		}
	}
}