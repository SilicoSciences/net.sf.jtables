package net.sf.jtables.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import net.sf.jtables.io.transformer.TransformerRowToObjectString;
import net.sf.jtables.table.Row;

public abstract class ReaderTableObjectFromRow<T> extends ReaderTableString {

	protected volatile List<T> elements = new ArrayList<T>();

	public ReaderTableObjectFromRow(BufferedReader reader, boolean columnIds, boolean rowIds,
			String delim) throws IOException {
		super(reader, columnIds, rowIds, delim);

	}

	public ReaderTableObjectFromRow(BufferedReader reader, boolean columnIds, boolean rowIds)
			throws IOException {
		super(reader, columnIds, rowIds);

	}

	public ReaderTableObjectFromRow(File file, boolean columnIds, boolean rowIds, String delim)
			throws IOException {
		super(file, columnIds, rowIds, delim);

	}

	public ReaderTableObjectFromRow(File file) throws IOException {
		super(file);

	}

	public ReaderTableObjectFromRow(File file, boolean columnIds, boolean rowIds)
			throws IOException {
		super(file, columnIds, rowIds);

	}

	public ReaderTableObjectFromRow(InputStream stream, boolean columnIds, boolean rowIds,
			String delim) throws IOException {
		super(stream, columnIds, rowIds, delim);

	}

	public ReaderTableObjectFromRow(InputStream stream, boolean columnIds, boolean rowIds)
			throws IOException {
		super(stream, columnIds, rowIds);

	}

	public ReaderTableObjectFromRow(Reader reader, boolean columnIds, boolean rowIds,
			String delim) throws IOException {
		super(reader, columnIds, rowIds, delim);

	}

	public ReaderTableObjectFromRow(Reader reader, boolean columnIds, boolean rowIds)
			throws IOException {
		super(reader, columnIds, rowIds);

	}

	@Override
	protected synchronized Row<String> doRead() throws IOException {
		if(getTransformer() == null){
			throw new IllegalStateException("set transformer first");
		}
		final Row<String> row = super.doRead();
		if(row == null){
			return null;
		}
		elements.add(getTransformer().transform(row));
		return row;
	}

	public synchronized List<T> getAllElements() throws IOException {
		readTableAtOnce();
		return elements;
	}

	protected abstract TransformerRowToObjectString<T> getTransformer();

}
