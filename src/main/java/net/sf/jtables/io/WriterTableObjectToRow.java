package net.sf.jtables.io;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import net.sf.jtables.io.transformer.TransformerObjectToRowString;

public abstract class WriterTableObjectToRow<T> extends WriterTableObjectToRowsAbstract<T> {

	public WriterTableObjectToRow(File file) throws IOException {
		super(file);
	}

	public WriterTableObjectToRow(OutputStream stream) {
		super(stream);
	}

	public WriterTableObjectToRow(Writer writer) {
		super(writer);
	}

	public WriterTableObjectToRow<T> writeElement(String delimiter, T element) throws IOException {
		if (getTransformer() == null) {
			throw new IllegalStateException("set transformer first");
		}
		super.write(delimiter, getTransformer().transform(element));
		return this;
	}

	protected abstract TransformerObjectToRowString<T> getTransformer();

}
