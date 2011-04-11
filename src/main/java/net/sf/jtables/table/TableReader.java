package net.sf.jtables.table;

import java.io.IOException;
import java.util.List;

import net.sf.kerner.utils.io.buffered.AbstractIOIterator;
import net.sf.kerner.utils.io.buffered.IOIterable;

/**
 * 
 * TODO description
 * 
 * <p>
 * <b>Example:</b><br>
 *
 * </p>
 * <p>
 * <pre>
 * TODO example
 * </pre>
 * </p>
 *
 * @author <a href="mailto:alex.kerner.24@googlemail.com">Alexander Kerner</a>
 * @version 2011-04-11
 *
 * @param <T>
 */
public interface TableReader<T> extends IOIterable<List<? extends T>>{
	
	/**
	 * Close this reader.
	 * @see java.io.Reader#close()
	 *
	 */
	void close();
	
	/**
	 * 
	 * Read a {@link Table} at once.
	 *
	 * @return
	 * @throws IOException
	 */
	AnnotatedTable<T> readAll() throws IOException;
	
	/**
	 * 
	 */
	AbstractIOIterator<List<? extends T>> getIterator() throws IOException;

}
