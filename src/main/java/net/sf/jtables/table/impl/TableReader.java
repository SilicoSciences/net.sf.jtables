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

package net.sf.jtables.table.impl;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import net.sf.jtables.table.Table;
import net.sf.kerner.utils.io.buffered.impl.BufferedStringReader;
import net.sf.kerner.utils.io.impl.AbstractGenericReader;

public class TableReader extends AbstractGenericReader<Table<String>> {

	public final static String DEFAULT_DELIM = "\t";
	protected final boolean header;
	protected final boolean rowIds;
	protected final String delim;

	final StringTable result = new StringTable();
	final Set<String> ids = new LinkedHashSet<String>();

	public TableReader(boolean header, boolean rowIds, String delim) {
		this.delim = delim;
		this.header = header;
		this.rowIds = rowIds;
	}

	public TableReader(boolean header, String delim) {
		this(header, false, delim);
	}

	public TableReader(String delim) {
		this(false, false, delim);
	}

	public TableReader() {
		this(false, false, DEFAULT_DELIM);
	}

	protected List<? extends String> handleLine(String line) {
		final Scanner scanner = new Scanner(line);
		scanner.useDelimiter(delim);
		final List<String> list = new ArrayList<String>();
		boolean first = true;
		while (scanner.hasNext()) {
			final String s = scanner.next();
			if (rowIds && first) {
				ids.add(s);
				first = false;
			} else
				list.add(s);
		}
		return list;
	}

	protected Set<String> getIds(String line) {
		final Scanner scanner = new Scanner(line);
		scanner.useDelimiter(delim);
		final Set<String> list = new LinkedHashSet<String>();
		while (scanner.hasNext()) {
			final String s = scanner.next();

			list.add(s);

		}
		return list;
	}

	public Table<String> read(Reader reader) throws IOException {
		final BufferedStringReader reader2 = new BufferedStringReader(reader);
		boolean firstLine = true;
		String line;
		while ((line = reader2.nextLine()) != null) {
			if (header && firstLine) {
				final Set<String> ids = getIds(line);
				result.setColumnIdentifier(ids);
				firstLine = false;
			} else {
				final List<? extends String> row = handleLine(line);
				if(row.size() != 0)
				result.addRow(row);
			}
		}
		result.setRowIdentifier(ids);
		return result;
	}

}
