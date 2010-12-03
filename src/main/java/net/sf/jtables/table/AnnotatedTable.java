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

import java.util.List;
import java.util.Set;

/**
 * 
 * A {@code AnnotatedTable} extends {@link Table} by providing ... TODO 
 * 
 * <p>
 * <b>Example:</b><br>
 *
 * </p>
 * <p>
 * <pre>
 * TODO example
 * </pre>
 * </p>
 *
 * @author <a href="mailto:alex.kerner.24@googlemail.com">Alexander Kerner</a>
 * @version 2010-12-04
 *
 * @param <T> type of elements in table
 */
public interface AnnotatedTable<T> extends Table<T>{
	
	/**
	 * 
	 * Assign all columns TODO
	 *
	 * @param ids
	 */
	void setColumnIdentifier(Set<? extends Object> ids);
	
	void setRowIdentifier(Set<? extends Object> ids);
	
	Set<Object> getRowIdentifier();
	
	Set<Object> getColumnIdentifier();
	
	List<T> getRow(Object key);
	
	List<T> getColumn(Object key);

}
