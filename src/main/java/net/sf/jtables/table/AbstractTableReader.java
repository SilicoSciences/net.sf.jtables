/**********************************************************************
Copyright (c) 2009-2010 Alexander Kerner. All rights reserved.
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

package net.sf.jtables.table;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import net.sf.kerner.utils.io.buffered.impl.BufferedStringReader;
import net.sf.kerner.utils.io.impl.AbstractGenericReader;

/**
 * 
 * An {@code AbstractTableReader} reads a {@link AnnotatedMutableTable} with
 * elements of type {@code T} from a
 * <ul>
 * <li>
 * {@link java.io.File File}</li>
 * <li>
 * {@link java.io.InputStream InputStream}</li>
 * <li>
 * {@link java.io.Reader Reader}</li>
 * </ul>
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
 * @version 2010-12-05
 * 
 * @param <T>
 *            type of elements in table that is read
 */
public abstract class AbstractTableReader<T> extends
		AbstractGenericReader<AnnotatedMutableTable<T>> {

	public final static String DEFAULT_DELIM = "\t";

	protected final boolean columnIds;

	protected final boolean rowIds;

	protected final String delim;

	private final Set<String> rowids = new LinkedHashSet<String>();

	public AbstractTableReader(boolean columnIds, boolean rowIds, String delim) {
		this.columnIds = columnIds;
		this.rowIds = rowIds;
		if (delim == null)
			this.delim = DEFAULT_DELIM;
		else
			this.delim = delim;
	}

	public AbstractTableReader(boolean columnIds, boolean rowIds) {
		this(columnIds, rowIds, null);
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

	protected List<T> handleLine(String line) {
		final Scanner scanner = new Scanner(line);
		scanner.useDelimiter(delim);
		final List<T> list = new ArrayList<T>();
		boolean first = true;
		while (scanner.hasNext()) {
			final String s = scanner.next();
			if (rowIds && first) {
				rowids.add(s);
				first = false;
			} else
				list.add(parse(s));
		}
		return list;
	}

	/**
	 * Read a {@code AnnotatedMutableTable} from {@code reader}.
	 */
	public AnnotatedMutableTable<T> read(Reader reader) throws IOException {
		final BufferedStringReader reader2 = new BufferedStringReader(reader);
		final AnnotatedMutableTable<T> result = getInstance();
		boolean firstLine = true;
		String line = null;
		while ((line = reader2.nextLine()) != null) {
			if (columnIds && firstLine) {
				final Set<String> colids = getColIds(line);
				result.setColumnIdentifier(colids);
				firstLine = false;
			} else {
				final List<T> row = handleLine(line);
				if (row.size() != 0)
					result.addRow(row);
			}
		}
		result.setRowIdentifier(rowids);
		return result;
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
	 * @param s {@link java.lang.String String} to parse from
	 * @return object of type {@code T} that was parsed
	 * @throws NumberFormatException if parsing fails
	 */
	protected abstract T parse(String s) throws NumberFormatException;

}
