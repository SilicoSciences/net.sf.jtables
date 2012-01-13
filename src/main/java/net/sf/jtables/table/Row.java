/**********************************************************************
Copyright (c) 2011-2012 Alexander Kerner. All rights reserved.
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

import net.sf.kerner.utils.collections.ObjectToIndexMapper;

/**
 * 
 * A table row.
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
 * @version 2012-01-12
 * 
 * @param <T>
 *            type of table element
 */
public interface Row<T> extends List<T>, Cloneable {

	/**
	 * 
	 * Retrieve this row's {@link ObjectToIndexMapper}.
	 * 
	 * @return this row's {@link ObjectToIndexMapper}
	 */
	ObjectToIndexMapper getObjectToIndexMapper();

	/**
	 * 
	 * Retrieve this row's identifiers.
	 * 
	 * @return this row's identifiers
	 */
	Set<Object> getIdentifier();

	/**
	 * 
	 * Set this row's identifier.
	 * 
	 * @param idents
	 *            new identifier for this row
	 */
	void setIdentifier(Set<? extends Object> idents);

	/**
	 * 
	 * Retrieve row's element that is associated with given identifier.
	 * 
	 * @param indentifier
	 *            identifier that is associated to returned value.
	 * @return value that is associated to given identifier
	 */
	T get(Object indentifier);

}
