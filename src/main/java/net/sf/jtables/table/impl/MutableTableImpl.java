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

import java.util.List;

import net.sf.jtables.table.Column;
import net.sf.jtables.table.MutableTable;
import net.sf.jtables.table.Row;
import net.sf.kerner.utils.collections.list.impl.ListUtils;

/**
 * 
 * Default implementation for {@link MutableTable}.
 * 
 *
 * @author <a href="mailto:alex.kerner.24@googlemail.com">Alexander Kerner</a>
 * @version 2012-01-12
 *
 * @param <T> type of elements in this {@code Table}
 */
public class MutableTableImpl<T> extends TableImpl<T> implements
		MutableTable<T> {

	public MutableTableImpl(List<Row<T>> rows) {
		super(rows);
	}

	public MutableTableImpl() {

	}

	public void setRow(int index, Row<T> elements) {
		super.rows.set(index, elements);
	}

	public void setColumn(int index, Column<T> elements) {
		checkColumnIndex(index);
		net.sf.kerner.utils.Utils.checkForNull(elements);

		// assert that we have enough rows to set whole column
		// fill number of rows to fit number of elements in column
		ListUtils.fill(super.rows, elements.size(), new RowImpl<T>());

		for (int i = 0; i < elements.size(); i++) {
			final Row<T> row = new RowImpl<T>(getRow(i));

			// assert that row is at least index+1 long, so it can take the
			// column element
			ListUtils.fill(row, index + 1, null);

			// finally set element
			row.set(index, elements.get(i));

			// replace row with new one
			setRow(i, row);
		}
	}

	public void addColumn(int index, Column<T> elements) {
		checkColumnIndex(index);
		net.sf.kerner.utils.Utils.checkForNull(elements);

		// assert that we have enough rows to set whole column
		// fill number of rows to fit number of elements in column
		ListUtils.fill(super.rows, elements.size(), new RowImpl<T>());

		for (int i = 0; i < elements.size(); i++) {
			final Row<T> row = new RowImpl<T>(getRow(i));

			// assert that row is at least index long, so it can take the column
			// element
			ListUtils.fill(row, index, null);

			// finally set element
			row.add(index, elements.get(i));

			// replace row with new one
			setRow(i, row);
		}
	}

	public void addColumn(Column<T> elements) {
		net.sf.kerner.utils.Utils.checkForNull(elements);

		// assert that we have enough rows to set whole column
		// fill number of rows to fit number of elements in column
		ListUtils.fill(super.rows, elements.size(), new RowImpl<T>());

		for (int i = 0; i < elements.size(); i++) {
			final Row<T> row = new RowImpl<T>(getRow(i));

			// since we are appending, assert that all row are maxRowSize()
			// long, so it can take the column element
			ListUtils.fill(row, getMaxRowSize() - 1, null);

			// finally set element
			row.add(elements.get(i));

			// replace row with new one
			setRow(i, row);
		}
	}

	public void set(int i, int j, T element) {
		// we are empty
		if (getNumberOfRows() == 0)
			addRow(new RowImpl<T>());
		
		fillRows(j + 1, null);
		fillColumns(i + 1, null);
		checkRowIndex(i);
		checkColumnIndex(j);
		
		final Row<T> row2 = new RowImpl<T>(getRow(i));
		row2.set(j, element);
		setRow(i, row2);
	}

	public void clear() {
		super.rows.clear();
	}

	public void addRow(Row<T> elements) {
		super.rows.add(elements);
	}

	public void addRow(int index, Row<T> elements) {
		super.rows.add(index, elements);
	}

	public void fillRows(int index,final T element) {
		if(index < 1)
			throw new IllegalArgumentException();

		// assert we have at least one row
		if (getNumberOfRows() == 0)
			addRow(new RowImpl<T>() {
				private static final long serialVersionUID = 8771525210955142646L;
				{
					add(element);
				}
			});

		final int end = getNumberOfRows();

		for (int i = 0; i < end; i++) {
			final Row<T> rr = new RowImpl<T>(getRow(i));
			ListUtils.fill(rr, index, element);
			setRow(i, rr);
		}
	}

	public void fillColumns(int index, final T element) {
		if(index < 1)
			throw new IllegalArgumentException();
		
		// assert we have at least one row
		if (getNumberOfRows() == 0)
			addRow(new RowImpl<T>() {
				private static final long serialVersionUID = 3543285099623756394L;
				{
					add(element);
				}
			});

		final int end = getNumberOfColumns();
		for (int i = 0; i < end; i++) {
			final Column<T> rr = new ColumnImpl<T>(getColumn(i));

			ListUtils.fill(rr, index, element);

			setColumn(i, rr);
		}
	}

	public void fill(int i, T element) {
		fillRows(i, element);
		fillColumns(i, element);
	}

	public void fillAndSet(int i, int j, T elementToFill, T elementToSet) {
		fillRows(j+1, elementToFill); // rows have to be filled up to column index
		fillColumns(i+1, elementToFill); // columns have to be filled up to row index
		set(i, j, elementToSet);
	}

	public void removeRow(int index) {
		checkRowIndex(index);
		super.rows.remove(index);
	}

	public void removeColumn(int index) {
		checkColumnIndex(index);
		
		// iterate over rows and remove element at position index if there is such an element
		for(List<? extends T> t : super.rows){
			if(t.size() > index){
				t.remove(index);
			}
		}
	}
	
	public void remove(int i, int j) {
		checkColumnIndex(j);
		getRow(i).remove(j);
	}
}
