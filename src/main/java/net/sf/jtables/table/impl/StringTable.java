package net.sf.jtables.table.impl;

import java.util.List;

public class StringTable extends AnnotatedMutableTableImpl<String> {

	public StringTable() {
		super();
	}
	
	public StringTable(List<List<? extends String>> rows) {
		super(rows);
	}
}
