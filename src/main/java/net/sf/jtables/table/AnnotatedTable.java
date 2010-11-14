package net.sf.jtables.table;

import java.util.List;
import java.util.Set;

public interface AnnotatedTable<T> extends Table<T>{
	
	void setColumnIdentifier(Set<? extends Object> ids);
	
	void setRowIdentifier(Set<? extends Object> ids);
	
	Set<Object> getRowIdentifier();
	
	Set<Object> getColumnIdentifier();
	
	List<T> getRow(Object key);
	
	List<T> getColumn(Object key);

}
