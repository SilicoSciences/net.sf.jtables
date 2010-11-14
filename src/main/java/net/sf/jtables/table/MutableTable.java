package net.sf.jtables.table;

import java.util.List;

public interface MutableTable<T> extends Table<T> {
	
	void addRow(List<? extends T> elements);

	void addRow(int index, List<? extends T> elements);

	void addColumn(List<? extends T> elements);

	void addColumn(int index, List<? extends T> elements);
	
	void setRow(int index, List<? extends T> elements);
	
	void setColumn(int index, List<? extends T> elements);
	
	void removeRow(int index);
	
	void removeColumn(int index);
	
	void set(int i, int j, T element);
	
	void remove(int i, int j);
	
	void fill(int i, T element);
	
	void fillRows(int i, T element);
	
	void fillColumns(int i, T element);

	void clear();

}
