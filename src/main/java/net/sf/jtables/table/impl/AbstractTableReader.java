/**********************************************************************
Copyright (c) 2009-2011 Alexander Kerner. All rights reserved.
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
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import net.sf.jtables.table.AnnotatedMutableTable;
import net.sf.jtables.table.AnnotatedTable;
import net.sf.jtables.table.TableReader;
import net.sf.kerner.utils.StringUtils;
import net.sf.kerner.utils.io.buffered.AbstractIOIterator;
import net.sf.kerner.utils.io.buffered.IOIterator;

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
 * @version 2011-08-28
 *
 * @param <T>
 */
public abstract class AbstractTableReader<T> extends AbstractIOIterator<List<T>>
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
		this(new FileInputStream(file), columnIds, rowIds, delim);
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
		final Scanner scanner = new Scanner(line);
		scanner.useDelimiter(delim);
		final Set<String> list = new LinkedHashSet<String>();
		while (scanner.hasNext()) {
			final String s = scanner.next();
			list.add(s);
		}
		return list;
	}
	
	@Override
	protected List<T> doRead() throws IOException {
		String line = reader.readLine();
		if (line == null)
			return null;
		if (colsB && firstLine) {
			colids.addAll(getColIds(line));
			firstLine = false;
			// column ids read, continue to next line
			line = reader.readLine();
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
		final AnnotatedMutableTable<T> result = getInstance();
		final IOIterator<List<T>> it = getIterator();
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

	public IOIterator<List<T>> getIterator() throws IOException {
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
