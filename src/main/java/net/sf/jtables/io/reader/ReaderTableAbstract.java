/**********************************************************************
Copyright (c) 2009-2012 Alexander Kerner. All rights reserved.
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 ***********************************************************************/

package net.sf.jtables.io.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import net.sf.jtables.table.Row;
import net.sf.jtables.table.TableAnnotated;
import net.sf.jtables.table.TableMutableAnnotated;
import net.sf.jtables.table.impl.RowImpl;
import net.sf.kerner.utils.io.buffered.AbstractIOIterator;
import net.sf.kerner.utils.io.buffered.IOIterator;

/**
 * Prototype implementation for {@link ReaderTable}.
 * <p>
 * <b>Example:</b><br>
 * </p>
 * <p>
 * 
 * <pre>
 * TODO example
 * </pre>
 * 
 * </p>
 * 
 * @author <a href="mailto:alex.kerner.24@googlemail.com">Alexander Kerner</a>
 * @version 2013-01-24
 * @param <T>
 *            type of elements in {@code Table}
 */
public abstract class ReaderTableAbstract<T> extends AbstractIOIterator<Row<T>> implements ReaderTable<T> {

    /**
     * Default column delimiter (tab).
     */
    public final static String DEFAULT_DELIM = "\t";

    /**
     * Does the table have column headers?
     */
    protected final boolean colsB;

    /**
     * column headers.
     */
    protected final List<String> columnHeaders = new ArrayList<String>();

    /**
     * column delimiter.
     */
    protected final String delim;

    /**
     * currently reading first line?
     */
    private volatile boolean firstLine = true;

    /**
     * row headers.
     */
    protected final List<String> rowHeaders = new ArrayList<String>();

    /**
     * Does the table have row headers?
     */
    protected final boolean rowsB;

    public ReaderTableAbstract(final File file) throws IOException {
        this(file, false, false, null);
    }

    /**
     * Create a new {@code AbstractTableReader}.
     * 
     * @param file
     *            {@link File} from which table is read
     * @param columnIds
     *            {@code true}, if columns have headers; {@code false} otherwise
     * @param rowIds
     *            {@code true}, if rows have headers; {@code false} otherwise
     * @throws IOException
     *             if anything goes wrong
     */
    public ReaderTableAbstract(final File file, final boolean columnIds, final boolean rowIds) throws IOException {
        this(file, columnIds, rowIds, null);
    }

    /**
     * Create a new {@code AbstractTableReader}.
     * 
     * @param file
     *            {@link File} from which table is read
     * @param columnIds
     *            {@code true}, if columns have headers; {@code false} otherwise
     * @param rowIds
     *            {@code true}, if rows have headers; {@code false} otherwise
     * @param delim
     *            column delimiter to use
     * @throws IOException
     *             if anything goes wrong
     */
    public ReaderTableAbstract(final File file, final boolean columnIds, final boolean rowIds, final String delim)
            throws IOException {
        this(new FileInputStream(file), columnIds, rowIds, delim);
    }

    /**
     * Create a new {@code AbstractTableReader}.
     * 
     * @param stream
     *            {@link InputStream} from which table is read
     * @param columnIds
     *            {@code true}, if columns have headers; {@code false} otherwise
     * @param rowIds
     *            {@code true}, if rows have headers; {@code false} otherwise
     * @throws IOException
     *             if anything goes wrong
     */
    public ReaderTableAbstract(final InputStream stream, final boolean columnIds, final boolean rowIds)
            throws IOException {
        this(stream, columnIds, rowIds, null);
    }

    /**
     * Create a new {@code AbstractTableReader}.
     * 
     * @param stream
     *            {@link InputStream} from which table is read
     * @param columnIds
     *            {@code true}, if columns have headers; {@code false} otherwise
     * @param rowIds
     *            {@code true}, if rows have headers; {@code false} otherwise
     * @param delim
     *            column delimiter to use
     * @throws IOException
     *             if anything goes wrong
     */
    public ReaderTableAbstract(final InputStream stream, final boolean columnIds, final boolean rowIds,
            final String delim) throws IOException {
        super(stream);
        this.colsB = columnIds;
        this.rowsB = rowIds;
        if (delim == null)
            this.delim = DEFAULT_DELIM;
        else
            this.delim = delim;
    }

    /**
     * Create a new {@code AbstractTableReader}.
     * 
     * @param reader
     *            {@link Reader} from which table is read
     * @param columnIds
     *            {@code true}, if columns have headers; {@code false} otherwise
     * @param rowIds
     *            {@code true}, if rows have headers; {@code false} otherwise
     * @throws IOException
     *             if anything goes wrong
     */
    public ReaderTableAbstract(final Reader reader, final boolean columnIds, final boolean rowIds) throws IOException {
        this(reader, columnIds, rowIds, null);
    }

    /**
     * Create a new {@code AbstractTableReader}.
     * 
     * @param reader
     *            {@link Reader} from which table is read
     * @param columnIds
     *            {@code true}, if columns have headers; {@code false} otherwise
     * @param rowIds
     *            {@code true}, if rows have headers; {@code false} otherwise
     * @param delim
     *            column delimiter to use
     * @throws IOException
     *             if anything goes wrong
     */
    public ReaderTableAbstract(final Reader reader, final boolean columnIds, final boolean rowIds, final String delim)
            throws IOException {
        super(reader);
        this.colsB = columnIds;
        this.rowsB = rowIds;
        if (delim == null)
            this.delim = DEFAULT_DELIM;
        else
            this.delim = delim;
    }

    /**
     * Read the next {@link Row} from input source.
     * 
     * @return next {@link Row} or {@code null} if nothing left to read
     */
    @Override
    protected Row<T> doRead() throws IOException {
        String line = reader.readLine();
        if (line == null)
            return null;
        if (colsB && firstLine) {
            columnHeaders.addAll(getColHeaders(line));
            firstLine = false;
            // column headers read, continue to next line
            line = reader.readLine();
            if (line == null)
                return null;
        }
        Scanner scanner = null;
        try {
            scanner = new Scanner(line);
            scanner.useDelimiter(delim);
            final RowImpl<T> result = new RowImpl<T>();

            // first column (row headers)?
            boolean first = true;

            while (scanner.hasNext()) {
                final String s = scanner.next();
                // if(UtilStrings.emptyString(s)){
                // continue;
                // }
                if (rowsB && first) {
                    rowHeaders.add(s);
                    first = false;
                } else {
                    result.add(parse(s));
                }
            }

            if (result.isEmpty())
                return null;

            result.setIdentifier(columnHeaders);

            return result;
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    /**
     * Extract column headers from first line.
     * 
     * @param line
     *            {@code String} that contains column headers
     * @return {@link List} of column headers
     */
    protected List<String> getColHeaders(final String line) {
        final Scanner scanner = new Scanner(line);
        scanner.useDelimiter(delim);
        final List<String> list = new ArrayList<String>();
        while (scanner.hasNext()) {
            final String s = scanner.next();
            list.add(s);
        }
        scanner.close();
        return list;
    }

    public synchronized List<String> getColumnHeaders() throws IOException {
        if (colsB && firstLine) {
            columnHeaders.addAll(getColHeaders(reader.readLine()));
            firstLine = false;
        }
        return columnHeaders;
    }

    /**
	 * 
	 */
    protected abstract TableMutableAnnotated<T> getInstance();

    /**
	 * 
	 */
    public IOIterator<Row<T>> getIterator() throws IOException {
        return this;
    }

    public synchronized List<String> getRowHeaders() throws IOException {
        return rowHeaders;
    }

    /**
     * Parse an object of type {@code T} from given string.
     * 
     * @param s
     *            {@link String} to parse from
     * @return object of type {@code T} that was parsed
     * @throws NumberFormatException
     *             if parsing fails
     */
    protected abstract T parse(String s) throws NumberFormatException;

    public synchronized TableAnnotated<T> readTableAtOnce() throws IOException {
        final TableMutableAnnotated<T> result = getInstance();
        final IOIterator<Row<T>> it = getIterator();
        while (it.hasNext()) {
            final Row<T> next = it.next();
            result.addRow(next);
        }
        it.close();
        result.setRowIdentifier(rowHeaders);
        result.setColumnIdentifier(columnHeaders);
        return result;
    }

}
