package net.sf.jtables.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class TableImpl<T> implements Table<T> {

	// Field //

	/**
	 * 
	 */
	protected final List<List<? extends T>> rows = new ArrayList<List<? extends T>>();

	// Constructor //

	/**
	 * 
	 * TODO description
	 * 
	 */
	public TableImpl() {
	}

	public TableImpl(List<? extends List<? extends T>> rows) {
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
				sb.append(Utils.NEW_LINE_STRING);
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
	 * 
	 */
	public synchronized Iterator<T> iterator() {
		return getAllElements().iterator();
	}

	/**
	 * 
	 */
	public List<T> getRow(int index) {
		checkRowIndex(index);
		// defensive copying since we are immutable
		return Collections.unmodifiableList(rows.get(index));
	}

	/**
	 * 
	 */
	public List<List<T>> getRows() {
		final List<List<T>> result = new ArrayList<List<T>>();
		for (int i = 0; i < getNumberOfRows(); i++) {
			// defensive copying since we are immutable
			result.add(Collections
					.unmodifiableList(new ArrayList<T>(getRow(i))));
		}
		return result;
	}

	/**
	 * 
	 */
	public List<T> getColumn(int index) {
		checkColumnIndex(index);
		final List<T> result = new ArrayList<T>();
		for (List<? extends T> l : rows) {
			if (index < l.size()) {
				result.add(l.get(index));
			} else {
				// log.debug("row at index [" + index + "] has no column");
			}
		}
		return Collections.unmodifiableList(new ArrayList<T>(result));
	}

	/**
	 * 
	 */
	public List<List<T>> getColumns() {
		final List<List<T>> result = new ArrayList<List<T>>();
		for (int i = 0; i < getNumberOfColumns(); i++) {
			// defensive copying since we are immutable
			result.add(Collections.unmodifiableList(new ArrayList<T>(
					getColumn(i))));
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
	public Iterator<List<T>> getRowIterator() {
		return new ArrayList<List<T>>(getRows()).iterator();
	}

	/**
	 * 
	 */
	public Iterator<List<T>> getColumnIterator() {
		return new ArrayList<List<T>>(getColumns()).iterator();
	}

}
