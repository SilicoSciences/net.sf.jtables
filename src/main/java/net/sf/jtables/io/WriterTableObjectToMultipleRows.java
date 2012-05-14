package net.sf.jtables.io;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Iterator;

import net.sf.jtables.io.transformer.TransformerObjectToMultipleRowsString;
import net.sf.jtables.table.Row;

public abstract class WriterTableObjectToMultipleRows<T> extends WriterTableObjectToRowsAbstract<T> {

	public WriterTableObjectToMultipleRows(File file) throws IOException {
		super(file);
	}

	public WriterTableObjectToMultipleRows(OutputStream stream) {
		super(stream);
	}

	public WriterTableObjectToMultipleRows(Writer writer) {
		super(writer);
	}

	public WriterTableObjectToMultipleRows<T> writeElement(String delimiter, T element)
			throws IOException {
		if (getTransformer() == null) {
			throw new IllegalStateException("set transformer first");
		}

		Iterator<Row<String>> it = getTransformer().transform(element).iterator();
		while (it.hasNext()) {
			super.write(delimiter, it.next());
			if (it.hasNext()) {
				super.writer.newLine();
			}

		}
		return this;
	}

	protected abstract TransformerObjectToMultipleRowsString<T> getTransformer();

}
