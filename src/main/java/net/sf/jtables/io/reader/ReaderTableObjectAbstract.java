package net.sf.jtables.io.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

import net.sf.kerner.utils.collections.list.impl.ListUtil;
import net.sf.kerner.utils.io.buffered.IOIterator;

public abstract class ReaderTableObjectAbstract<T> implements IOIterator<T> {

	protected final ReaderTableString reader;

	public ReaderTableObjectAbstract(BufferedReader reader, boolean columnIds, boolean rowIds,
			String delim) throws IOException {
		this.reader = new ReaderTableString(reader, columnIds, rowIds, delim);

	}

	public ReaderTableObjectAbstract(BufferedReader reader, boolean columnIds, boolean rowIds)
			throws IOException {
		this.reader = new ReaderTableString(reader, columnIds, rowIds);

	}

	public ReaderTableObjectAbstract(File file, boolean columnIds, boolean rowIds, String delim)
			throws IOException {
		this.reader = new ReaderTableString(file, columnIds, rowIds, delim);

	}

	public ReaderTableObjectAbstract(File file) throws IOException {
		this.reader = new ReaderTableString(file, true, false);

	}

	public ReaderTableObjectAbstract(File file, boolean columnIds, boolean rowIds)
			throws IOException {
		this.reader = new ReaderTableString(file, columnIds, rowIds);

	}

	public ReaderTableObjectAbstract(InputStream stream, boolean columnIds, boolean rowIds,
			String delim) throws IOException {
		this.reader = new ReaderTableString(stream, columnIds, rowIds, delim);

	}

	public ReaderTableObjectAbstract(InputStream stream, boolean columnIds, boolean rowIds)
			throws IOException {
		this.reader = new ReaderTableString(stream, columnIds, rowIds);

	}

	public ReaderTableObjectAbstract(Reader reader, boolean columnIds, boolean rowIds, String delim)
			throws IOException {
		this.reader = new ReaderTableString(reader, columnIds, rowIds, delim);

	}

	public ReaderTableObjectAbstract(Reader reader, boolean columnIds, boolean rowIds)
			throws IOException {
		this.reader = new ReaderTableString(reader, columnIds, rowIds);

	}

	public synchronized void close() {
		reader.close();
	}
	
	public boolean hasNext() throws IOException {
		return reader.hasNext();
	}
	
	public List<T> readAll() throws IOException{
		final List<T> result = ListUtil.newList();
		
		while(hasNext()){
			final T next = next();
			if(next == null){
				throw new NullPointerException();
			}
			result.add(next);
		}
		
		return result;
	}

}
