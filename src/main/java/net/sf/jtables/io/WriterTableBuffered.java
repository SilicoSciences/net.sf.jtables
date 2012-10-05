package net.sf.jtables.io;

import java.io.IOException;
import java.util.List;

import net.sf.jtables.table.Row;

public interface WriterTableBuffered {

    WriterTableBuffered write(List<? extends Row<? extends Object>> rows) throws IOException;

    WriterTableBuffered write(Row<? extends Object>... rows) throws IOException;

    WriterTableBuffered write(Row<?> row) throws IOException;

    WriterTableBuffered write(String delimiter, List<? extends Row<? extends Object>> rows) throws IOException;

    WriterTableBuffered write(String delimiter, Row<? extends Object> row) throws IOException;

    WriterTableBuffered write(String delimiter, Row<? extends Object>... rows) throws IOException;

}
