package net.sf.jtables.table.impl;

import java.util.List;

import net.sf.jtables.table.Row;

public class TableDouble extends AnnotatedMutableTableImpl<Double> {

	public TableDouble() {
		super();
	}

	public TableDouble(List<Row<Double>> rows) {
		super(rows);
	}
}
