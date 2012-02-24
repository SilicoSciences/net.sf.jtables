package net.sf.jtables.table;

import java.io.IOException;
import java.util.List;

public interface TableWriterBuffered {

	void write(Row<?> row) throws IOException;
	
	void write(String delimiter, Row<?> row) throws IOException;

	void write(List<Row<?>> rows) throws IOException;
	
	void write(String delimiter, List<Row<?>> rows) throws IOException;

	void write(Row<?>... rows) throws IOException;
	
	void write(String delimiter, Row<?>... rows) throws IOException;

}
