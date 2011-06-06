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

package net.sf.jtables.table.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import net.sf.jtables.table.AnnotatedMutableTable;
import net.sf.kerner.utils.collections.ObjectToIndexMapper;
import net.sf.kerner.utils.collections.impl.ObjectToIndexMapperImpl;
import net.sf.kerner.utils.io.IOUtils;

/**
 * 
 * Default implementation for {@link net.sf.jtables.table.AnnotatedMutableTable AnnotatedMutableTable}.
 * 
 *
 * @author <a href="mailto:alex.kerner.24@googlemail.com">Alexander Kerner</a>
 * @version 2010-12-05
 *
 * @param <T> type of elements in this {@code AnnotatedMutableTableImpl}
 */
public class AnnotatedMutableTableImpl<T> extends MutableTableImpl<T> implements
AnnotatedMutableTable<T> {

	protected volatile ObjectToIndexMapper rowIdents = new ObjectToIndexMapperImpl(
			new HashSet<Object>());

	protected volatile ObjectToIndexMapper colIdents = new ObjectToIndexMapperImpl(
			new HashSet<Object>());

	public AnnotatedMutableTableImpl() {
		super();
	}

	public AnnotatedMutableTableImpl(List<List<? extends T>> rows) {
		super(rows);
	}

	// Private //

	// Protected //

	protected void checkRowIndex(Object key) {
		if (rowIdents.containsKey(key)) {
			// all good
		} else
			throw new NoSuchElementException("no element for row index [" + key
					+ "]");
	}

	protected void checkColumnIndex(Object key) {
		if (colIdents.containsKey(key)) {
			// all good
		} else
			throw new NoSuchElementException("no element for row index [" + key
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

		final Iterator<List<T>> rowIt = getRowIterator();
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
		this.colIdents = new ObjectToIndexMapperImpl(ids);
	}

	public void setRowIdentifier(Set<? extends Object> ids) {
		this.rowIdents = new ObjectToIndexMapperImpl(ids);
	}

	public Set<Object> getRowIdentifier() {
		return new LinkedHashSet<Object>(rowIdents.keySet());
	}

	public Set<Object> getColumnIdentifier() {
		return new LinkedHashSet<Object>(colIdents.keySet());
	}

	public List<T> getRow(Object key) {
		net.sf.kerner.utils.Utils.checkForNull(key);
		checkRowIndex(key);
		return getRow(rowIdents.get(key));
	}

	public List<T> getColumn(Object key) {
		net.sf.kerner.utils.Utils.checkForNull(key);
		checkColumnIndex(key);
		return getColumn(colIdents.get(key));
	}

}
