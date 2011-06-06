package net.sf.jtables.table;

import java.io.IOException;
import java.util.List;

import net.sf.kerner.utils.io.buffered.AbstractIOIterator;
import net.sf.kerner.utils.io.buffered.IOIterable;

/**
 * 
 * A {@code TableReader} reads an {@link AnnotatedTable} from an input source.
 * </p> It does so by extending
 * {@link net.sf.kerner.utils.io.buffered.IOIterable IOIterable} in oder to
 * provide possibility to iterate over a table's rows.
 * </p>
 * Via {@link TableReader#readAll()} it is also possible, to read in a whole table at once.
 * 
 * @see IOIterable
 * @see AnnotatedTable
 * @see StringTable
 * @see IntegerTable
 * @see DoubleTable
 * 
 * @author <a href="mailto:alex.kerner.24@googlemail.com">Alexander Kerner</a>
 * @version 2011-06-06
 * 
 * @param <T> type of elements in table
 */
public interface TableReader<T> extends IOIterable<List<? extends T>> {

	/**
	 * Close this reader.
	 * 
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
