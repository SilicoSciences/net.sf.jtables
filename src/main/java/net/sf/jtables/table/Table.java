package net.sf.jtables.table;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public interface Table<T> extends Cloneable, Iterable<T> {
	
	List<T> getRow(int index);
	
	List<List<T>> getRows();
	
	List<T> getColumn(int index);
	
	List<List<T>> getColumns();
	
	T get(int i, int j);
	
	int getRowSize(int index);
	
	int getColumnSize(int index);
	
	int getMaxRowSize();
	
	int getMaxColumnSize();
	
	int getNumberOfRows();
	
	int getNumberOfColumns();
	
	boolean contains(T element);
	
	Collection<T> getAllElements();
	
	Iterator<T> getRowIterator();
	
	Iterator<T> getColumnIterator();
	
}
