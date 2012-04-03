package net.sf.jtables.io;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class WriterTableObjectToRowsAbstract<T> extends WriterTableString {
	
	public WriterTableObjectToRowsAbstract(File file) throws IOException {
		super(file);
	}

	public WriterTableObjectToRowsAbstract(OutputStream stream) {
		super(stream);
	}

	public WriterTableObjectToRowsAbstract(Writer writer) {
		super(writer);
	}

	public WriterTableObjectToRowsAbstract<T> writeElement(T element) throws IOException {
		writeElement(DEFAULT_DELIMITER, element);
		return this;
	}
	
	public WriterTableObjectToRowsAbstract<T> writeElements(Collection<? extends T> elements) throws IOException {
		writeElements(DEFAULT_DELIMITER, elements);
		return this;
	}
	
	public WriterTableObjectToRowsAbstract<T> writeElements(String delimiter, Collection<? extends T> elements) throws IOException {
		Iterator<? extends T> it = elements.iterator();
		while(it.hasNext()){
			writeElement(delimiter, it.next());
			if(it.hasNext()){
				super.writer.newLine();
			}
		}
		return this;
	}
	
	public abstract WriterTableObjectToRowsAbstract<T> writeElement(String delimiter, T element) throws IOException;

}
