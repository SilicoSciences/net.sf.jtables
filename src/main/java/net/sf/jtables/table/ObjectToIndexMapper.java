package net.sf.jtables.table;

import java.util.Collection;

/**
 * 
 * 
 * An {@code ObjectToIndexMapper} establishes a mapping between any set of objects and a {@link java.util.List}'s integer indices.
 * <p>
 * Given a set of objects, e.g. following set of strings:
 * </p>
 * <pre>
 * {one, two, three, four}
 * </pre>
 * And a given list with following elements:
 * <pre>
 * {blue, red, black, green}
 * </pre> 
 * Access to this list's elements can be more intuitive accessing the elements by an object-based index:
 * <pre>
 * List colors = new ArrayList(){{blue, red, black, green}};
 * ObjectToIndexMapper mapper = new ObjectToIndexMapperImpl(colors);
 * assertEquals(blue, colors.get(mapper.get(one)));
 * ...
 * </pre>
 * <p>
 * <b>Example:</b>
 * 
 * <pre>
 * TODO example
 * </pre>
 * 
 * </p>
 * 
 * @author <a href="mailto:alex.kerner.24@googlemail.com">Alexander Kerner</a>
 * @version 2010-10-04
 * @see java.util.Collection
 * @see java.util.List
 * @see java.util.Map
 * 
 */
public interface ObjectToIndexMapper {
	
	boolean containsKey(Object key);
	
	boolean containsValue(int index);

	int get(Object key);

	Object getValue(int index);
	
	Collection<? extends Integer> values();

	Collection<? extends Object> keySet();

}
