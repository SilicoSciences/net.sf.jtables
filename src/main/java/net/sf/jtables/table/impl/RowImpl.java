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

package net.sf.jtables.table.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import net.sf.jtables.table.Row;
import net.sf.kerner.utils.collections.ObjectToIndexMapper;
import net.sf.kerner.utils.collections.impl.ObjectToIndexMapperImpl;

/**
 * 
 * Default implementation for {@link Row}.
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
 * @version 2012-01-25
 * 
 * @param <T>
 *            type of table elements
 */
public class RowImpl<T> implements Row<T> {

	protected final List<T> implementation = new ArrayList<T>();

	protected volatile ObjectToIndexMapper mapper = new ObjectToIndexMapperImpl(
			new ArrayList<Object>());

	public RowImpl(List<T> elements) {
		implementation.addAll(elements);
	}
	
	public RowImpl(T... elements) {
		implementation.addAll(Arrays.asList(elements));
	}

	public RowImpl() {
	}

	public void setIdentifier(List<? extends Object> ids) {
		this.mapper = new ObjectToIndexMapperImpl(ids);
	}

	// Implement //

	public T get(Object indentifier) {
		// if(implementation.size() <= mapper.get(indentifier)){
		// throw new NoSuchElementException("no value for [" + indentifier +
		// "]");
		// }
		return get(mapper.get(indentifier));
	}

	public List<Object> getIdentifier() {
		return new ArrayList<Object>(mapper.keys());
	}

	public ObjectToIndexMapper getObjectToIndexMapper() {
		return new ObjectToIndexMapperImpl(mapper.keys());
	}

	// Delegates //

	public int size() {
		return implementation.size();
	}

	public boolean isEmpty() {
		return implementation.isEmpty();
	}

	public boolean contains(Object o) {
		return implementation.contains(o);
	}

	public Iterator<T> iterator() {
		return implementation.iterator();
	}

	public Object[] toArray() {
		return implementation.toArray();
	}

	@SuppressWarnings("hiding")
	public <T> T[] toArray(T[] a) {
		return implementation.toArray(a);
	}

	public boolean add(T e) {
		return implementation.add(e);
	}

	public boolean remove(Object o) {
		return implementation.remove(o);
	}

	public boolean containsAll(Collection<?> c) {
		return implementation.containsAll(c);
	}

	public boolean addAll(Collection<? extends T> c) {
		return implementation.addAll(c);
	}

	public boolean addAll(int index, Collection<? extends T> c) {
		return implementation.addAll(index, c);
	}

	public boolean removeAll(Collection<?> c) {
		return implementation.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return implementation.retainAll(c);
	}

	public void clear() {
		implementation.clear();
	}

	public boolean equals(Object o) {
		return implementation.equals(o);
	}

	public int hashCode() {
		return implementation.hashCode();
	}

	@Override
	public String toString() {
		return implementation.toString();
	}

	public T get(int index) {
		return implementation.get(index);
	}

	public T set(int index, T element) {
		return implementation.set(index, element);
	}

	public void add(int index, T element) {
		implementation.add(index, element);
	}

	public T remove(int index) {
		return implementation.remove(index);
	}

	public int indexOf(Object o) {
		return implementation.indexOf(o);
	}

	public int lastIndexOf(Object o) {
		return implementation.lastIndexOf(o);
	}

	public ListIterator<T> listIterator() {
		return implementation.listIterator();
	}

	public ListIterator<T> listIterator(int index) {
		return implementation.listIterator(index);
	}

	public List<T> subList(int fromIndex, int toIndex) {
		return implementation.subList(fromIndex, toIndex);
	}

}
