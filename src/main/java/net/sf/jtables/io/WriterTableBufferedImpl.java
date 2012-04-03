package net.sf.jtables.io;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import net.sf.jtables.table.Row;
import net.sf.kerner.utils.collections.impl.CollectionUtils;
import net.sf.kerner.utils.io.buffered.AbstractBufferedWriter;

public class WriterTableBufferedImpl extends AbstractBufferedWriter implements WriterTableBuffered {

	public final static String DEFAULT_DELIMITER = "\t";

	protected volatile List<?> rowIds = new ArrayList<Object>();

	protected volatile List<?> colIds = new ArrayList<Object>();

	protected volatile boolean firstRow = true;

	protected volatile boolean firstColumn = true;

	public WriterTableBufferedImpl(File file) throws IOException {
		super(file);
	}

	public WriterTableBufferedImpl(OutputStream stream) {
		super(stream);
	}

	public WriterTableBufferedImpl(Writer writer) {
		super(writer);
	}

	public WriterTableBufferedImpl write(String delimiter, Row<?> row) throws IOException {
		if (firstRow && colIds != null && colIds.size() > 0){
			writer.write(CollectionUtils.toString(colIds, delimiter));
			writer.newLine();
			firstRow = false;
		}
		writer.write(row.toString(delimiter));
		return this;
	}

	public WriterTableBufferedImpl write(Row<?> row) throws IOException {
		return write(DEFAULT_DELIMITER, row);
	}

	public WriterTableBufferedImpl write(String delimiter, List<Row<?>> rows) throws IOException {
		Iterator<Row<?>> it = rows.iterator();
		while (it.hasNext()) {
			Row<?> r = it.next();
			write(delimiter, r);
			if (it.hasNext()) {
				writer.newLine();
			}
			writer.flush();
		}
		return this;
	}

	public WriterTableBufferedImpl write(List<Row<?>> rows) throws IOException {
		return write(DEFAULT_DELIMITER, rows);
	}

	public WriterTableBufferedImpl write(Row<?>... rows) throws IOException {
		return write(DEFAULT_DELIMITER, rows);
	}

	public WriterTableBufferedImpl write(String delimiter, Row<?>... rows) throws IOException {
		return write(delimiter, Arrays.asList(rows));
	}

	public List<?> getRowIds() {
		return rowIds;
	}

	public void setRowIds(List<?> rowIds) {
		this.rowIds = rowIds;
	}

	public List<?> getColIds() {
		return colIds;
	}

	public void setColIds(List<?> colIds) {
		this.colIds = colIds;
	}
}
