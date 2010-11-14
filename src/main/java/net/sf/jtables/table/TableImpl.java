package net.sf.jtables.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import net.sf.kerner.commons.collection.ListFactory;
import net.sf.kerner.commons.collection.impl.ArrayListFactory;
import net.sf.kerner.commons.io.IOUtils;

public class TableImpl<T> implements Table<T> {

	// Field //

	/**
	 * 
	 */
	protected final List<List<? extends T>> rows = new ArrayList<List<? extends T>>();

	protected ListFactory<T> listFactory;

	protected final ListFactory<T> DEFAULT_LIST_FACTORY = new ArrayListFactory<T>();


	// Constructor //

	/**
	 * 
	 * TODO description
	 * 
	 */
	public TableImpl() {
		this.listFactory = DEFAULT_LIST_FACTORY;
	}
	
	public TableImpl(ListFactory<T> factory) {
		this.listFactory = factory;
	}

	public TableImpl(List<? extends List<? extends T>> rows) {
		synchronized (rows) {
			this.listFactory = DEFAULT_LIST_FACTORY;
			this.rows.addAll(rows);
		}
	}
	
	public TableImpl(List<? extends List<? extends T>> rows, ListFactory<T> factory) {
		synchronized (rows) {
			this.listFactory = factory;
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
	 * 
	 */
	public synchronized Iterator<T> iterator() {
		return getAllElements().iterator();
	}

	public List<T> getRow(int index) {
		checkRowIndex(index);
		// defensive copying since we are immutable
		return Collections.unmodifiableList(listFactory.createCollection(rows.get(index)));
	}

	public List<List<T>> getRows() {
		final ArrayList<List<T>> result = new ArrayList<List<T>>();
		result.addAll(getAllElements());
		return rows;
	}

	public List<T> getColumn(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<List<T>> getColumns() {
		// TODO Auto-generated method stub
		return null;
	}

	public T get(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getRowSize(int index) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getColumnSize(int index) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getMaxRowSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getMaxColumnSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getNumberOfRows() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getNumberOfColumns() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean contains(T element) {
		// TODO Auto-generated method stub
		return false;
	}

	public Collection<T> getAllElements() {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterator<T> getRowIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterator<T> getColumnIterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
