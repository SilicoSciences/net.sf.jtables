package net.sf.jtables.io;

import java.io.IOException;
import java.util.List;

import net.sf.jtables.table.Row;

public interface WriterTableBuffered {

	WriterTableBuffered write(Row<?> row) throws IOException;

	WriterTableBuffered write(String delimiter, Row<?> row) throws IOException;

	WriterTableBuffered write(List<Row<?>> rows) throws IOException;

	WriterTableBuffered write(String delimiter, List<Row<?>> rows) throws IOException;

	WriterTableBuffered write(Row<?>... rows) throws IOException;

	WriterTableBuffered write(String delimiter, Row<?>... rows) throws IOException;

}
