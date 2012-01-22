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
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import net.sf.jtables.table.Row;

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
 * @version 2012-01-12
 * 
 * @param <T>
 *            type of table elements
 */
public class RowImpl<T> implements Row<T> {

	/**
	 * List implementation to which is delegated.
	 */
	protected final List<RowColumnElement<T>> implementation = new ArrayList<RowColumnElement<T>>();

	protected volatile String identifier;
	
	public RowImpl(String identifier, List<T> elements) {
		this.identifier = identifier;
		for(T t : elements)
			this.add(t);
	}
	
	public RowImpl(Row<T> template) {
		this.setIdentifier(template.getIdentifier());
		this.implementation.addAll(template.getElements());
	}
	
	public RowImpl() {
		
	}
	
	protected List<T> asValueList(){
		final List<T> values = new ArrayList<T>();
		for(RowColumnElement<T> e : implementation){
			values.add(e.getValue());
		}
		return values;
	}

	// Implement //

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
		
	}

	public T get(Object indentifier) {
		for(RowColumnElement<T> t : implementation){
			if(t.getIdentifier().equals(indentifier))
				return t.getValue();
		}
		throw new NoSuchElementException("no element for identifier [" + identifier + "]");
	}

	public boolean add(RowColumnElement<T> element) {
		return implementation.add(element);
	}
	
	public boolean addAll(List<RowColumnElement<T>> elements) {
		return implementation.addAll(elements);
	}
	
	public List<RowColumnElement<T>> getElements() {
		return implementation;
	}
	
	@Override
	protected RowImpl<T> clone() throws CloneNotSupportedException {
		return new RowImpl<T>(this);
	}
	
	public RowColumnElement<T> getElement(int i) {
		return implementation.get(i);
	}
	
	public RowColumnElement<T> getElement(Object identifier) {
		for(RowColumnElement<T> e : implementation){
			if(e.getIdentifier().equals(identifier))
				return e;
		}
		throw new NoSuchElementException("no element for identifier " + identifier);
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
		return asValueList().iterator();
	}

	public Object[] toArray() {
		return implementation.toArray();
	}

	@SuppressWarnings("hiding")
	public <T> T[] toArray(T[] a) {
		return implementation.toArray(a);
	}

	public boolean add(T e) {
		return implementation.add(new RowColumnElement<T>(null, e));
	}

	public boolean remove(Object o) {
		return implementation.remove(o);
	}

	public boolean containsAll(Collection<?> c) {
		return implementation.containsAll(c);
	}

	public boolean addAll(Collection<? extends T> c) {
		boolean b = false;
		for(T t : c)
			b = add(t);
		return b;
	}

	public boolean addAll(int index, Collection<? extends T> c) {
		boolean b = false;
		List<T> list = new ArrayList<T>(c);
		Collections.reverse(list);
		for(T t : list){
			b = implementation.add(new RowColumnElement<T>(null, t));
		}
		return b;
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
		return implementation.get(index).getValue();
	}

	public T set(int index, T element) {
		return implementation.set(index, new RowColumnElement<T>(null, element)).getValue();
	}

	public void add(int index, T element) {
		implementation.add(index, new RowColumnElement<T>(null, element));
	}

	public T remove(int index) {
		return implementation.remove(index).getValue();
	}

	public int indexOf(Object o) {
		return implementation.indexOf(o);
	}

	public int lastIndexOf(Object o) {
		return implementation.lastIndexOf(o);
	}

	public ListIterator<T> listIterator() {
		return asValueList().listIterator();
	}

	public ListIterator<T> listIterator(int index) {
		return asValueList().listIterator(index);
	}

	public List<T> subList(int fromIndex, int toIndex) {
		return asValueList().subList(fromIndex, toIndex);
	}

}
