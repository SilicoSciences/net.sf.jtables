package net.sf.jtables.table.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.jtables.table.MutableTable;
import net.sf.jtables.table.Utils;

public class MutableTableImpl<T> extends TableImpl<T> implements
		MutableTable<T> {

	public MutableTableImpl(List<List<? extends T>> rows) {
		super(rows);
	}

	public MutableTableImpl() {

	}

	public void setRow(int index, List<? extends T> elements) {
		super.rows.set(index, elements);
	}

	public void setColumn(int index, List<? extends T> elements) {
		checkColumnIndex(index);
		net.sf.kerner.utils.Utils.checkForNull(elements);

		// assert that we have enough rows to set whole column
		// fill number of rows to fit number of elements in column
		Utils.fill(super.rows, elements.size(), new ArrayList<T>());

		for (int i = 0; i < elements.size(); i++) {
			final List<T> row = new ArrayList<T>(getRow(i));

			// assert that row is at least index+1 long, so it can take the
			// column element
			Utils.fill(row, index + 1, null);

			// finally set element
			row.set(index, elements.get(i));

			// replace row with new one
			setRow(i, row);
		}
	}

	public void addColumn(int index, List<? extends T> elements) {
		checkColumnIndex(index);
		net.sf.kerner.utils.Utils.checkForNull(elements);

		// assert that we have enough rows to set whole column
		// fill number of rows to fit number of elements in column
		Utils.fill(super.rows, elements.size(), new ArrayList<T>());

		for (int i = 0; i < elements.size(); i++) {
			final List<T> row = new ArrayList<T>(getRow(i));

			// assert that row is at least index long, so it can take the column
			// element
			Utils.fill(row, index, null);

			// finally set element
			row.add(index, elements.get(i));

			// replace row with new one
			setRow(i, row);
		}
	}

	public void addColumn(List<? extends T> elements) {
		net.sf.kerner.utils.Utils.checkForNull(elements);

		// assert that we have enough rows to set whole column
		// fill number of rows to fit number of elements in column
		Utils.fill(super.rows, elements.size(), new ArrayList<T>());

		for (int i = 0; i < elements.size(); i++) {
			final List<T> row = new ArrayList<T>(getRow(i));

			// since we are appending, assert that all row are maxRowSize()
			// long, so it can take the column element
			Utils.fill(row, getMaxRowSize() - 1, null);

			// finally set element
			row.add(elements.get(i));

			// replace row with new one
			setRow(i, row);
		}
	}

	public void set(int i, int j, T element) {
		// we are empty
		if (getNumberOfRows() == 0)
			addRow(new ArrayList<T>());
		
		fillRows(j + 1, null);
		fillColumns(i + 1, null);
		checkRowIndex(i);
		checkColumnIndex(j);
		
		final List<T> row2 = new ArrayList<T>(getRow(i));
		row2.set(j, element);
		setRow(i, row2);
	}

	public void clear() {
		super.rows.clear();
	}

	public void addRow(List<? extends T> elements) {
		super.rows.add(elements);
	}

	public void addRow(int index, List<? extends T> elements) {
		super.rows.add(index, elements);
	}

	public void fillRows(int index,final T element) {
		if(index < 1)
			throw new IllegalArgumentException();

		// assert we have at least one row
		if (getNumberOfRows() == 0)
			addRow(new ArrayList<T>() {
				private static final long serialVersionUID = 8771525210955142646L;
				{
					add(element);
				}
			});

		final int end = getNumberOfRows();

		for (int i = 0; i < end; i++) {
			final List<T> rr = new ArrayList<T>(getRow(i));
			Utils.fill(rr, index, element);
			setRow(i, rr);
		}
	}

	public void fillColumns(int index, final T element) {
		if(index < 1)
			throw new IllegalArgumentException();
		
		// assert we have at least one row
		if (getNumberOfRows() == 0)
			addRow(new ArrayList<T>() {
				private static final long serialVersionUID = 3543285099623756394L;
				{
					add(element);
				}
			});

		final int end = getNumberOfColumns();
		for (int i = 0; i < end; i++) {
			final List<T> rr = new ArrayList<T>(getColumn(i));

			Utils.fill(rr, index, element);

			setColumn(i, rr);
		}
	}

	public void fill(int i, T element) {
		fillRows(i, element);
		fillColumns(i, element);
	}

	public void fillAndSet(int i, int j, T elementToFill, T elementToSet) {
		fillRows(j+1, elementToFill); // rows have to be filled up to column index
		fillColumns(i+1, elementToFill); // columns have to be filled up to row index
		set(i, j, elementToSet);
	}

	public void removeRow(int index) {
		checkRowIndex(index);
		super.rows.remove(index);
	}

	public void removeColumn(int index) {
		checkColumnIndex(index);
		
		// iterate over rows and remove element at position index if there is such an element
		for(List<? extends T> t : super.rows){
			if(t.size() > index){
				t.remove(index);
			}
		}
	}
	
	public void remove(int i, int j) {
		checkColumnIndex(j);
		getRow(i).remove(j);
	}
}
