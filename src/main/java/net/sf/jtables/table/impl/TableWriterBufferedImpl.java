package net.sf.jtables.table.impl;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import net.sf.jtables.table.Row;
import net.sf.jtables.table.TableWriterBuffered;
import net.sf.kerner.utils.collections.impl.CollectionUtils;
import net.sf.kerner.utils.io.buffered.AbstractBufferedWriter;

public class TableWriterBufferedImpl extends AbstractBufferedWriter implements TableWriterBuffered {

	public final static String DEFAULT_DELIMITER = "\t";

	protected final List<?> rowIds;

	protected final List<?> colIds;

	protected boolean firstRow = true;

	protected boolean firstColumn = true;

	public TableWriterBufferedImpl(File file, List<?> rowIds, List<?> colIds) throws IOException {
		super(file);
		this.rowIds = rowIds;
		this.colIds = colIds;
	}

	public TableWriterBufferedImpl(File file) throws IOException {
		super(file);
		this.rowIds = null;
		this.colIds = null;
	}

	public TableWriterBufferedImpl(OutputStream stream, List<?> rowIds, List<?> colIds) {
		super(stream);
		this.rowIds = rowIds;
		this.colIds = colIds;
	}

	public TableWriterBufferedImpl(OutputStream stream) {
		super(stream);
		this.rowIds = null;
		this.colIds = null;
	}

	public TableWriterBufferedImpl(Writer writer, List<?> rowIds, List<?> colIds) {
		super(writer);
		this.rowIds = rowIds;
		this.colIds = colIds;
	}

	public TableWriterBufferedImpl(Writer writer, List<?> rowIds) {
		super(writer);
		this.rowIds = null;
		this.colIds = null;
	}

	public void write(String delimiter, Row<?> row) throws IOException {
		if (firstRow)
			writer.write(CollectionUtils.toString(rowIds, delimiter));
		writer.write(row.toString(delimiter));
	}

	public void write(Row<?> row) throws IOException {
		write(DEFAULT_DELIMITER, row);
	}

	public void write(String delimiter, List<Row<?>> rows) throws IOException {
		Iterator<Row<?>> it = rows.iterator();
		while (it.hasNext()) {
			Row<?> r = it.next();
			write(delimiter, r);
			if (it.hasNext()) {
				writer.newLine();
			}
			writer.flush();
		}
	}

	public void write(List<Row<?>> rows) throws IOException {
		write(DEFAULT_DELIMITER, rows);
	}

	public void write(Row<?>... rows) throws IOException {
		write(DEFAULT_DELIMITER, rows);
	}

	public void write(String delimiter, Row<?>... rows) throws IOException {
		write(delimiter, Arrays.asList(rows));
	}
}
