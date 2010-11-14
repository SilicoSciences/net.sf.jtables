package net.sf.jtables.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class TableImpl<T> implements Table<T> {

	// Field //

	/**
	 * 
	 */
	protected final List<List<? extends T>> rows = new ArrayList<List<? extends T>>();
	
	public TableImpl() {
		
	}
	
	public TableImpl(List<List<? extends T>> rows) {
		this.rows.addAll(rows);
	}

	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> getRow(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<List<T>> getRows() {
		// TODO Auto-generated method stub
		return null;
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
