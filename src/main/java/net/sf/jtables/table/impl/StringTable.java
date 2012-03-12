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

import java.util.ArrayList;
import java.util.List;

import net.sf.jtables.table.Column;
import net.sf.jtables.table.Row;

/**
 * 
 * An {@link net.sf.jtables.table.AnnotatedMutableTable AnnotatedMutableTable}
 * with {@link java.lang.String String} elements.
 * 
 * 
 * @author <a href="mailto:alex.kerner.24@googlemail.com">Alexander Kerner</a>
 * @version 2010-12-25
 * 
 */
public class StringTable extends AnnotatedMutableTableImpl<String> {

	public StringTable() {
		super();
	}

	public StringTable(List<Row<String>> rows) {
		super(rows);
	}

	@Override
	public TableImpl<String> clone() throws CloneNotSupportedException {
		final List<Row<String>> rows = new ArrayList<Row<String>>();
		for (Row<String> row : this.rows) {
			final Column<String> columns = new ColumnImpl<String>();
			for (String element : row) {
				columns.add(element);
			}
			rows.add(columns);
		}
		return new StringTable(rows);
	}

	public List<Column<String>> getColumns(String idPattern) {
		final List<Column<String>> result = new ArrayList<Column<String>>();

		for (Object s : getColumnIdentifier()) {
			if (s.toString().matches(idPattern)) {
				result.add(getColumn(s));
			}
		}

		return result;
	}

	public List<Row<String>> getRows(String idPattern) {
		final List<Row<String>> result = new ArrayList<Row<String>>();

		for (Object s : getRowIdentifier()) {
			if (s.toString().matches(idPattern)) {
				result.add(getRow(s));
			}
		}

		return result;
	}
}
