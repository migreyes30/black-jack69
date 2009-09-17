/* 
 ITESM-CEM
 Jorge Adrian Garcia Jimenez - 967292
 Uriel Cruz Pineda - 465722 
 Materia: Programacion y Estructura de Datos - Ariel Ortiz
 */

import java.awt.Component;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class SimpleMap<K, V> implements Map<K, V> {

	@SuppressWarnings("hiding")
	private class Entrada<K, V> implements Map.Entry<K, V> {

		private K key;
		private V value;

		public Entrada(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public V setValue(V value) {
			V old = this.value;
			this.value = value;
			return old;
		}

	}

	private List<Entrada<K, V>> info = new ArrayList<Entrada<K, V>>();

	public int size() {
		return info.size();
	}

	public boolean isEmpty() {
		return info.isEmpty();
	}

	public boolean containsKey(Object key) {
		return buscaIndice((K) key) != -1;
	}

	public boolean containsValue(Object value) {
		for (Entrada<K, V> entrada : info) {
			V v = entrada.getValue();
			if ((value == null ? v == null : value.equals(v))) {
				return true;
			}
		}
		return false;
	}

	public V get(Object key) {
		int indice = buscaIndice((K) key);
		if (indice == -1) {
			return null;
		} else {
			return info.get(indice).getValue();
		}
	}

	public V put(K key, V value) {
		int indice = buscaIndice(key);
		if (indice == -1) {
			info.add(new Entrada<K, V>(key, value));
			return null;
		} else {
			return info.get(indice).setValue(value);
		}
	}

	public V remove(Object key) {
		int indice = buscaIndice((K) key);
		if (indice == -1) {
			return null;
		} else {
			return info.remove(indice).getValue();
		}
	}

	public void putAll(Map<? extends K, ? extends V> t) {
		// TODO Auto-generated method stub

	}

	public void clear() {
		info.clear();

	}

	public Set<K> keySet() {
		LinkedHashSet<K> set = new LinkedHashSet<K>();
		for (Entrada<K, V> entrada : info) {
			set.add(entrada.getKey());
		}
		return set;
	}

	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	private int buscaIndice(K key) {
		int i = 0;
		for (Entrada<K, V> entrada : info) {
			K k = entrada.getKey();
			if ((key == null ? k == null : key.equals(k))) {
				return i;
			}
			i++;
		}
		return -1;
	}

}
