/**********************************************************************
Copyright (c) 2009-2012 Alexander Kerner. All rights reserved.
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 ***********************************************************************/

package net.sf.jtables.table.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

import net.sf.jtables.table.AnnotatedMutableTable;
import net.sf.jtables.table.AnnotatedTable;
import net.sf.jtables.table.Row;
import net.sf.jtables.table.TableReader;
import net.sf.kerner.utils.io.buffered.AbstractIOIterator;
import net.sf.kerner.utils.io.buffered.IOIterator;

/**
 * 
 * Prototype implementation for {@link TableReader}.
 * 
 * <p>
 * <b>Example:</b><br>
 * 
 * </p>
 * <p>
 * 
 * <pre>
 * TODO example
 * </pre>
 * 
 * </p>
 * 
 * @author <a href="mailto:alex.kerner.24@googlemail.com">Alexander Kerner</a>
 * @version 2012-01-12
 * 
 * @param <T>
 *            type of elements in {@code Table}
 */
public abstract class AbstractTableReader<T> extends AbstractIOIterator<Row<T>> implements TableReader<T> {

	/**
	 * Default column delimiter (tab).
	 */
	public final static String DEFAULT_DELIM = "\t";

	/**
	 * Does the table have column headers?
	 */
	protected final boolean colsB;

	/**
	 * Does the table have row headers?
	 */
	protected final boolean rowsB;

	/**
	 * column delimiter.
	 */
	protected final String delim;

	/**
	 * row headers.
	 */
//	protected final Set<String> rowHeaders = new LinkedHashSet<String>();

	/**
	 * column headers.
	 */
	protected final Set<String> columnHeaders = new LinkedHashSet<String>();

	/**
	 * currently reading first line?
	 */
	private volatile boolean firstLine = true;

	/**
	 * 
	 * Create a new {@code AbstractTableReader}.
	 * 
	 * @param reader
	 *            {@link Reader} from which table is read
	 * @param columnIds
	 *            {@code true}, if columns have headers; {@code false} otherwise
	 * @param rowIds
	 *            {@code true}, if rows have headers; {@code false} otherwise
	 * @param delim
	 *            column delimiter to use
	 * @throws IOException
	 *             if anything goes wrong
	 */
	public AbstractTableReader(Reader reader, boolean columnIds, boolean rowIds, String delim) throws IOException {
		super(reader);
		this.colsB = columnIds;
		this.rowsB = rowIds;
		if (delim == null)
			this.delim = DEFAULT_DELIM;
		else
			this.delim = delim;
		read();
	}

	/**
	 * 
	 * Create a new {@code AbstractTableReader}.
	 * 
	 * @param stream
	 *            {@link InputStream} from which table is read
	 * @param columnIds
	 *            {@code true}, if columns have headers; {@code false} otherwise
	 * @param rowIds
	 *            {@code true}, if rows have headers; {@code false} otherwise
	 * @param delim
	 *            column delimiter to use
	 * @throws IOException
	 *             if anything goes wrong
	 */
	public AbstractTableReader(InputStream stream, boolean columnIds, boolean rowIds, String delim) throws IOException {
		super(stream);
		this.colsB = columnIds;
		this.rowsB = rowIds;
		if (delim == null)
			this.delim = DEFAULT_DELIM;
		else
			this.delim = delim;
		read();
	}

	/**
	 * 
	 * Create a new {@code AbstractTableReader}.
	 * 
	 * @param file
	 *            {@link File} from which table is read
	 * @param columnIds
	 *            {@code true}, if columns have headers; {@code false} otherwise
	 * @param rowIds
	 *            {@code true}, if rows have headers; {@code false} otherwise
	 * @param delim
	 *            column delimiter to use
	 * @throws IOException
	 *             if anything goes wrong
	 */
	public AbstractTableReader(File file, boolean columnIds, boolean rowIds, String delim) throws IOException {
		this(new FileInputStream(file), columnIds, rowIds, delim);
	}

	/**
	 * 
	 * Create a new {@code AbstractTableReader}.
	 * 
	 * @param file
	 *            {@link File} from which table is read
	 * @param columnIds
	 *            {@code true}, if columns have headers; {@code false} otherwise
	 * @param rowIds
	 *            {@code true}, if rows have headers; {@code false} otherwise
	 *            
	 * @throws IOException
	 *             if anything goes wrong
	 */
	public AbstractTableReader(File file, boolean columnIds, boolean rowIds) throws IOException {
		this(file, columnIds, rowIds, null);
	}

	/**
	 * 
	 * Create a new {@code AbstractTableReader}.
	 * 
	 * @param stream
	 *            {@link InputStream} from which table is read
	 * @param columnIds
	 *            {@code true}, if columns have headers; {@code false} otherwise
	 * @param rowIds
	 *            {@code true}, if rows have headers; {@code false} otherwise
	 *            
	 * @throws IOException
	 *             if anything goes wrong
	 */
	public AbstractTableReader(InputStream stream, boolean columnIds, boolean rowIds) throws IOException {
		this(stream, columnIds, rowIds, null);
	}

	/**
	 * 
	 * Create a new {@code AbstractTableReader}.
	 * 
	 * @param reader
	 *            {@link Reader} from which table is read
	 * @param columnIds
	 *            {@code true}, if columns have headers; {@code false} otherwise
	 * @param rowIds
	 *            {@code true}, if rows have headers; {@code false} otherwise
	 *            
	 * @throws IOException
	 *             if anything goes wrong
	 */
	public AbstractTableReader(Reader reader, boolean columnIds, boolean rowIds) throws IOException {
		this(reader, columnIds, rowIds, null);
	}

	/**
	 * 
	 * Extract column headers from first line.
	 *
	 * @param line {@code String} that contains column headers
	 * @return {@link Set} of column headers
	 */
	protected Set<String> getColHeaders(String line) {
		final Scanner scanner = new Scanner(line);
		scanner.useDelimiter(delim);
		final Set<String> list = new LinkedHashSet<String>();
		while (scanner.hasNext()) {
			final String s = scanner.next();
			list.add(s);
		}
		return list;
	}

	/**
	 * 
	 */
	@Override
	protected Row<T> doRead() throws IOException {
		String line = reader.readLine();
		if (line == null)
			return null;
		if (colsB && firstLine) {
			columnHeaders.addAll(getColHeaders(line));
			firstLine = false;
			// column headers read, continue to next line
			line = reader.readLine();
			if (line == null)
				return null;
		}

		final Scanner scanner = new Scanner(line);
		scanner.useDelimiter(delim);
		final Row<T> result = new RowImpl<T>();

		// first column (row headers)?
		boolean first = true;

		while (scanner.hasNext()) {
			final String s = scanner.next();
			// if(StringUtils.emptyString(s)){
			// continue;
			// }
			if (rowsB && first) {
				result.setIdentifier(s);
				first = false;
			} else {
				final Iterator<String> it = columnHeaders.iterator();
				String identifier = null;
				if(it.hasNext()){
					identifier = it.next();
					it.remove();
				}
				System.out.println("add new element to row: " + identifier + ", " + parse(s));
				result.add(new RowColumnElement<T>(identifier, parse(s)));
			}
		}

		if (result.isEmpty())
			return null;

		return result;
	}

	/**
	 * Read a {@code AnnotatedTable} at once.
	 */
	public AnnotatedTable<T> readAll() throws IOException {
		final AnnotatedMutableTable<T> result = getInstance();
		final IOIterator<Row<T>> it = getIterator();
		while (it.hasNext()) {
			final Row<T> next = it.next();
			// System.err.println("adding row " + next);
			result.addRow(next);
		}
		it.close();
		// System.err.println("cIds:" + result.getColumnIdentifier());
		// System.err.println("rIds:" + result.getRowIdentifier());
		return result;
	}

	public IOIterator<Row<T>> getIterator() throws IOException {
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
