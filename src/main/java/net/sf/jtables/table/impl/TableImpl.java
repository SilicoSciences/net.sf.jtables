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
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import net.sf.jtables.table.Column;
import net.sf.jtables.table.Row;
import net.sf.jtables.table.Table;
import net.sf.kerner.utils.io.IOUtils;

/**
 * 
 * Default implementation for {@link Table}. 
 * 
 *
 * @author <a href="mailto:alex.kerner.24@googlemail.com">Alexander Kerner</a>
 * @version 2012-01-22
 *
 * @param <T> type of elements in this {@code Table}
 */
public class TableImpl<T> implements Table<T> {

	// Field //

	/**
	 * 
	 */
	protected final List<Row<T>> rows = new ArrayList<Row<T>>();

	// Constructor //

	public TableImpl() {
	}

	public TableImpl(List<Row<T>> rows) {
		synchronized (rows) {
			this.rows.addAll(rows);
		}
	}

	// Private //

	// Protected //

	protected void checkRowIndex(int index) {
		if (index < 0)
			throw new IllegalArgumentException("row index must be >= 0");
		if (index >= getNumberOfRows())
			throw new NoSuchElementException("no element for row index ["
					+ index + "]");
	}

	protected void checkColumnIndex(int index) {
		if (index < 0)
			throw new IllegalArgumentException("column index must be >= 0");
		if (index >= getNumberOfColumns())
			throw new NoSuchElementException("no element for column index ["
					+ index + "]");
	}

	// Public //

	//

	// Override //

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		final Iterator<?> it = getRowIterator();
		while (it.hasNext()) {
			sb.append(it.next());
			if (it.hasNext())
				sb.append(IOUtils.NEW_LINE_STRING);
		}
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rows == null) ? 0 : rows.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof TableImpl))
			return false;
		TableImpl<?> other = (TableImpl<?>) obj;
		if (rows == null) {
			if (other.rows != null)
				return false;
		} else if (!rows.equals(other.rows))
			return false;
		return true;
	}

	/**
	 * Retrieve an iterator over all elements.
	 */
	public synchronized Iterator<T> iterator() {
		return getAllElements().iterator();
	}

	/**
	 * 
	 */
	public Row<T> getRow(int index) {
		checkRowIndex(index);
		// TODO defensive copying since we are immutable
		return rows.get(index);
	}

	/**
	 * 
	 */
	public List<Row<T>> getRows() {
		final List<Row<T>> result = new ArrayList<Row<T>>();
		for (int i = 0; i < getNumberOfRows(); i++) {
			// TODO defensive copying since we are immutable
			result.add(new RowImpl<T>(getRow(i)));
		}
		return result;
	}

	/**
	 * 
	 */
	public Column<T> getColumn(int index) {
		checkColumnIndex(index);
		final Column<T> result = new ColumnImpl<T>();
		for (Row<T> l : rows) {
			if (index < l.size()) {
				System.out.println("add element " + l.getElement(index).getIdentifier());
				result.add(l.getElement(index));
			} else {
				// log.debug("row at index [" + index + "] has no column");
			}
		}
		return new ColumnImpl<T>(result);
	}

	/**
	 * 
	 */
	public List<Column<T>> getColumns() {
		final List<Column<T>> result = new ArrayList<Column<T>>();
		for (int i = 0; i < getNumberOfColumns(); i++) {
			// defensive copying since we are immutable
			result.add(new ColumnImpl<T>(
					getColumn(i)));
		}
		return result;
	}

	/**
	 * 
	 */
	public T get(int i, int j) {
		checkColumnIndex(j);
		return getRow(i).get(j);
	}

	/**
	 * 
	 */
	public int getRowSize(int index) {
		return getRow(index).size();
	}

	/**
	 * 
	 */
	public int getColumnSize(int index) {
		return getColumn(index).size();
	}

	/**
	 * 
	 */
	public int getMaxRowSize() {
		int result = 0;
		for (List<?> r : rows) {
			if (r.size() > result)
				result = r.size();
		}
		return result;
	}

	/**
	 * 
	 */
	public int getMaxColumnSize() {
		int result = 0;
		for (List<? extends T> r : getColumns()) {
			if (r.size() > result)
				result = r.size();
		}
		return result;
	}

	/**
	 * 
	 */
	public int getNumberOfRows() {
		return rows.size();
	}

	/**
	 * 
	 */
	public int getNumberOfColumns() {
		return getMaxRowSize();
	}

	/**
	 * 
	 */
	public boolean contains(T element) {
		return getAllElements().contains(element);
	}

	/**
	 * 
	 */
	public List<T> getAllElements() {
		final ArrayList<T> result = new ArrayList<T>();
	
		for (int i = 0; i < getNumberOfRows(); i++) {
			
			final List<T> row = getRow(i);
			// defensive copying since we are immutable
			for (T t : row) {
				// defensive copying since we are immutable
				result.add(t);
			}
		}
		return result;
	}

	/**
	 * 
	 */
	public Iterator<Row<T>> getRowIterator() {
		return new ArrayList<Row<T>>(getRows()).iterator();
	}

	/**
	 * 
	 */
	public Iterator<Column<T>> getColumnIterator() {
		return new ArrayList<Column<T>>(getColumns()).iterator();
	}

}
