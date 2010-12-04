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
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * 
 * A {@code AnnotatedTable} extends {@link Table} by providing object
 * identifiers for rows and columns. <br>
 * This makes it possible to access rows and columns not only by their integer
 * index, but also by an additional object mapping.
 * 
 * <p>
 * <b>Example:</b><br>
 * 
 * </p>
 * <p>
 * 
 * <pre>
 * TODO example
 * </pre>
 * 
 * </p>
 * 
 * @author <a href="mailto:alex.kerner.24@googlemail.com">Alexander Kerner</a>
 * @version 2010-12-04
 * 
 * @param <T>
 *            type of elements in table
 */
public interface AnnotatedTable<T> extends Table<T> {

	/**
	 * 
	 * Set identifiers for columns.
	 *
	 * @param ids a {@link java.util.Set Set} that contains all column identifiers
	 */
	void setColumnIdentifier(Set<? extends Object> ids);

	/**
	 * 
	 * Set identifiers for rows.
	 *
	 * @param ids a {@link java.util.Set Set} that contains all row identifiers
	 */
	void setRowIdentifier(Set<? extends Object> ids);

	/**
	 * 
	 * Retrieve a {@link java.util.Set Set} that contains all row identifiers. 
	 *
	 * @return all row identifiers
	 */
	Set<Object> getRowIdentifier();

	/**
	 * 
	 * Retrieve a {@link java.util.Set Set} that contains all column identifiers. 
	 *
	 * @return all column identifiers
	 */
	Set<Object> getColumnIdentifier();

	/**
	 * 
	 * Retrieve row that is associated to given object key.
	 *
	 * @param key identifier that maps to returned row
	 * @return row that is mapped by given identifier
	 * @throws NoSuchElementException if there is no row mapped by given identifier
	 */
	List<T> getRow(Object key);

	/**
	 * 
	 * Retrieve column that is associated to given object key.
	 *
	 * @param key identifier that maps to returned column
	 * @return column that is mapped by given identifier
	 * @throws NoSuchElementException if there is no column mapped by given identifier
	 */
	List<T> getColumn(Object key);

}
