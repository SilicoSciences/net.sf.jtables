/**
 * 
 */
package net.sf.jtables.table.impl;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

import net.sf.jtables.table.ObjectToIndexMapper;
import net.sf.jtables.table.Utils;

/**
 * 
 *
 * TODO description
 * 
 * <p>
 * <b>Example:</b>
 * <pre>
 * TODO example
 * </pre>
 * </p>
 *
 * @author <a href="mailto:alex.kerner.24@googlemail.com">Alexander Kerner</a>
 * @version 2010-10-04
 *
 */
public class ObjectToIndexMapperImpl implements ObjectToIndexMapper {

	// Field //
	
	/**
	 * 
	 */
	protected final Map<Object, Integer> map = new LinkedHashMap<Object, Integer>();
	
	// Constructor //

	/**
	 * 
	 */
	public ObjectToIndexMapperImpl(Set<? extends Object> keys) {
		final Collection<Integer> values = new LinkedHashSet<Integer>();
		for(int i=0; i<keys.size(); i++){
			values.add(Integer.valueOf(i));
		}
		Utils.initMapWithValues(map, keys, values);
	}

	// Private //
	
	// Protected //

	protected void checkIndex(Object key) {
		if (map.containsKey(key)) {
			// all good
		} else
			throw new NoSuchElementException("no element for row index [" + key
					+ "]");
	}
	
	
	// Public //
	
	// Override //
	
	@Override
	public String toString() {
		return map.toString();
	}
	
	// Implement //
	
	/**
	 * 
	 */
	public int get(Object key) {
		final Integer result = map.get(key);
		if (result != null) {
			return result;
		} else
			throw new NoSuchElementException("no element for row index [" + key
					+ "]");
	}

	public Object getValue(int index) {
		for (Entry<Object, Integer> e : map.entrySet()) {
			if (e.getValue().equals(Integer.valueOf(index)))
				return e.getKey();
		}
		throw new NoSuchElementException("no element for row index [" + index
				+ "]");
	}

	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	public boolean containsValue(int index) {
		return map.containsValue(Integer.valueOf(index));
	}

	public Collection<? extends Integer> values() {
		return map.values();
	}

	public Collection<? extends Object> keySet() {
		return map.keySet();
	}

}
