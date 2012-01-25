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

package net.sf.jtables.table.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import net.sf.jtables.table.AnnotatedMutableTable;
import net.sf.jtables.table.TableReader;

/**
 * 
 * {@link TableReader} to read a table that contains {@link Integer} values.
 * 
 * <p>
 * <b>Example:</b><br>
 *
 * </p>
 * <p>
 * <pre>
 * TODO example
 * </pre>
 * </p>
 *
 * @author <a href="mailto:alex.kerner.24@googlemail.com">Alexander Kerner</a>
 * @version 2012-01-25
 *
 */
public class IntegerTableReader extends AbstractTableReader<Integer> {

	public IntegerTableReader(BufferedReader reader, boolean columnIds,
			boolean rowIds, String delim) throws IOException {
		super(reader, columnIds, rowIds, delim);
	}

	public IntegerTableReader(BufferedReader reader, boolean columnIds,
			boolean rowIds) throws IOException {
		super(reader, columnIds, rowIds);
	}

	public IntegerTableReader(File file, boolean columnIds, boolean rowIds,
			String delim) throws IOException {
		super(file, columnIds, rowIds, delim);
	}

	public IntegerTableReader(File file, boolean columnIds, boolean rowIds)
			throws IOException {
		super(file, columnIds, rowIds);
	}

	public IntegerTableReader(InputStream stream, boolean columnIds,
			boolean rowIds, String delim) throws IOException {
		super(stream, columnIds, rowIds, delim);
	}

	public IntegerTableReader(InputStream stream, boolean columnIds,
			boolean rowIds) throws IOException {
		super(stream, columnIds, rowIds);
	}

	public IntegerTableReader(Reader reader, boolean columnIds, boolean rowIds,
			String delim) throws IOException {
		super(reader, columnIds, rowIds, delim);
	}

	public IntegerTableReader(Reader reader, boolean columnIds, boolean rowIds) throws IOException {
		super(reader, columnIds, rowIds);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected IntegerTable getInstance() {
		return new IntegerTable();
	}

	@Override
	protected Integer parse(String s) throws NumberFormatException {
		return Integer.parseInt(s);
	}

}
