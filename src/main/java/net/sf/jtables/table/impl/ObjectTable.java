package net.sf.jtables.table.impl;

import java.util.List;

public class ObjectTable extends AnnotatedMutableTableImpl<Object> {

	public ObjectTable() {
		super();
	}

	public ObjectTable(List<List<? extends Object>> rows) {
		super(rows);
	}

}
