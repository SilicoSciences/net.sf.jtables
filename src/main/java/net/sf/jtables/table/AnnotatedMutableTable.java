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

package net.sf.jtables.table;

import java.util.Set;

/**
 * 
 * A {@code MutableTable} and also a {@code AnnotatedTable}.
 *
 * @author <a href="mailto:alex.kerner.24@googlemail.com">Alexander Kerner</a>
 * @version 2012-01-12
 *
 * @param <T> type of elements in {@Table}
 * 
 * @see MutableTable
 * @see AnnotatedTable
 */
public interface AnnotatedMutableTable<T> extends AnnotatedTable<T>, MutableTable<T> {

	/**
	 * 
	 * Set identifiers for columns.
	 *
	 * @param ids a {@link Set} that contains all column identifiers
	 */
	void setColumnIdentifier(Set<? extends Object> ids);

	/**
	 * 
	 * Set identifiers for rows.
	 *
	 * @param ids a {@link Set} that contains all row identifiers
	 */
	void setRowIdentifier(Set<? extends Object> ids);
	
}
