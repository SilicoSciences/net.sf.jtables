package net.sf.jtables.table;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Utils {
	
	public static final String NEW_LINE_STRING = System.getProperty("line.separator");

	private Utils(){
		// singleton
	}
	
	/**
	 * 
	 * 
	 * Fill given {@link java.util.List}. That is to add
	 * 
	 * <pre>
	 * <code>numElements-list.size()-1</code>
	 * </pre>
	 * 
	 * elements to this {@code List}, resulting in a new list size of
	 * {@code numElements}. If <code>numElements < list.size()</code>, this
	 * method does nothing.
	 * 
	 * @param <E>
	 *            type of element that is used for filling this {@code List}
	 * @param list
	 *            the {@code List} that is filled
	 * @param numElements
	 *            new {@link java.util.List#size()} of this {@code List}
	 * @param e
	 *            element which is used to fill the list
	 * @see java.util.List
	 */
	public static <E> void fill(List<E> list, int numElements, E e) {
		if (numElements < list.size())
			return;
		final int iterations = numElements - list.size() - 1;
		for (int i = 0; i <= iterations; i++) {
			list.add(e);
		}
	}
	
	public static <M, V> void initMapWithValues(Map<M, V> map,
			Collection<? extends M> keys, Collection<? extends V> values,
			boolean clean) {
		if (clean)
			map.clear();
		final Iterator<? extends V> valIt = values.iterator();
		for (M m : keys) {
			if (valIt.hasNext())
				map.put(m, valIt.next());
		}
	}

	public static <M, V> void initMapWithValues(Map<M, V> map,
			Collection<? extends M> keys, Collection<? extends V> values) {
		initMapWithValues(map, keys, values, true);
	}
}
