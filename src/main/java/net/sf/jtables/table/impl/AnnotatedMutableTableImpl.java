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

package net.sf.jtables.table.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import net.sf.jtables.table.AnnotatedMutableTable;
import net.sf.jtables.table.Column;
import net.sf.jtables.table.Row;
import net.sf.kerner.utils.collections.ObjectToIndexMapper;
import net.sf.kerner.utils.collections.impl.ObjectToIndexMapperImpl;
import net.sf.kerner.utils.io.IOUtils;

/**
 * 
 * Default implementation for {@link AnnotatedMutableTable}.
 * 
 *
 * @author <a href="mailto:alex.kerner.24@googlemail.com">Alexander Kerner</a>
 * @version 2012-01-12
 *
 * @param <T> type of elements in {@code Table}
 */
public class AnnotatedMutableTableImpl<T> extends MutableTableImpl<T> implements
AnnotatedMutableTable<T> {

	/**
	 * row mappings.
	 */
	protected volatile ObjectToIndexMapper rowMapper = new ObjectToIndexMapperImpl(
			new HashSet<Object>());

	/**
	 * column mappings.
	 */
	protected volatile ObjectToIndexMapper colMapper = new ObjectToIndexMapperImpl(
			new HashSet<Object>());

	/**
	 * 
	 * Create an empty {@code AnnotatedMutableTableImpl}.
	 *
	 */
	public AnnotatedMutableTableImpl() {
		super();
	}

	/**
	 * 
	 * Create an {@code AnnotatedMutableTableImpl} with given rows.
	 *
	 * @param rows rows initially contained by this {@code Table}
	 */
	public AnnotatedMutableTableImpl(List<Row<T>> rows) {
		super(rows);
	}

	// Private //

	// Protected //

	protected void checkRowIndex(Object key) {
		if (rowMapper.containsKey(key)) {
			// all good
		} else
			throw new NoSuchElementException("no element for row index [" + key
					+ "]");
	}

	protected void checkColumnIndex(Object key) {
		if (colMapper.containsKey(key)) {
			// all good
		} else
			throw new NoSuchElementException("no element for column index [" + key
					+ "]");
	}

	// Public //

	// Override //
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();

		// print column indices
		if (getColumnIdentifier().isEmpty()) {
			// skip
		} else {
			final List<?> r = new ArrayList<Object>(getColumnIdentifier());
			final java.util.Iterator<?> it = r.iterator();
			
			if(getRowIdentifier().isEmpty()){
				
			} else {
				sb.append("\t");
			}
			
			while (it.hasNext()) {
				sb.append(it.next());
				if (it.hasNext())
					sb.append('\t');
			}
			sb.append(IOUtils.NEW_LINE_STRING);
		}

		final Iterator<? extends List<T>> rowIt = getRowIterator();
		final Iterator<?> identIt = getRowIdentifier().iterator();

		while (rowIt.hasNext() || identIt.hasNext()) {
			if (identIt.hasNext()) {
				sb.append(identIt.next());
				sb.append('\t');
			}
			if (rowIt.hasNext()) {
				final Iterator<?> ii = rowIt.next().iterator();
				while(ii.hasNext()){
					sb.append(ii.next());
					if(ii.hasNext())
						sb.append("\t");
				}
				
				
			}
			if (rowIt.hasNext() || identIt.hasNext())
				sb.append(IOUtils.NEW_LINE_STRING);
		}
		return sb.toString();
	}

	// Implement //

	/**
	 * 
	 */
	public void setColumnIdentifier(Set<? extends Object> ids) {
		this.colMapper = new ObjectToIndexMapperImpl(ids);
	}

	public void setRowIdentifier(Set<? extends Object> ids) {
		this.rowMapper = new ObjectToIndexMapperImpl(ids);
	}

	public Set<Object> getRowIdentifier() {
		return new LinkedHashSet<Object>(rowMapper.keySet());
	}

	public Set<Object> getColumnIdentifier() {
		return new LinkedHashSet<Object>(colMapper.keySet());
	}

	public Row<T> getRow(Object key) {
		net.sf.kerner.utils.Utils.checkForNull(key);
		checkRowIndex(key);
		return getRow(rowMapper.get(key));
	}

	public Column<T> getColumn(Object key) {
		net.sf.kerner.utils.Utils.checkForNull(key);
		checkColumnIndex(key);
		return getColumn(colMapper.get(key));
	}

}
