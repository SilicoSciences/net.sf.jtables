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

package net.sf.jtables.table;

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
	
	List<T> getAllElements();
	
	Iterator<List<T>> getRowIterator();
	
	Iterator<List<T>> getColumnIterator();
	
}
