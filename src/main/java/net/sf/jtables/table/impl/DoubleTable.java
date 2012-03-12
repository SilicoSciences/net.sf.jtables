package net.sf.jtables.table.impl;

import java.util.List;

import net.sf.jtables.table.Row;

public class DoubleTable extends AnnotatedMutableTableImpl<Double> {

	public DoubleTable() {
		super();
	}

	public DoubleTable(List<Row<Double>> rows) {
		super(rows);
	}
}
