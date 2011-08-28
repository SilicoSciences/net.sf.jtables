package net.sf.jtables.table.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import net.sf.jtables.table.AnnotatedMutableTable;

public class DoubleTableReader extends AbstractTableReader<Double> {

	public DoubleTableReader(File file, boolean columnIds, boolean rowIds,
			String delim) throws IOException {
		super(file, columnIds, rowIds, delim);
	}

	public DoubleTableReader(File file, boolean columnIds, boolean rowIds)
			throws IOException {
		super(file, columnIds, rowIds);
	}

	public DoubleTableReader(InputStream stream, boolean columnIds,
			boolean rowIds, String delim) throws IOException {
		super(stream, columnIds, rowIds, delim);
	}

	public DoubleTableReader(InputStream stream, boolean columnIds,
			boolean rowIds) throws IOException {
		super(stream, columnIds, rowIds);
	}

	public DoubleTableReader(Reader reader, boolean columnIds, boolean rowIds,
			String delim) throws IOException {
		super(reader, columnIds, rowIds, delim);
	}

	public DoubleTableReader(Reader reader, boolean columnIds, boolean rowIds)
			throws IOException {
		super(reader, columnIds, rowIds);
	}

	@Override
	protected AnnotatedMutableTable<Double> getInstance() {
		return new AnnotatedMutableTableImpl<Double>();
	}

	@Override
	protected Double parse(String s) throws NumberFormatException {
		return Double.parseDouble(s);
	}
	
	

}
