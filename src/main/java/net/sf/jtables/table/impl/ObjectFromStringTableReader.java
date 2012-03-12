package net.sf.jtables.table.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.jtables.table.Row;
import net.sf.jtables.table.RowToObjectTransformerString;
import net.sf.kerner.utils.io.buffered.IOIterator;

public class ObjectFromStringTableReader<T> {

	protected final boolean colIds;

	protected final boolean rowIds;

	protected final RowToObjectTransformerString<? extends T> parser;

	protected final int maxElements;

	public ObjectFromStringTableReader(RowToObjectTransformerString<? extends T> parser, boolean colIds, boolean rowIds,
			int maxElements) {
		this.colIds = colIds;
		this.rowIds = rowIds;
		this.parser = parser;
		this.maxElements = maxElements;
	}

	public ObjectFromStringTableReader(RowToObjectTransformerString<? extends T> parser) {
		this(parser, true, false, -1);
	}

	public List<T> readFile(File file) throws IOException {
		final List<T> result = new ArrayList<T>();
		final StringTableReader reader = new StringTableReader(file, colIds, rowIds);
		final IOIterator<Row<String>> it = reader.getIterator();
		int cnt = 0;
		while(it.hasNext() && (maxElements < 0 || maxElements >= cnt)){
			cnt++;
			result.add(parser.transform(it.next()));
		}
		reader.close();
		return result;
	}

}
