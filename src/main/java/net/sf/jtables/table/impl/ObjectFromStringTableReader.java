package net.sf.jtables.table.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.jtables.table.Row;
import net.sf.jtables.table.RowParserString;
import net.sf.kerner.utils.io.buffered.IOIterator;

public class ObjectFromStringTableReader<E> {
	
	protected final RowParserString<E> parser;
	
	protected final boolean colIds;

	protected final boolean rowIds;

	protected final int maxElements;
	
	public ObjectFromStringTableReader(RowParserString<E> parser, boolean colIds, boolean rowIds,
			int maxElements) {
		this.parser = parser;
		this.colIds = colIds;
		this.rowIds = rowIds;
		this.maxElements = maxElements;
	}
	
	public ObjectFromStringTableReader(RowParserString<E> parser) {
		this(parser, true, false, -1);
	}

	private boolean doRead(int alreadyRead, boolean rowHasNext){
		if(!rowHasNext)
			return false;
		if(maxElements < 0)
			return true;
		if(maxElements >= alreadyRead)
			return true;
		return false;
	}
	
	public List<E> readFile(File file) throws IOException {
		List<E> result = new ArrayList<E>();
		final StringTableReader tReader = new StringTableReader(file, colIds, rowIds);
		final IOIterator<Row<String>> rowIt = tReader.getIterator();
		int count = 0;
		while (doRead(count, rowIt.hasNext())) {
			result.add(parser.transform(rowIt.next()));
			count++;
		}
		tReader.close();
		count = 0;
		return result;
	}

}
