package net.sf.jtables.table.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import net.sf.jtables.table.AnnotatedMutableTable;
import net.sf.jtables.table.ObjectToIndexMapper;
import net.sf.jtables.table.Utils;

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

	protected Set<Integer> createIndicesValues(Collection<? extends Object> keys) {
		final Set<Integer> result = new LinkedHashSet<Integer>();
		for (int i = 0; i < keys.size(); i++) {
			result.add(Integer.valueOf(i));
		}
		return result;
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
			sb.append(Utils.NEW_LINE_STRING);
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
				sb.append(Utils.NEW_LINE_STRING);
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
		checkRowIndex(key);
		return getRow(colIdents.get(key));
	}

}
