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
import net.sf.kerner.utils.collections.impl.UtilCollection;
import net.sf.kerner.utils.io.buffered.AbstractBufferedWriter;

public class WriterTableBufferedImpl extends AbstractBufferedWriter implements WriterTableBuffered {

    public final static String DEFAULT_DELIMITER = "\t";

    protected volatile List<? extends Object> colIds = new ArrayList<Object>();

    protected volatile boolean firstColumn = true;

    protected volatile boolean firstRow = true;

    protected volatile List<? extends Object> rowIds = new ArrayList<Object>();

    public WriterTableBufferedImpl(final File file) throws IOException {
        super(file);
    }

    public WriterTableBufferedImpl(final OutputStream stream) {
        super(stream);
    }

    public WriterTableBufferedImpl(final Writer writer) {
        super(writer);
    }

    public List<Object> getColIds() {
        return new ArrayList<Object>(colIds);
    }

    public List<Object> getRowIds() {
        return new ArrayList<Object>(rowIds);
    }

    public void setColIds(final List<? extends Object> colIds) {
        this.colIds = colIds;
    }

    public void setColIds(final Object... colIds) {
        setColIds(Arrays.asList(colIds));
    }

    public void setRowIds(final List<? extends Object> rowIds) {
        this.rowIds = rowIds;
    }

    public WriterTableBufferedImpl write(final List<? extends Row<? extends Object>> rows) throws IOException {
        return write(DEFAULT_DELIMITER, rows);
    }

    public WriterTableBufferedImpl write(final Row<? extends Object> row) throws IOException {
        return write(DEFAULT_DELIMITER, row);
    }

    public WriterTableBufferedImpl write(final Row<? extends Object>... rows) throws IOException {
        return write(DEFAULT_DELIMITER, rows);
    }

    public WriterTableBufferedImpl write(final String delimiter, final List<? extends Row<? extends Object>> rows)
            throws IOException {
        final Iterator<? extends Row<? extends Object>> it = rows.iterator();
        while (it.hasNext()) {
            final Row<?> r = it.next();
            write(delimiter, r);
            if (it.hasNext()) {
                writer.newLine();
            }
            writer.flush();
        }
        return this;
    }

    public WriterTableBufferedImpl write(final String delimiter, final Row<? extends Object>... rows)
            throws IOException {
        return write(delimiter, Arrays.asList(rows));
    }

    public WriterTableBufferedImpl write(final String delimiter, final Row<?> row) throws IOException {
        if (firstRow && colIds != null && colIds.size() > 0) {
            writer.write(UtilCollection.toString(colIds, delimiter));
            writer.newLine();
            firstRow = false;
        }
        writer.write(row.toString(delimiter));
        return this;
    }
}
