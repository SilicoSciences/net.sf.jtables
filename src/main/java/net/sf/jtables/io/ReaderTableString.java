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

package net.sf.jtables.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import net.sf.jtables.table.impl.TableString;

/**
 * 
 * {@link ReaderTable} to read a table that contains {@link String} values.
 * 
 * 
 * @author <a href="mailto:alex.kerner.24@googlemail.com">Alexander Kerner</a>
 * @version 2012-03-13
 * 
 */
public class ReaderTableString extends ReaderTableAbstract<String> {

	public ReaderTableString(BufferedReader reader, boolean columnIds, boolean rowIds, String delim)
			throws IOException {
		super(reader, columnIds, rowIds, delim);
	}

	public ReaderTableString(BufferedReader reader, boolean columnIds, boolean rowIds)
			throws IOException {
		super(reader, columnIds, rowIds);
	}

	public ReaderTableString(File file, boolean columnIds, boolean rowIds, String delim)
			throws IOException {
		super(file, columnIds, rowIds, delim);
	}

	public ReaderTableString(File file) throws IOException {
		super(file);
	}

	public ReaderTableString(File file, boolean columnIds, boolean rowIds) throws IOException {
		super(file, columnIds, rowIds);
	}

	public ReaderTableString(InputStream stream, boolean columnIds, boolean rowIds, String delim)
			throws IOException {
		super(stream, columnIds, rowIds, delim);
	}

	public ReaderTableString(InputStream stream, boolean columnIds, boolean rowIds)
			throws IOException {
		super(stream, columnIds, rowIds);
	}

	public ReaderTableString(Reader reader, boolean columnIds, boolean rowIds, String delim)
			throws IOException {
		super(reader, columnIds, rowIds, delim);
	}

	public ReaderTableString(Reader reader, boolean columnIds, boolean rowIds) throws IOException {
		super(reader, columnIds, rowIds);
	}

	@Override
	protected TableString getInstance() {
		return new TableString();
	}

	@Override
	protected String parse(String s) {
		return s;
	}

	@Override
	public TableString readTableAtOnce() throws IOException {
		return (TableString) super.readTableAtOnce();
	}
}
