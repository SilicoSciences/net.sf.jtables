/**********************************************************************
Copyright (c) 2009-2010 Alexander Kerner. All rights reserved.
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

import net.sf.jtables.table.AbstractTableReader;
import net.sf.jtables.table.AnnotatedMutableTable;

/**
 * 
 * Implementation for {@link net.sf.jtables.table.AbstractTableReader
 * AbstractTableReader} that reads tables of type {@link StringTable}.
 * 
 * 
 * @author <a href="mailto:alex.kerner.24@googlemail.com">Alexander Kerner</a>
 * @version 2010-12-05
 * 
 */
public class StringTableReader extends AbstractTableReader<String>{

	public StringTableReader(boolean columnIds, boolean rowIds, String delim) {
		super(columnIds, rowIds, delim);
	}

	public StringTableReader(boolean columnIds, boolean rowIds) {
		super(columnIds, rowIds);
	}

	@Override
	protected AnnotatedMutableTable<String> getInstance() {
		return new StringTable();
	}

	@Override
	protected String parse(String s) {
		return s;
	}
	
}
