package net.sf.jtables.table.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import net.sf.jtables.table.Row;
import net.sf.kerner.utils.collections.ObjectToIndexMapper;
import net.sf.kerner.utils.collections.impl.ObjectToIndexMapperImpl;

public class RowImpl<T> implements Row<T> {

	protected final List<T> implementation;
	
	protected volatile ObjectToIndexMapper mapper = new ObjectToIndexMapperImpl(
			new HashSet<Object>());
	
	public RowImpl(List<T> elements) {
		 implementation = Collections.unmodifiableList(new ArrayList<T>(elements));
	}

	public void setIdentifier(Set<? extends Object> ids) {
		this.mapper = new ObjectToIndexMapperImpl(ids);
	}
	
	// Implement //
	
	public T get(Object indentifier) {
		return get(mapper.get(indentifier));
	}

	public Set<Object> getIdentifier() {
		return new LinkedHashSet<Object>(mapper.keySet());
	}
	
	public ObjectToIndexMapper getObjectToIndexMapper() {
		return new ObjectToIndexMapperImpl(mapper.keySet());
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
