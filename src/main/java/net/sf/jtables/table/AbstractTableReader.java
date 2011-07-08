package net.sf.jtables.table;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import net.sf.kerner.utils.StringUtils;
import net.sf.kerner.utils.io.buffered.AbstractIOIterator;

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
public abstract class AbstractTableReader<T> extends AbstractIOIterator<List<? extends T>>
		implements TableReader<T> {
	
	public final static String DEFAULT_DELIM = "\t";

	protected final boolean colsB;

	protected final boolean rowsB;

	protected final String delim;

	protected final Set<String> rowids = new LinkedHashSet<String>();

	protected final Set<String> colids = new LinkedHashSet<String>();

	private volatile boolean firstLine = true;

	public AbstractTableReader(Reader reader, boolean columnIds,
			boolean rowIds, String delim) throws IOException {
		super(reader);
		this.colsB = columnIds;
		this.rowsB = rowIds;
		if (delim == null)
			this.delim = DEFAULT_DELIM;
		else
			this.delim = delim;
		read();
	}

	public AbstractTableReader(InputStream stream, boolean columnIds,
			boolean rowIds, String delim) throws IOException {
		super(stream);
		this.colsB = columnIds;
		this.rowsB = rowIds;
		if (delim == null)
			this.delim = DEFAULT_DELIM;
		else
			this.delim = delim;
		read();
	}

	public AbstractTableReader(File file, boolean columnIds, boolean rowIds,
			String delim) throws IOException {
		this(new FileInputStream(file), columnIds, rowIds, null);
	}

	public AbstractTableReader(File file, boolean columnIds, boolean rowIds)
			throws IOException {
		this(file, columnIds, rowIds, null);
	}

	public AbstractTableReader(InputStream stream, boolean columnIds,
			boolean rowIds) throws IOException {
		this(stream, columnIds, rowIds, null);
	}

	public AbstractTableReader(Reader reader, boolean columnIds, boolean rowIds) throws IOException {
		this(reader, columnIds, rowIds, null);
	}

	protected Set<String> getColIds(String line) {
//		System.err.println("extractring column identifiers");
		final Scanner scanner = new Scanner(line);
		scanner.useDelimiter(delim);
		final Set<String> list = new LinkedHashSet<String>();
		while (scanner.hasNext()) {
			final String s = scanner.next();
			list.add(s);
		}
//		System.err.println("got column identifiers: " + list);
		return list;
	}
	
	@Override
	protected List<? extends T> doRead() throws IOException {
		String line = reader.readLine();
//		System.err.println("read line [" + line + "]");
		if (line == null)
			return null;
		if (colsB && firstLine) {
			colids.addAll(getColIds(line));
			firstLine = false;
			// column ids read, continue to next line
			line = reader.readLine();
//			System.err.println("read line [" + line + "]");
			if (line == null)
				return null;
		}

		final Scanner scanner = new Scanner(line);
		scanner.useDelimiter(delim);
		final List<T> result = new ArrayList<T>();
		boolean first = true;

		while (scanner.hasNext()) {
			final String s = scanner.next();
			if(StringUtils.emptyString(s))
				continue;
			if (rowsB && first) {
				rowids.add(s);
				first = false;
			} else {
				result.add(parse(s));
			}
		}
		
		if(result.isEmpty())
			return null;
		return result;
	}

	/**
	 * Read a {@code AnnotatedTable} at once.
	 */
	public AnnotatedTable<T> readAll() throws IOException {
//		System.err.println("reading at once");
		final AnnotatedMutableTable<T> result = getInstance();
		final AbstractIOIterator<List<? extends T>> it = getIterator();
		while (it.hasNext()) {
			final List<? extends T> next = it.next();
//			System.err.println("adding row " + next);
			result.addRow(next);
		}
		it.close();
		result.setRowIdentifier(rowids);
		result.setColumnIdentifier(colids);
//		System.err.println("cIds:" + result.getColumnIdentifier());
//		System.err.println("rIds:" + result.getRowIdentifier());
		return result;
	}

	public AbstractIOIterator<List< ? extends T>> getIterator() throws IOException {
		return this;
	}

	/**
	 * 
	 * Get a new instance of {@link AnnotatedMutableTable}.
	 * 
	 * @return a new instance of {@link AnnotatedMutableTable}
	 */
	protected abstract AnnotatedMutableTable<T> getInstance();

	/**
	 * 
	 * Parse an object of type {@code T} from given string.
	 * 
	 * @param s
	 *            {@link java.lang.String String} to parse from
	 * @return object of type {@code T} that was parsed
	 * @throws NumberFormatException
	 *             if parsing fails
	 */
	protected abstract T parse(String s) throws NumberFormatException;

}
