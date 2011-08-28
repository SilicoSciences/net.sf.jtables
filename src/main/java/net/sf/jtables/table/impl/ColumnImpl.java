package net.sf.jtables.table.impl;

import java.util.List;

import net.sf.jtables.table.Column;

public class ColumnImpl<T> extends RowImpl<T> implements Column<T> {

	public ColumnImpl(List<T> elements) {
		super(elements);
	}

}
